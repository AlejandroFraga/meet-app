package com.example.alejandro.myapplication.daos.notifications

import android.content.Intent
import com.example.alejandro.myapplication.MainActivity
import com.example.alejandro.myapplication.daos.RemoteDAO
import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.notifications.Notificacion
import com.example.alejandro.myapplication.utils.*
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import java.util.*

class NotificationsRemoteDAOImpl(private val databaseFacade: DatabaseFacade) : NotificationsRemoteDAO {

    private val listeners = arrayOf(NOTIFICACION_DB)

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
            NOTIFICACION_DB -> {
                val notificacion: Notificacion? = getNotificacion(document)
                if (notificacion != null) {
                    databaseFacade.saveLocal(notificacion)
                    //TODO aqui habera que comprobar segundo a data e se foi enviada mostrar a notificacion
                    if (!notificacion.enviada) {
                        databaseFacade.notifications(notificacion)
                    } else {
                        databaseFacade.dismissNotifications(notificacion.notificacionId)
                    }
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
            NOTIFICACION_DB -> {
                val notificacion: Notificacion? = getNotificacion(document)
                if (notificacion != null) {
                    databaseFacade.saveLocal(notificacion)
                    //TODO aqui habera que comprobar segundo a data e se foi enviada mostrar a notificacion
                    if (!notificacion.enviada) {
                        databaseFacade.notifications(notificacion)
                    } else {
                        databaseFacade.dismissNotifications(notificacion.notificacionId)
                    }
                }
            }
        }
    }

    private fun modified(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            NOTIFICACION_DB -> {
                val notificacion: Notificacion? = getNotificacion(document)
                if (notificacion != null) {
                    databaseFacade.saveLocal(notificacion)
                    //TODO aqui habera que comprobar segundo a data e se foi enviada mostrar a notificacion
                    if (!notificacion.enviada) {
                        databaseFacade.notifications(notificacion)
                    } else {
                        databaseFacade.dismissNotifications(notificacion.notificacionId)
                    }
                }
            }
        }
    }

    private fun removed(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            NOTIFICACION_DB -> {
                //TODO aqui habera que facer un dismiss da notificación
                databaseFacade.removeNotificacion(document.id)
            }
        }
    }

    private fun getNotificacion(document: QueryDocumentSnapshot): Notificacion? {
        val id = document.id
        val notificacionId = toInt(document.get(NOTIFICACION_ID_DB))
        val title = toString(document.get(TITLE_DB))
        val bigTitle = toString(document.get(BIG_TITLE_DB))
        val text = toString(document.get(TEXT_DB))
        val bigText = toString(document.get(BIG_TEXT_DB))
        val data = toDate(document.get(DATA_DB))
        val enviada = toBoolean(document.get(ENVIADA_DB))

        if (title != null && text != null) {
            return Notificacion(id, notificacionId, title, bigTitle, text, bigText, data, enviada)
        }
        return null
    }

    //Función de gardado, no caso de ser a primeira vez que se garda, haberá que inicializala, no caso contrario actualizala
    override fun save(notificacion: Notificacion) {
        if (notificacion.id != null) {
            update(notificacion)
        } else {
            init(notificacion)
        }
    }

    private fun init(notificacion: Notificacion) {
        val modeloDB = getUserCollectionReference(NOTIFICACION_DB)

        if (modeloDB != null) {
            modeloDB.add(toMap(notificacion)).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    databaseFacade.updateId(notificacion, task.result?.id)
                } else {
                    //TODO control de erros
                }
            }
        }
    }

    private fun update(notificacion: Notificacion) {
        val modeloDB = getUserDocumentReference(NOTIFICACION_DB, notificacion.id)

        if (modeloDB != null) {
            modeloDB.set(toMap(notificacion)).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    databaseFacade.saveLocal(notificacion)
                } else {
                    //TODO control de erros
                }
            }
        }
    }
}