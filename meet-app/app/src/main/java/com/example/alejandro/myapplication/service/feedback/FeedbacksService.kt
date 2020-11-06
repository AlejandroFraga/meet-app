package com.example.alejandro.myapplication.service.feedback

import com.example.alejandro.myapplication.model.feedback.*
import io.realm.RealmResults

interface FeedbacksService {

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

    fun removeFeedback(id: String)

    fun removeModelo(id: String)

    fun removePregunta(id: String)

    fun removePosibleResposta(id: String)

    fun removePosiblesRespostas(id: String)

    fun removeResposta(id: String)

    fun updateResposta(updateResposta: Feedback, resposta: Resposta, posibleResposta: PosibleResposta)

    fun updateCovered(feedbacks: RealmResults<Feedback>)

    fun updateCovered(feedback: Feedback)

    fun initRespostas(feedback: Feedback)

    fun send(feedback: Feedback)

    fun saveLocal(feedback: Feedback)

    fun saveLocal(modelo: Modelo)

    fun saveLocal(pregunta: Pregunta)

    fun saveLocal(posibleResposta: PosibleResposta)

    fun saveLocal(posiblesRespostas: PosiblesRespostas)

    fun saveLocal(resposta: Resposta)

    fun save(feedback: Feedback)

    fun save(resposta: Resposta)

}