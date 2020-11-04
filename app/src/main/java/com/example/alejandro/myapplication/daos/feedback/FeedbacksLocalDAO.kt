package com.example.alejandro.myapplication.daos.feedback

import com.example.alejandro.myapplication.model.feedback.*
import io.realm.RealmResults

interface FeedbacksLocalDAO {

    fun getAllFeedbacks(): RealmResults<Feedback>

    fun getFeedback(id: String): Feedback?

    fun getFeedbacksFromModelo(id: String): RealmResults<Feedback>

    fun getModelo(id: String): Modelo?

    fun getPregunta(id: String): Pregunta?

    fun getPreguntasFromPosiblesRespostas(id: String): RealmResults<Pregunta>

    fun getRespostasFromPregunta(id: String): RealmResults<Resposta>

    fun getPosibleResposta(id: String): PosibleResposta?

    fun getPosiblesRespostas(id: String): PosiblesRespostas?

    fun getResposta(id: String): Resposta?

    fun removeFeedback(id: String?)

    fun removeModelo(id: String?)

    fun removePregunta(id: String?)

    fun removePosibleResposta(id: String?)

    fun removePosiblesRespostas(id: String?)

    fun removeResposta(id: String?)

    fun updateId(feedback: Feedback?, id: String?)

    fun updateId(resposta: Resposta?, id: String?)

    fun updateResposta(feedback: Feedback, resposta: Resposta, posibleResposta: PosibleResposta)

    fun updateCovered(feedbacks: RealmResults<Feedback>)

    fun updateCovered(feedback: Feedback)

    fun initRespostas(feedback: Feedback)

    fun send(feedback: Feedback)

    fun save(feedback: Feedback)

    fun save(modelo: Modelo)

    fun save(pregunta: Pregunta)

    fun save(posibleResposta: PosibleResposta)

    fun save(posiblesRespostas: PosiblesRespostas)

    fun save(resposta: Resposta)

}