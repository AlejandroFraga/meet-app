package com.example.alejandro.myapplication.service.feedback

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.feedback.*
import io.realm.RealmResults


class FeedbacksServiceImpl(private val databaseFacade: DatabaseFacade) : FeedbacksService {

    //Functions
    override fun getAllFeedbacks(): RealmResults<Feedback> {
        return databaseFacade.getAllFeedbacks()
    }

    override fun getFeedback(id: String): Feedback? {
        return databaseFacade.getFeedback(id)
    }

    override fun getFeedbacksFromModelo(id: String): RealmResults<Feedback> {
        return databaseFacade.getFeedbacksFromModelo(id)
    }

    override fun getModelo(id: String): Modelo? {
        return databaseFacade.getModelo(id)
    }

    override fun getPregunta(id: String): Pregunta? {
        return databaseFacade.getPregunta(id)
    }

    override fun getPreguntasFromPosiblesRespostas(id: String): RealmResults<Pregunta> {
        return databaseFacade.getPreguntasFromPosiblesRespostas(id)
    }

    override fun getRespostasFromPregunta(id: String): RealmResults<Resposta> {
        return databaseFacade.getRespostasFromPregunta(id)
    }

    override fun getPosibleResposta(id: String): PosibleResposta? {
        return databaseFacade.getPosibleResposta(id)
    }

    override fun getPosiblesRespostas(id: String): PosiblesRespostas? {
        return databaseFacade.getPosiblesRespostas(id)
    }

    override fun getResposta(id: String): Resposta? {
        return databaseFacade.getResposta(id)
    }

    override fun removeFeedback(id: String) {
        databaseFacade.removeFeedback(id)
    }

    override fun removeModelo(id: String) {
        databaseFacade.removeModelo(id)
    }

    override fun removePregunta(id: String) {
        databaseFacade.removePregunta(id)
    }

    override fun removePosibleResposta(id: String) {
        databaseFacade.removePosibleResposta(id)
    }

    override fun removePosiblesRespostas(id: String) {
        databaseFacade.removePosiblesRespostas(id)
    }

    override fun removeResposta(id: String) {
        databaseFacade.removeResposta(id)
    }

    override fun updateResposta(updateResposta: Feedback, resposta: Resposta, posibleResposta: PosibleResposta) {
        databaseFacade.updateResposta(updateResposta, resposta, posibleResposta)
    }

    override fun updateCovered(feedbacks: RealmResults<Feedback>) {
        databaseFacade.updateCovered(feedbacks)
    }

    override fun updateCovered(feedback: Feedback) {
        databaseFacade.updateCovered(feedback)
    }

    override fun initRespostas(feedback: Feedback) {
        databaseFacade.initRespostas(feedback)
    }

    override fun send(feedback: Feedback) {
        databaseFacade.send(feedback)
    }

    override fun saveLocal(feedback: Feedback) {
        databaseFacade.saveLocal(feedback)
    }

    override fun saveLocal(modelo: Modelo) {
        databaseFacade.saveLocal(modelo)
    }

    override fun saveLocal(pregunta: Pregunta) {
        databaseFacade.saveLocal(pregunta)
    }

    override fun saveLocal(posibleResposta: PosibleResposta) {
        databaseFacade.saveLocal(posibleResposta)
    }

    override fun saveLocal(posiblesRespostas: PosiblesRespostas) {
        databaseFacade.saveLocal(posiblesRespostas)
    }

    override fun saveLocal(resposta: Resposta) {
        databaseFacade.saveLocal(resposta)
    }

    override fun save(feedback: Feedback) {
        databaseFacade.save(feedback)
    }

    override fun save(resposta: Resposta) {
        databaseFacade.save(resposta)
    }

}