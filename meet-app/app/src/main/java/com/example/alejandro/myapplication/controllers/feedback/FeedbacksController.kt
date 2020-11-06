package com.example.alejandro.myapplication.controllers.feedback

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.feedback.*
import com.example.alejandro.myapplication.service.feedback.FeedbacksService
import com.example.alejandro.myapplication.service.feedback.FeedbacksServiceImpl
import io.realm.RealmResults

//Controlador do paquete Feedbacks

class FeedbacksController(databaseFacade: DatabaseFacade) {

    //Service
    private val feedbacksService: FeedbacksService = FeedbacksServiceImpl(databaseFacade)

    //Functions
    fun getAllFeedbacks(): RealmResults<Feedback> {
        return feedbacksService.getAllFeedbacks()
    }

    fun getFeedback(id: String): Feedback? {
        return feedbacksService.getFeedback(id)
    }

    fun getFeedbacksFromModelo(id: String): RealmResults<Feedback> {
        return feedbacksService.getFeedbacksFromModelo(id)
    }

    fun getModelo(id: String): Modelo? {
        return feedbacksService.getModelo(id)
    }

    fun getPregunta(id: String): Pregunta? {
        return feedbacksService.getPregunta(id)
    }

    fun getPreguntasFromPosiblesRespostas(id: String): RealmResults<Pregunta> {
        return feedbacksService.getPreguntasFromPosiblesRespostas(id)
    }

    fun getRespostasFromPregunta(id: String): RealmResults<Resposta> {
        return feedbacksService.getRespostasFromPregunta(id)
    }

    fun getPosibleResposta(id: String): PosibleResposta? {
        return feedbacksService.getPosibleResposta(id)
    }

    fun getPosiblesRespostas(id: String): PosiblesRespostas? {
        return feedbacksService.getPosiblesRespostas(id)
    }

    fun getResposta(id: String): Resposta? {
        return feedbacksService.getResposta(id)
    }

    fun removeFeedback(id: String) {
        feedbacksService.removeFeedback(id)
    }

    fun removeModelo(id: String) {
        feedbacksService.removeModelo(id)
    }

    fun removePregunta(id: String) {
        feedbacksService.removePregunta(id)
    }

    fun removePosibleResposta(id: String) {
        feedbacksService.removePosibleResposta(id)
    }

    fun removePosiblesRespostas(id: String) {
        feedbacksService.removePosiblesRespostas(id)
    }

    fun removeResposta(id: String) {
        feedbacksService.removeResposta(id)
    }

    fun updateResposta(updateResposta: Feedback, resposta: Resposta, posibleResposta: PosibleResposta) {
        feedbacksService.updateResposta(updateResposta, resposta, posibleResposta)
    }

    fun updateCovered(feedbacks: RealmResults<Feedback>) {
        feedbacksService.updateCovered(feedbacks)
    }

    fun updateCovered(feedback: Feedback) {
        feedbacksService.updateCovered(feedback)
    }

    fun initRespostas(feedback: Feedback) {
        feedbacksService.initRespostas(feedback)
    }

    fun send(feedback: Feedback) {
        feedbacksService.send(feedback)
    }

    fun saveLocal(feedback: Feedback) {
        feedbacksService.saveLocal(feedback)
    }

    fun saveLocal(modelo: Modelo) {
        feedbacksService.saveLocal(modelo)
    }

    fun saveLocal(pregunta: Pregunta) {
        feedbacksService.saveLocal(pregunta)
    }

    fun saveLocal(posibleResposta: PosibleResposta) {
        feedbacksService.saveLocal(posibleResposta)
    }

    fun saveLocal(posiblesRespostas: PosiblesRespostas) {
        feedbacksService.saveLocal(posiblesRespostas)
    }

    fun saveLocal(resposta: Resposta) {
        feedbacksService.saveLocal(resposta)
    }

    fun save(feedback: Feedback) {
        feedbacksService.save(feedback)
    }

    fun save(resposta: Resposta) {
        feedbacksService.save(resposta)
    }

}