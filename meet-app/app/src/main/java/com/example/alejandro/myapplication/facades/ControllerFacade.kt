package com.example.alejandro.myapplication.facades

import android.content.Context
import com.example.alejandro.myapplication.controllers.calendar.CalendarController
import com.example.alejandro.myapplication.controllers.chat.ChatController
import com.example.alejandro.myapplication.controllers.configuration.ConfigurationsController
import com.example.alejandro.myapplication.controllers.feedback.FeedbacksController
import com.example.alejandro.myapplication.controllers.notifications.Notifications
import com.example.alejandro.myapplication.controllers.notifications.NotificationsController
import com.example.alejandro.myapplication.controllers.patientdata.PatientDataController
import com.example.alejandro.myapplication.model.calendar.Evento
import com.example.alejandro.myapplication.model.chat.Mensaxe
import com.example.alejandro.myapplication.model.configuration.Configuracion
import com.example.alejandro.myapplication.model.feedback.*
import com.example.alejandro.myapplication.model.notifications.Notificacion
import com.example.alejandro.myapplication.model.patientdata.Etapa
import com.example.alejandro.myapplication.model.patientdata.FichaPaciente
import io.realm.RealmResults
import java.util.*

class ControllerFacade(val context: Context) {

    //Notifications
    private val notifications: Notifications = Notifications(this)

    //DatabaseFacade
    private val databaseFacade: DatabaseFacade = DatabaseFacade(context, this)

    //Controllers
    private val patientDataController: PatientDataController = PatientDataController(databaseFacade)
    private val notificationsController: NotificationsController = NotificationsController(databaseFacade)
    private val calendarController: CalendarController = CalendarController(databaseFacade)
    private val chatController: ChatController = ChatController(databaseFacade)
    private val configurationsController: ConfigurationsController = ConfigurationsController(databaseFacade)
    private val feedbacksController: FeedbacksController = FeedbacksController(databaseFacade)

    //Init
    fun init(){
        //FIXME
        notifications.initNotifications()
        databaseFacade.initListeners()
    }

    //Functions Notifications
    fun notifications(notificacion: Notificacion){
        notifications.notifications(context, notificacion)
    }

    fun dismissNotificationsChat() {
        notifications.dismissNotificationsChat()
    }

    fun dismissNotifications(id: Int) {
        notifications.dismissNotifications(id)
    }

    //Functions PatientData
    fun getFichaPaciente(): FichaPaciente? {
        return patientDataController.getFichaPaciente()
    }

    fun getAllEtapas(): RealmResults<Etapa> {
        return patientDataController.getAllEtapas()
    }

    fun getEtapa(id: String): Etapa? {
        return patientDataController.getEtapa(id)
    }

    fun getLastEtapa(): Etapa? {
        return patientDataController.getLastEtapa()
    }

    fun getEtapaFichaPaciente(num: Int): Etapa? {
        return patientDataController.getEtapaFichaPaciente(num)
    }

    fun getNumEtapas(): Int {
        return patientDataController.getNumEtapas()
    }

    fun getNumEtapaActual(): Int {
        return patientDataController.getNumEtapaActual()
    }

    fun getEtapaActual(): Etapa? {
        return patientDataController.getEtapaActual()
    }

    fun removeFichaPaciente(id: String) {
        patientDataController.removeFichaPaciente(id)
    }

    fun removeEtapa(id: String) {
        patientDataController.removeEtapa(id)
    }

    fun saveLocal(fichaPaciente: FichaPaciente) {
        patientDataController.saveLocal(fichaPaciente)
    }

    fun saveLocal(etapa: Etapa) {
        patientDataController.saveLocal(etapa)
    }

    //Functions Notifications
    fun getAllNotificacions(): RealmResults<Notificacion> {
        return notificationsController.getAllNotificacions()
    }

    fun getPendingNotificacions(): RealmResults<Notificacion> {
        return notificationsController.getPendingNotificacions()
    }

    fun getNumPendingNotificacions(): Long {
        return notificationsController.getNumPendingNotificacions()
    }

    fun getNotificacion(id: String): Notificacion? {
        return notificationsController.getNotificacion(id)
    }

    fun getNotificacion(notificacionId: Int): Notificacion? {
        return notificationsController.getNotificacion(notificacionId)
    }

    fun removeNotificacion(id: String) {
        notificationsController.removeNotificacion(id)
    }

    fun updateNotificationSent(notificacionId: Int, notificacion: Notificacion) {
        notificationsController.updateNotificationSent(notificacionId, notificacion)
    }

    fun toggleSent(notificacion: Notificacion) {
        notificationsController.toggleSent(notificacion)
    }

    fun saveLocal(notificacion: Notificacion) {
        notificationsController.saveLocal(notificacion)
    }

    fun save(notificacion: Notificacion) {
        notificationsController.save(notificacion)
    }

    //Functions Calendario
    fun getAllEventos(): RealmResults<Evento> {
        return calendarController.getAllEventos()
    }

    fun getEventosData(date: Date): RealmResults<Evento> {
        return calendarController.getEventosData(date)
    }

    fun getEvento(id: String): Evento? {
        return calendarController.getEvento(id)
    }

    fun removeEvento(id: String) {
        calendarController.removeEvento(id)
    }

    fun saveLocal(evento: Evento) {
        calendarController.saveLocal(evento)
    }

    fun save(evento: Evento) {
        calendarController.save(evento)
    }

    //Functions Chat
    fun getAllMensaxes(): RealmResults<Mensaxe> {
        return chatController.getAllMensaxes()
    }

    fun getPendingMensaxes(): RealmResults<Mensaxe> {
        return chatController.getPendingMensaxes()
    }

    fun getNumPendingMensaxes(): Long {
        return chatController.getNumPendingMensaxes()
    }

    fun getMensaxe(id: String): Mensaxe? {
        return chatController.getMensaxe(id)
    }

    fun removeMensaxe(id: String) {
        chatController.removeMensaxe(id)
    }

    fun updateDataEnvio(mensaxe: Mensaxe) {
        chatController.updateDataEnvio(mensaxe)
    }

    fun updateDataLectura(mensaxe: Mensaxe) {
        chatController.updateDataLectura(mensaxe)
    }

    fun saveLocal(mensaxe: Mensaxe) {
        chatController.saveLocal(mensaxe)
    }

    fun save(mensaxe: Mensaxe) {
        chatController.save(mensaxe)
    }

    //Functions Configurations
    fun getAllConfigurations(): RealmResults<Configuracion> {
        return configurationsController.getAllConfigurations()
    }

    fun getConfiguration(id: String): Configuracion? {
        return configurationsController.getConfiguration(id)
    }

    fun removeConfiguracion(id: String) {
        configurationsController.removeConfiguracion(id)
    }

    fun getNotificationId(notificacion: Notificacion): Int {
        return configurationsController.getNotificationId(notificacion)
    }

    fun updateId(configuracion: Configuracion?, id: String?) {
        return configurationsController.updateId(configuracion, id)
    }

    fun saveLocal(configuracion: Configuracion) {
        configurationsController.saveLocal(configuracion)
    }

    fun save(configuracion: Configuracion) {
        configurationsController.save(configuracion)
    }

    //Functions Feedbacks
    fun getAllFeedbacks(): RealmResults<Feedback> {
        return feedbacksController.getAllFeedbacks()
    }

    fun getFeedback(id: String): Feedback? {
        return feedbacksController.getFeedback(id)
    }

    fun getFeedbacksFromModelo(id: String): RealmResults<Feedback> {
        return feedbacksController.getFeedbacksFromModelo(id)
    }

    fun getModelo(id: String): Modelo? {
        return feedbacksController.getModelo(id)
    }

    fun getPregunta(id: String): Pregunta? {
        return feedbacksController.getPregunta(id)
    }

    fun getPreguntasFromPosiblesRespostas(id: String): RealmResults<Pregunta> {
        return feedbacksController.getPreguntasFromPosiblesRespostas(id)
    }

    fun getRespostasFromPregunta(id: String): RealmResults<Resposta> {
        return feedbacksController.getRespostasFromPregunta(id)
    }

    fun getPosibleResposta(id: String): PosibleResposta? {
        return feedbacksController.getPosibleResposta(id)
    }

    fun getPosiblesRespostas(id: String): PosiblesRespostas? {
        return feedbacksController.getPosiblesRespostas(id)
    }

    fun getResposta(id: String): Resposta? {
        return feedbacksController.getResposta(id)
    }

    fun removeFeedback(id: String) {
        feedbacksController.removeFeedback(id)
    }

    fun removeModelo(id: String) {
        feedbacksController.removeModelo(id)
    }

    fun removePregunta(id: String) {
        feedbacksController.removePregunta(id)
    }

    fun removePosibleResposta(id: String) {
        feedbacksController.removePosibleResposta(id)
    }

    fun removePosiblesRespostas(id: String) {
        feedbacksController.removePosiblesRespostas(id)
    }

    fun removeResposta(id: String) {
        feedbacksController.removeResposta(id)
    }

    fun updateResposta(updateResposta: Feedback, resposta: Resposta, posibleResposta: PosibleResposta) {
        feedbacksController.updateResposta(updateResposta, resposta, posibleResposta)
    }

    fun updateCovered(feedbacks: RealmResults<Feedback>) {
        feedbacksController.updateCovered(feedbacks)
    }

    fun updateCovered(feedback: Feedback) {
        feedbacksController.updateCovered(feedback)
    }

    fun initRespostas(feedback: Feedback) {
        feedbacksController.initRespostas(feedback)
    }

    fun send(feedback: Feedback) {
        feedbacksController.send(feedback)
    }

    fun saveLocal(feedback: Feedback) {
        feedbacksController.saveLocal(feedback)
    }

    fun saveLocal(modelo: Modelo) {
        feedbacksController.saveLocal(modelo)
    }

    fun saveLocal(pregunta: Pregunta) {
        feedbacksController.saveLocal(pregunta)
    }

    fun saveLocal(posibleResposta: PosibleResposta) {
        feedbacksController.saveLocal(posibleResposta)
    }

    fun saveLocal(posiblesRespostas: PosiblesRespostas) {
        feedbacksController.saveLocal(posiblesRespostas)
    }

    fun saveLocal(resposta: Resposta) {
        feedbacksController.saveLocal(resposta)
    }

    fun save(feedback: Feedback) {
        feedbacksController.save(feedback)
    }

    fun save(resposta: Resposta) {
        feedbacksController.save(resposta)
    }

}