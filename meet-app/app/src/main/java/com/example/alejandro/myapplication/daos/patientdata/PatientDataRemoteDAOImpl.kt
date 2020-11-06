package com.example.alejandro.myapplication.daos.patientdata

import android.content.Intent
import com.example.alejandro.myapplication.MainActivity
import com.example.alejandro.myapplication.daos.RemoteDAO
import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.patientdata.Etapa
import com.example.alejandro.myapplication.model.patientdata.FichaPaciente
import com.example.alejandro.myapplication.utils.*
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.gson.Gson
import io.realm.RealmList
import java.util.*

class PatientDataRemoteDAOImpl(private val databaseFacade: DatabaseFacade) : PatientDataRemoteDAO {

    private val listeners = arrayOf(ETAPA_DB, FICHA_PACIENTE_DB)

    // Función que inicializa o percorrido do array de listeners recuperando os datos de cada táboa asociada
    override fun initListeners(remoteDAOs: List<RemoteDAO>) {
        initListener(0, remoteDAOs)
    }

    // Función recursiva que realiza o percorrido do array de listeners recuperando os datos de cada táboa asociada
    // unha vez finalizado este percorrido, chama á mesma función do seguinte RemoteDAO da lista
    private fun initListener(i: Int, remoteDAOs: List<RemoteDAO>) {
        if (listeners.size > i) {
            val listener: String = listeners[i]

            getUserCollectionReference(listener)?.get()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val querySnapshot = task.result
                    if (querySnapshot != null) {
                        for (queryDocumentSnapshot in querySnapshot) {
                            init(queryDocumentSnapshot, listener)
                        }
                    }
                } else {
                    //TODO control de erros
                }
                initListener(i + 1, remoteDAOs)
            }
        } else {
            setListeners()
            if(remoteDAOs.isNotEmpty()){
                remoteDAOs[0].initListeners(remoteDAOs.drop(1))
            } else {
                val intent = Intent(databaseFacade.context, MainActivity::class.java)
                databaseFacade.context.startActivity(intent)
            }
        }
    }

    private fun init(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            FICHA_PACIENTE_DB -> {
                val fichaPaciente = getFichaPaciente(document)
                if (fichaPaciente != null) {
                    //TODO Aquí podería controlarse o cargado inicial mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(fichaPaciente)
                }
            }

            ETAPA_DB -> {
                val etapa = getEtapa(document)
                if (etapa != null) {
                    //TODO Aquí podería controlarse o cargado inicial mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(etapa)
                }
            }
        }
    }

    private fun setListeners() {
        for (listener in listeners) {
            getUserCollectionReference(listener)?.addSnapshotListener(EventListener<QuerySnapshot> { snapshots: QuerySnapshot?, e ->
                if (e != null) {
                    //TODO control de erros
                    return@EventListener
                }
                function(snapshots, listener)
            })
        }
    }

    private fun function(snapshots: QuerySnapshot?, collection: String) {
        for (dc in snapshots!!.documentChanges) {
            when (dc.type) {
                DocumentChange.Type.ADDED -> {
                    added(dc.document, collection)
                }

                DocumentChange.Type.MODIFIED -> {
                    modified(dc.document, collection)
                }

                DocumentChange.Type.REMOVED -> {
                    removed(dc.document, collection)
                }
            }
        }
    }

    private fun added(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            FICHA_PACIENTE_DB -> {
                val fichaPaciente = getFichaPaciente(document)
                if (fichaPaciente != null) {
                    //TODO Aquí podería controlarse o engadido durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(fichaPaciente)
                }
            }

            ETAPA_DB -> {
                val etapa = getEtapa(document)
                if (etapa != null) {
                    //TODO Aquí podería controlarse o engadido durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(etapa)
                }
            }
        }
    }

    private fun modified(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            FICHA_PACIENTE_DB -> {
                val fichaPaciente = getFichaPaciente(document)
                if (fichaPaciente != null) {
                    //TODO Aquí podería controlarse o modificado durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(fichaPaciente)
                }
            }

            ETAPA_DB -> {
                val etapa = getEtapa(document)
                if (etapa != null) {
                    //TODO Aquí podería controlarse o modificado durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(etapa)
                }
            }
        }
    }

    private fun removed(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            FICHA_PACIENTE_DB -> {
                //TODO Aquí podería controlarse a eliminacion durante a execución da aplicación mediante unha notificación, por exemplo
                databaseFacade.removeFichaPaciente(document.id)
            }

            ETAPA_DB -> {
                //TODO Aquí podería controlarse a eliminacion durante a execución da aplicación mediante unha notificación, por exemplo
                databaseFacade.removeEtapa(document.id)
            }
        }
    }

    private fun getFichaPaciente(document: QueryDocumentSnapshot): FichaPaciente? {
        val id = document.id
        val nome = toString(document.get(NOME_DB))
        val primeiroApelido = toString(document.get(PRIMEIRO_APELIDO_DB))
        val segundoApelido = toString(document.get(SEGUNDO_APELIDO_DB))
        val nacemento = toDate(document.get(NACEMENTO_DB))
        val etapasA = Gson().fromJson(document.get(ETAPAS_DB).toString(), ArrayList::class.java)
        val etapas: RealmList<Etapa> = RealmList()
        if (etapasA != null) {
            for (etapaId in etapasA) {
                val respostaS = toString(etapaId)
                if (respostaS != null) {
                    val etapa = databaseFacade.getEtapa(respostaS)
                    if (etapa != null) {
                        etapas.add(etapa)
                    }
                }
            }
        }

        if (nome != null && primeiroApelido != null && segundoApelido != null && nacemento != null) {
            return FichaPaciente(id, nome, primeiroApelido, segundoApelido, nacemento, etapas)
        }
        return null
    }

    private fun getEtapa(document: QueryDocumentSnapshot): Etapa? {
        val id = document.id
        val title = toString(document.get(TITLE_DB))
        val descripcion = toString(document.get(DESCRIPCION_DB))
        val dataComezo = toDate(document.get(DATA_COMEZO_DB))
        val dataFin = toDate(document.get(DATA_FIN_DB))

        if (title != null && descripcion != null && dataComezo != null && dataFin != null) {
            return Etapa(id, title, descripcion, dataComezo, dataFin)
        }
        return null
    }
}