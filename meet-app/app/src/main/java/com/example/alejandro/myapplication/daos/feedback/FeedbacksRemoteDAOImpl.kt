package com.example.alejandro.myapplication.daos.feedback

import android.content.Intent
import com.example.alejandro.myapplication.MainActivity
import com.example.alejandro.myapplication.daos.RemoteDAO
import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.feedback.*
import com.example.alejandro.myapplication.utils.*
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.gson.Gson
import io.realm.RealmList

class FeedbacksRemoteDAOImpl(private val databaseFacade: DatabaseFacade) : FeedbacksRemoteDAO {

    private val listeners = arrayOf(
            arrayOf(PUBLIC_DB, POSIBLE_RESPOSTA_DB),
            arrayOf(PUBLIC_DB, POSIBLES_RESPOSTAS_DB),
            arrayOf(PUBLIC_DB, PREGUNTA_DB),
            arrayOf(PACIENTE_DB, RESPOSTA_DB),
            arrayOf(PUBLIC_DB, MODELO_DB),
            arrayOf(PACIENTE_DB, FEEDBACK_DB))

    // Función que inicializa o percorrido do array de listeners recuperando os datos de cada táboa asociada
    override fun initListeners(remoteDAOs: List<RemoteDAO>) {
        initListener(0, remoteDAOs)
    }

    // Función recursiva que realiza o percorrido do array de listeners recuperando os datos de cada táboa asociada
    // unha vez finalizado este percorrido, chama á mesma función do seguinte RemoteDAO da lista
    private fun initListener(i: Int, remoteDAOs: List<RemoteDAO>) {
        if (listeners.size > i) {
            val listener = listeners[i]

            getCollectionReference(listener[0], listener[1])?.get()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val querySnapshot = task.result
                    if (querySnapshot != null) {
                        for (queryDocumentSnapshot in querySnapshot) {
                            init(queryDocumentSnapshot, listener[1])
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
            FEEDBACK_DB -> {
                val feedback: Feedback? = getFeedback(document)
                if (feedback != null) {
                    //TODO Aquí podería controlarse o cargado inicial mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(feedback)
                }
            }

            MODELO_DB -> {
                val modelo: Modelo? = getModelo(document)
                if (modelo != null) {
                    //TODO Aquí podería controlarse o cargado inicial mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(modelo)
                }
            }

            PREGUNTA_DB -> {
                val pregunta: Pregunta? = getPregunta(document)
                if (pregunta != null) {
                    //TODO Aquí podería controlarse o cargado inicial mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(pregunta)
                }
            }

            POSIBLE_RESPOSTA_DB -> {
                val posibleResposta: PosibleResposta? = getPosibleResposta(document)
                if (posibleResposta != null) {
                    //TODO Aquí podería controlarse o cargado inicial mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(posibleResposta)
                }
            }

            POSIBLES_RESPOSTAS_DB -> {
                val posiblesRespostas: PosiblesRespostas? = getPosiblesRespostas(document)
                if (posiblesRespostas != null) {
                    //TODO Aquí podería controlarse o cargado inicial mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(posiblesRespostas)
                }
            }

            RESPOSTA_DB -> {
                val resposta: Resposta? = getResposta(document)
                if (resposta != null) {
                    //TODO Aquí podería controlarse o cargado inicial mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(resposta)
                }
            }
        }
    }

    private fun setListeners() {
        for (listener in listeners) {
            getCollectionReference(listener[0], listener[1])?.addSnapshotListener(EventListener<QuerySnapshot> { snapshots: QuerySnapshot?, e ->
                if (e != null) {
                    //TODO control de erros
                    return@EventListener
                }
                function(snapshots, listener[1])
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
            FEEDBACK_DB -> {
                val feedback: Feedback? = getFeedback(document)
                if (feedback != null) {
                    //TODO Aquí podería controlarse o engadido durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(feedback)
                }
            }

            MODELO_DB -> {
                val modelo: Modelo? = getModelo(document)
                if (modelo != null) {
                    //TODO Aquí podería controlarse o engadido durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(modelo)
                }
            }

            PREGUNTA_DB -> {
                val pregunta: Pregunta? = getPregunta(document)
                if (pregunta != null) {
                    //TODO Aquí podería controlarse o engadido durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(pregunta)
                }
            }

            POSIBLE_RESPOSTA_DB -> {
                val posibleResposta: PosibleResposta? = getPosibleResposta(document)
                if (posibleResposta != null) {
                    //TODO Aquí podería controlarse o engadido durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(posibleResposta)
                }
            }

            POSIBLES_RESPOSTAS_DB -> {
                val posiblesRespostas: PosiblesRespostas? = getPosiblesRespostas(document)
                if (posiblesRespostas != null) {
                    //TODO Aquí podería controlarse o engadido durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(posiblesRespostas)
                }
            }

            RESPOSTA_DB -> {
                val resposta: Resposta? = getResposta(document)
                if (resposta != null) {
                    //TODO Aquí podería controlarse o engadido durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(resposta)
                }
            }
        }
    }

    private fun modified(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            FEEDBACK_DB -> {
                val feedback: Feedback? = getFeedback(document)
                if (feedback != null) {
                    //TODO Aquí podería controlarse o modificado durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(feedback)
                }
            }

            MODELO_DB -> {
                val modelo: Modelo? = getModelo(document)
                if (modelo != null) {
                    //TODO Aquí podería controlarse o modificado durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(modelo)
                }
            }

            PREGUNTA_DB -> {
                val pregunta: Pregunta? = getPregunta(document)
                if (pregunta != null) {
                    //TODO Aquí podería controlarse o modificado durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(pregunta)
                }
            }

            POSIBLE_RESPOSTA_DB -> {
                val posibleResposta: PosibleResposta? = getPosibleResposta(document)
                if (posibleResposta != null) {
                    //TODO Aquí podería controlarse o modificado durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(posibleResposta)
                }
            }

            POSIBLES_RESPOSTAS_DB -> {
                val posiblesRespostas: PosiblesRespostas? = getPosiblesRespostas(document)
                if (posiblesRespostas != null) {
                    //TODO Aquí podería controlarse o modificado durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(posiblesRespostas)
                }
            }

            RESPOSTA_DB -> {
                val resposta: Resposta? = getResposta(document)
                if (resposta != null) {
                    //TODO Aquí podería controlarse o modificado durante a execución da aplicación mediante unha notificación, por exemplo
                    databaseFacade.saveLocal(resposta)
                }
            }
        }
    }

    private fun removed(document: QueryDocumentSnapshot, collection: String) {
        when (collection) {
            FEEDBACK_DB -> {
                //TODO Aquí podería controlarse a eliminacion durante a execución da aplicación mediante unha notificación, por exemplo
                databaseFacade.removeFeedback(document.id)
            }

            MODELO_DB -> {
                //TODO Aquí podería controlarse a eliminacion durante a execución da aplicación mediante unha notificación, por exemplo
                databaseFacade.removeModelo(document.id)
            }

            PREGUNTA_DB -> {
                //TODO Aquí podería controlarse a eliminacion durante a execución da aplicación mediante unha notificación, por exemplo
                databaseFacade.removePregunta(document.id)
            }

            POSIBLE_RESPOSTA_DB -> {
                //TODO Aquí podería controlarse a eliminacion durante a execución da aplicación mediante unha notificación, por exemplo
                databaseFacade.removePosibleResposta(document.id)
            }

            POSIBLES_RESPOSTAS_DB -> {
                //TODO Aquí podería controlarse a eliminacion durante a execución da aplicación mediante unha notificación, por exemplo
                databaseFacade.removePosiblesRespostas(document.id)
            }

            RESPOSTA_DB -> {
                //TODO Aquí podería controlarse a eliminacion durante a execución da aplicación mediante unha notificación, por exemplo
                databaseFacade.removeResposta(document.id)
            }
        }
    }

    private fun getFeedback(document: QueryDocumentSnapshot): Feedback? {
        val id = document.id
        val title = toString(document.get(TITLE_DB))
        val dataFin = toDate(document.get(DATA_FIN_DB))
        val modeloId = toString(document.get(MODELO_DB))
        val modelo: Modelo? = if (modeloId != null) {
            databaseFacade.getModelo(modeloId)
        } else {
            null
        }
        val respostasA = Gson().fromJson(document.get(RESPOSTAS_DB).toString(), ArrayList::class.java)
        val respostas: RealmList<Resposta> = RealmList()
        if (respostasA != null) {
            for (idResposta in respostasA) {
                val respostaId = toString(idResposta)
                if (respostaId != null) {
                    val resposta: Resposta? = databaseFacade.getResposta(respostaId)
                    if (resposta != null) {
                        respostas.add(resposta)
                    }
                }
            }
        }
        val estado = toString(document.get(ESTADO_DB))

        if (title != null && dataFin != null && modelo != null) {
            return Feedback(id, title, dataFin, modelo, respostas, estado)
        }
        return null
    }

    private fun getModelo(document: QueryDocumentSnapshot): Modelo? {
        val id = document.id
        val version: Double? = toDouble(document.get(VERSION_DB).toString())
        val titulo: String? = toString(document.get(TITULO_DB))
        val preguntasA = Gson().fromJson(document.get(PREGUNTAS_DB).toString(), ArrayList::class.java)
        val preguntas: RealmList<Pregunta> = RealmList()
        if (preguntasA != null) {
            for (idPregunta in preguntasA) {
                val preguntaId = toString(idPregunta)
                if (preguntaId != null) {
                    val pregunta: Pregunta? = databaseFacade.getPregunta(preguntaId)
                    if (pregunta != null) {
                        preguntas.add(pregunta)
                    }
                }
            }
        }

        if (version != null && titulo != null) {
            return Modelo(id, version, titulo, preguntas)
        }
        return null
    }

    private fun getPregunta(document: QueryDocumentSnapshot): Pregunta? {
        val id = document.id
        val enunciado: String = document.get(ENUNCIADO_DB).toString()
        val posiblesRespostas: PosiblesRespostas? = databaseFacade.getPosiblesRespostas(document.get(POSIBLES_RESPOSTAS_DB).toString())

        return Pregunta(id, enunciado, posiblesRespostas)
    }

    private fun getPosiblesRespostas(document: QueryDocumentSnapshot): PosiblesRespostas? {
        val id = document.id
        val representacion: String? = toString(document.get(REPRESENTACION_DB))
        val posibleRespostasA = Gson().fromJson(document.get(POSIBLE_RESPOSTAS_DB).toString(), ArrayList::class.java)
        val posibleRespostas: RealmList<PosibleResposta> = RealmList()
        if (posibleRespostasA != null) {
            for (idPosibleResposta in posibleRespostasA) {
                val posibleRespostaId = toString(idPosibleResposta)
                if (posibleRespostaId != null) {
                    val posibleResposta: PosibleResposta? = databaseFacade.getPosibleResposta(posibleRespostaId)
                    if (posibleResposta != null) {
                        posibleRespostas.add(posibleResposta)
                    }
                }
            }
        }

        if (representacion != null) {
            return PosiblesRespostas(id, representacion, posibleRespostas)
        }
        return null
    }

    private fun getPosibleResposta(document: QueryDocumentSnapshot): PosibleResposta? {
        val id = document.id
        val representacion: String? = toString(document.get(REPRESENTACION_DB))
        val valor: String? = toString(document.get(VALOR_DB))

        if (representacion != null && valor != null) {
            return PosibleResposta(id, representacion, valor)
        }
        return null
    }

    private fun getResposta(document: QueryDocumentSnapshot): Resposta? {
        val id = document.id
        val pregunta: Pregunta? = databaseFacade.getPregunta(document.get(PREGUNTA_DB).toString())
        val posibleResposta: PosibleResposta? = databaseFacade.getPosibleResposta(document.get(POSIBLE_RESPOSTA_DB).toString())

        if (pregunta != null) {
            return Resposta(id, pregunta, posibleResposta)
        }
        return null
    }

    //Función de gardado, no caso de ser a primeira vez que se garda, haberá que inicializala, no caso contrario actualizala
    override fun save(feedback: Feedback) {
        if (feedback.id != null) {
            update(feedback)
        } else {
            init(feedback)
        }
    }

    private fun init(feedback: Feedback) {
        val modeloDB = getUserCollectionReference(FEEDBACK_DB)

        modeloDB?.add(toMap(feedback))?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                databaseFacade.updateId(feedback, task.result?.id)
            } else {
                //TODO control de erros
            }
        }
    }

    private fun update(feedback: Feedback) {
        val modeloDB = getUserDocumentReference(FEEDBACK_DB, feedback.id)

        modeloDB?.set(toMap(feedback))?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                databaseFacade.saveLocal(feedback)
            } else {
                //TODO control de erros
            }
        }
    }

    //Función de gardado, no caso de ser a primeira vez que se garda, haberá que inicializala, no caso contrario actualizala
    override fun save(resposta: Resposta) {
        if (resposta.id != null) {
            update(resposta)
        } else {
            init(resposta)
        }
    }

    private fun init(resposta: Resposta) {
        val modeloDB = getUserCollectionReference(RESPOSTA_DB)

        modeloDB?.add(toMap(resposta))?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                databaseFacade.updateId(resposta, task.result?.id)
            } else {
                //TODO control de erros
            }
        }
    }

    private fun update(resposta: Resposta) {
        val modeloDB = getUserDocumentReference(RESPOSTA_DB, resposta.id)

        modeloDB?.set(toMap(resposta))?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                databaseFacade.saveLocal(resposta)
            } else {
                //TODO control de erros
            }
        }
    }
}