package com.example.alejandro.myapplication.daos.configuration

import android.content.Intent
import android.util.Log
import com.example.alejandro.myapplication.MainActivity
import com.example.alejandro.myapplication.daos.RemoteDAO
import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.configuration.Configuracion
import com.example.alejandro.myapplication.utils.*
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class ConfigurationsRemoteDAOImpl(private val databaseFacade: DatabaseFacade) : ConfigurationsRemoteDAO {

    private val listeners = arrayOf(CONFIGURACION_DB)

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
            CONFIGURACION_DB -> {
                val configuracion: Configuracion? = getConfiguracion(document)
                if (configuracion != null) {
                    //TODO Aquí podería controlarse o cargado inicial mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(configuracion)
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
            CONFIGURACION_DB -> {
                val configuracion: Configuracion? = getConfiguracion(document)
                if (configuracion != null) {
                    //TODO Aquí podería controlarse o engadido durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(configuracion)
                }
            }
        }
    }

    private fun modified(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            CONFIGURACION_DB -> {
                val configuracion: Configuracion? = getConfiguracion(document)
                if (configuracion != null) {
                    //TODO Aquí podería controlarse o modificado durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(configuracion)
                }
            }
        }
    }

    private fun removed(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            CONFIGURACION_DB -> {
                //TODO Aquí podería controlarse a eliminacion durante a execución da aplicación mediante unha notificación, por exemplo
                databaseFacade.removeConfiguracion(document.id)
            }
        }
    }

    private fun getConfiguracion(document: QueryDocumentSnapshot): Configuracion? {
        val id = document.id
        val descripcion = toString(document.get(DESCRIPCION_DB))
        val valor = toString(document.get(VALOR_DB))

        if (descripcion != null && valor != null) {
            return Configuracion(id, descripcion, valor)
        }
        return null
    }

    //Función de gardado, no caso de ser a primeira vez que se garda, haberá que inicializala, no caso contrario actualizala
    override fun save(configuracion: Configuracion) {
        if (configuracion.id != null) {
            update(configuracion)
        } else {
            init(configuracion)
        }
    }

    private fun init(configuracion: Configuracion) {
        val modeloDB = getUserCollectionReference(CONFIGURACION_DB)

        if (modeloDB != null) {
            modeloDB.add(toMap(configuracion)).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    databaseFacade.updateId(configuracion, task.result?.id)
                } else {
                    //TODO control de erros
                }
            }
        }
    }

    private fun update(configuracion: Configuracion) {
        val modeloDB = getUserDocumentReference(CONFIGURACION_DB, configuracion.id)

        if (modeloDB != null) {
            modeloDB.set(toMap(configuracion)).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    databaseFacade.saveLocal(configuracion)
                } else {
                    //TODO control de erros
                }
            }
        }
    }
}