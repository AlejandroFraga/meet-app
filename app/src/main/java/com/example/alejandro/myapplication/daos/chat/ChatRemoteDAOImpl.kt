package com.example.alejandro.myapplication.daos.chat

import android.content.Intent
import android.util.Log
import com.example.alejandro.myapplication.MainActivity
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.daos.RemoteDAO
import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.chat.Mensaxe
import com.example.alejandro.myapplication.model.notifications.Notificacion
import com.example.alejandro.myapplication.utils.*
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import java.util.*

class ChatRemoteDAOImpl(private val databaseFacade: DatabaseFacade) : ChatRemoteDAO {

    private val listeners = arrayOf(MENSAXE_DB)

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
            if (remoteDAOs.isNotEmpty()) {
                remoteDAOs[0].initListeners(remoteDAOs.drop(1))
            } else {
                val intent = Intent(databaseFacade.context, MainActivity::class.java)
                databaseFacade.context.startActivity(intent)
            }
        }
    }

    private fun init(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            MENSAXE_DB -> {
                val mensaxe: Mensaxe? = getMensaxe(document)
                if (mensaxe != null) {
                    databaseFacade.saveLocal(mensaxe)
                    //TODO Aquí podería controlarse o cargado inicial mediante unha notificación, por exemplo
                    if (!mensaxe.ePropia && mensaxe.dataLectura == null) {
                        val pendingMensaxes = databaseFacade.getPendingMensaxes()
                        if (!pendingMensaxes.isEmpty()) {
                            val youHave = databaseFacade.context.resources.getString(R.string.you_have)
                            val pendingMessages = databaseFacade.context.resources.getString(R.string.pending_messages)

                            val notificacion = Notificacion(null,
                                    NOTTFICACION_ID_CHAT,
                                    youHave + SPACE + pendingMensaxes.size + SPACE + pendingMessages,
                                    youHave + SPACE + pendingMensaxes.size + SPACE + pendingMessages,
                                    pendingMensaxes[pendingMensaxes.size - 1]!!.mensaxe,
                                    pendingMensaxes[pendingMensaxes.size - 1]!!.mensaxe,
                                    Date(), false)

                            databaseFacade.notifications(notificacion)
                            databaseFacade.saveLocal(notificacion)
                        }
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
            MENSAXE_DB -> {
                val mensaxe: Mensaxe? = getMensaxe(document)
                if (mensaxe != null) {
                    databaseFacade.saveLocal(mensaxe)
                    //TODO Aquí podería controlarse o engadido durante a execución da aplicación mediante unha notificación, por exemplo
                    if (!mensaxe.ePropia && mensaxe.dataLectura == null) {
                        val pendingMensaxes = databaseFacade.getPendingMensaxes()
                        if (!pendingMensaxes.isEmpty()) {
                            val youHave = databaseFacade.context.resources.getString(R.string.you_have)
                            val pendingMessages = databaseFacade.context.resources.getString(R.string.pending_messages)

                            val notificacion = Notificacion(null,
                                    NOTTFICACION_ID_CHAT,
                                    youHave + SPACE + pendingMensaxes.size + SPACE + pendingMessages,
                                    youHave + SPACE + pendingMensaxes.size + SPACE + pendingMessages,
                                    pendingMensaxes[pendingMensaxes.size - 1]!!.mensaxe,
                                    pendingMensaxes[pendingMensaxes.size - 1]!!.mensaxe,
                                    Date(), false)

                            databaseFacade.notifications(notificacion)
                            databaseFacade.saveLocal(notificacion)
                        }
                    }
                }
            }
        }
    }

    private fun modified(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            MENSAXE_DB -> {
                val mensaxe: Mensaxe? = getMensaxe(document)
                if (mensaxe != null) {
                    //TODO Aquí podería controlarse o modificado durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(mensaxe)
                }
            }
        }
    }

    private fun removed(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            MENSAXE_DB -> {
                //TODO Aquí podería controlarse a eliminacion durante a execución da aplicación mediante unha notificación, por exemplo
                databaseFacade.removeMensaxe(document.id)
            }
        }
    }

    private fun getMensaxe(document: QueryDocumentSnapshot): Mensaxe? {
        val id = document.id
        val mensaxe = toString(document.get(MENSAXE_DB))
        val dataCreacion = toDate(document.get(DATA_CREACION_DB))
        val dataEnvio = toDate(document.get(DATA_ENVIO_DB))
        val dataRecepcion = toDate(document.get(DATA_RECEPCION_DB))
        val dataLectura = toDate(document.get(DATA_LECTURA_DB))
        val ePropia = toBoolean(document.get(E_PROPIA_DB))
        val respostaAId = toString(document.get(RESPOSTA_A_DB))
        val respostaA = if (respostaAId != null) {
            databaseFacade.getMensaxe(respostaAId)
        } else {
            null
        }

        if (mensaxe != null && dataCreacion != null && ePropia != null) {
            return Mensaxe(id, mensaxe, dataCreacion, dataEnvio, dataRecepcion, dataLectura, ePropia, respostaA)
        }
        return null
    }

    //Función de gardado, no caso de ser a primeira vez que se garda, haberá que inicializala, no caso contrario actualizala
    override fun save(mensaxe: Mensaxe) {
        if (mensaxe.id != null) {
            update(mensaxe)
        } else {
            databaseFacade.updateDataEnvio(mensaxe)
            init(mensaxe)
        }
    }

    private fun init(mensaxe: Mensaxe) {
        val modeloDB = getUserCollectionReference(MENSAXE_DB)

        if (modeloDB != null) {
            modeloDB.add(toMap(mensaxe)).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    databaseFacade.updateId(mensaxe, task.result?.id)
                } else {
                    //TODO control de erros
                }
            }
        }
    }

    private fun update(mensaxe: Mensaxe) {
        val modeloDB = getUserDocumentReference(MENSAXE_DB, mensaxe.id)

        if (modeloDB != null) {
            modeloDB.set(toMap(mensaxe)).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    databaseFacade.saveLocal(mensaxe)
                } else {
                    //TODO control de erros
                }
            }
        }
    }
}