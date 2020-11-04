package com.example.alejandro.myapplication.daos.calendar

import android.content.Intent
import com.example.alejandro.myapplication.MainActivity
import com.example.alejandro.myapplication.activities.chat.ChatActivity
import com.example.alejandro.myapplication.daos.RemoteDAO
import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.calendar.Evento
import com.example.alejandro.myapplication.model.notifications.Notificacion
import com.example.alejandro.myapplication.utils.*
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import java.util.*

class CalendarRemoteDAOImpl(private val databaseFacade: DatabaseFacade) : CalendarRemoteDAO {

    private val listeners = arrayOf(EVENTO_DB)

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
            EVENTO_DB -> {
                val evento: Evento? = getEvento(document)
                if (evento != null) {
                    //TODO Aquí podería controlarse o cargado inicial mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(evento)
                }
            }
        }
    }

    fun setListeners() {
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
            EVENTO_DB -> {
                val evento: Evento? = getEvento(document)
                if (evento != null) {
                    //TODO Aquí podería controlarse o engadido durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(evento)
                }
            }
        }
    }

    private fun modified(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            EVENTO_DB -> {
                val evento: Evento? = getEvento(document)
                if (evento != null) {
                    //TODO Aquí podería controlarse o modificado durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(evento)
                }
            }
        }
    }

    private fun removed(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            EVENTO_DB -> {
                //TODO Aquí podería controlarse a eliminacion durante a execución da aplicación mediante unha notificación, por exemplo
                databaseFacade.removeEvento(document.id)
            }
        }
    }

    private fun getEvento(document: QueryDocumentSnapshot): Evento? {
        val id = document.id
        val titulo = toString(document.get(TITULO_DB))
        val dataComezo = toDate(document.get(DATA_COMEZO_DB))
        val dataFin = toDate(document.get(DATA_FIN_DB))
        val dataNotificacion = toDate(document.get(DATA_NOTIFICACION_DB))
        val ubicacion = toString(document.get(UBICACION_DB))
        val cor = toString(document.get(COR_DB))

        if (titulo != null && dataComezo != null && dataFin != null) {
            return Evento(id, titulo, dataComezo, dataFin, dataNotificacion, ubicacion, cor)
        }
        return null
    }

    //Función de gardado, no caso de ser a primeira vez que se garda, haberá que inicializala, no caso contrario actualizala
    override fun save(evento: Evento) {
        if (evento.id != null) {
            update(evento)
        } else {
            init(evento)
        }
    }

    private fun init(evento: Evento) {
        val modeloDB = getUserCollectionReference(EVENTO_DB)

        if(modeloDB != null) {
            modeloDB.add(toMap(evento)).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    databaseFacade.updateId(evento, task.result?.id)
                } else {
                    //TODO control de erros
                }
            }
        }
    }

    private fun update(evento: Evento) {
        val modeloDB = getUserDocumentReference(EVENTO_DB, evento.id)

        if(modeloDB != null) {
            modeloDB.set(toMap(evento)).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    databaseFacade.saveLocal(evento)
                } else {
                    //TODO control de erros
                }
            }
        }
    }
}