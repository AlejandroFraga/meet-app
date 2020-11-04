package com.example.alejandro.myapplication.facades

import android.content.Context
import android.content.Intent
import com.example.alejandro.myapplication.MainActivity
import com.example.alejandro.myapplication.daos.RemoteDAO
import com.example.alejandro.myapplication.daos.calendar.CalendarLocalDAO
import com.example.alejandro.myapplication.daos.calendar.CalendarLocalDAOImpl
import com.example.alejandro.myapplication.daos.calendar.CalendarRemoteDAO
import com.example.alejandro.myapplication.daos.calendar.CalendarRemoteDAOImpl
import com.example.alejandro.myapplication.daos.chat.ChatLocalDAO
import com.example.alejandro.myapplication.daos.chat.ChatLocalDAOImpl
import com.example.alejandro.myapplication.daos.chat.ChatRemoteDAO
import com.example.alejandro.myapplication.daos.chat.ChatRemoteDAOImpl
import com.example.alejandro.myapplication.daos.configuration.ConfigurationsLocalDAO
import com.example.alejandro.myapplication.daos.configuration.ConfigurationsLocalDAOImpl
import com.example.alejandro.myapplication.daos.configuration.ConfigurationsRemoteDAO
import com.example.alejandro.myapplication.daos.configuration.ConfigurationsRemoteDAOImpl
import com.example.alejandro.myapplication.daos.feedback.FeedbacksLocalDAO
import com.example.alejandro.myapplication.daos.feedback.FeedbacksLocalDAOImpl
import com.example.alejandro.myapplication.daos.feedback.FeedbacksRemoteDAO
import com.example.alejandro.myapplication.daos.feedback.FeedbacksRemoteDAOImpl
import com.example.alejandro.myapplication.daos.notifications.NotificationsLocalDAO
import com.example.alejandro.myapplication.daos.notifications.NotificationsLocalDAOImpl
import com.example.alejandro.myapplication.daos.notifications.NotificationsRemoteDAO
import com.example.alejandro.myapplication.daos.notifications.NotificationsRemoteDAOImpl
import com.example.alejandro.myapplication.daos.patientdata.PatientDataLocalDAO
import com.example.alejandro.myapplication.daos.patientdata.PatientDataLocalDAOImpl
import com.example.alejandro.myapplication.daos.patientdata.PatientDataRemoteDAO
import com.example.alejandro.myapplication.daos.patientdata.PatientDataRemoteDAOImpl
import com.example.alejandro.myapplication.model.calendar.Evento
import com.example.alejandro.myapplication.model.chat.Mensaxe
import com.example.alejandro.myapplication.model.configuration.Configuracion
import com.example.alejandro.myapplication.model.feedback.*
import com.example.alejandro.myapplication.model.notifications.Notificacion
import com.example.alejandro.myapplication.model.patientdata.Etapa
import com.example.alejandro.myapplication.model.patientdata.FichaPaciente
import io.realm.Realm
import io.realm.RealmResults
import java.util.*

class DatabaseFacade(val context: Context, private val controllerFacade: ControllerFacade) {

    //Realm
    private val realm: Realm = Realm.getDefaultInstance()

    //Local DAOs
    private val patientDataLocalDAO: PatientDataLocalDAO = PatientDataLocalDAOImpl(realm)
    private val notificationsLocalDAO: NotificationsLocalDAO = NotificationsLocalDAOImpl(realm, this)
    private val calendarLocalDAO: CalendarLocalDAO = CalendarLocalDAOImpl(realm)
    private val chatLocalDAO: ChatLocalDAO = ChatLocalDAOImpl(realm, this)
    private val configurationsLocalDAO: ConfigurationsLocalDAO = ConfigurationsLocalDAOImpl(realm, this)
    private val feedbacksLocalDAO: FeedbacksLocalDAO = FeedbacksLocalDAOImpl(realm, this)

    //Remote DAOs
    private val patientDataRemoteDAO: PatientDataRemoteDAO = PatientDataRemoteDAOImpl(this)
    private val notificationsRemoteDAO: NotificationsRemoteDAO = NotificationsRemoteDAOImpl(this)
    private val calendarRemoteDAO: CalendarRemoteDAO = CalendarRemoteDAOImpl(this)
    private val chatRemoteDAO: ChatRemoteDAO = ChatRemoteDAOImpl(this)
    private val configurationsRemoteDAO: ConfigurationsRemoteDAO = ConfigurationsRemoteDAOImpl(this)
    private val feedbacksRemoteDAO: FeedbacksRemoteDAO = FeedbacksRemoteDAOImpl(this)

    private val remoteDAOs: Array<RemoteDAO> = arrayOf(patientDataRemoteDAO, notificationsRemoteDAO, calendarRemoteDAO, chatRemoteDAO, configurationsRemoteDAO, feedbacksRemoteDAO)

    fun initListeners() {
        if(remoteDAOs.isNotEmpty()){
            remoteDAOs[0].initListeners(remoteDAOs.drop(1))
        }
    }

    //ControllerFacade
    fun notifications(notificacion: Notificacion){
        controllerFacade.notifications(notificacion)
    }

    fun dismissNotificationsChat() {
        controllerFacade.dismissNotificationsChat()
    }

    fun dismissNotifications(id: Int) {
        controllerFacade.dismissNotifications(id)
    }

    //Local DAOs Public Functions

    //Patientdata
    fun getFichaPaciente(): FichaPaciente? {
        return patientDataLocalDAO.getFichaPaciente()
    }

    fun getAllEtapas(): RealmResults<Etapa> {
        return patientDataLocalDAO.getAllEtapas()
    }

    fun getEtapa(id: String): Etapa? {
        return patientDataLocalDAO.getEtapa(id)
    }

    fun getLastEtapa(): Etapa? {
        return patientDataLocalDAO.getLastEtapa()
    }

    fun getEtapaFichaPaciente(num: Int): Etapa? {
        return patientDataLocalDAO.getEtapaFichaPaciente(num)
    }

    fun getNumEtapas(): Int {
        return patientDataLocalDAO.getNumEtapas()
    }

    fun getNumEtapaActual(): Int {
        return patientDataLocalDAO.getNumEtapaActual()
    }

    fun getEtapaActual(): Etapa? {
        return patientDataLocalDAO.getEtapaActual()
    }

    fun removeFichaPaciente(id: String) {
        patientDataLocalDAO.removeFichaPaciente(id)
    }

    fun removeEtapa(id: String) {
        patientDataLocalDAO.removeEtapa(id)
    }

    fun updateId(fichaPaciente: FichaPaciente?, id: String?) {
        patientDataLocalDAO.updateId(fichaPaciente, id)
    }

    fun updateId(etapa: Etapa?, id: String?) {
        patientDataLocalDAO.updateId(etapa, id)
    }

    fun saveLocal(fichaPaciente: FichaPaciente) {
        patientDataLocalDAO.save(fichaPaciente)
    }

    fun saveLocal(etapa: Etapa) {
        patientDataLocalDAO.save(etapa)
    }

    //Notifications
    fun getAllNotificacions(): RealmResults<Notificacion> {
        return notificationsLocalDAO.getAllNotificacions()
    }

    fun getPendingNotificacions(): RealmResults<Notificacion> {
        return notificationsLocalDAO.getPendingNotificacions()
    }

    fun getNumPendingNotificacions(): Long {
        return notificationsLocalDAO.getNumPendingNotificacions()
    }

    fun getNotificacion(id: String): Notificacion? {
        return notificationsLocalDAO.getNotificacion(id)
    }

    fun getNotificacion(notificacionId: Int): Notificacion? {
        return notificationsLocalDAO.getNotificacion(notificacionId)
    }

    fun removeNotificacion(id: String) {
        notificationsLocalDAO.removeNotificacion(id)
    }

    fun updateId(notificacion: Notificacion?, id: String?) {
        notificationsLocalDAO.updateId(notificacion, id)
    }

    fun updateNotificationSent(notificacionId: Int, notificacion: Notificacion) {
        notificationsLocalDAO.updateNotificationSent(notificacionId, notificacion)
    }

    fun toggleSent(notificacion: Notificacion) {
        notificationsLocalDAO.toggleSent(notificacion)
    }

    fun saveLocal(notificacion: Notificacion) {
        notificationsLocalDAO.save(notificacion)
    }

    //Calendar
    fun getAllEventos(): RealmResults<Evento> {
        return calendarLocalDAO.getAllEventos()
    }

    fun getEventosData(date: Date): RealmResults<Evento> {
        return calendarLocalDAO.getEventosData(date)
    }

    fun getEvento(id: String): Evento? {
        return calendarLocalDAO.getEvento(id)
    }

    fun removeEvento(id: String) {
        calendarLocalDAO.removeEvento(id)
    }

    fun updateId(evento: Evento?, id: String?) {
        calendarLocalDAO.updateId(evento, id)
    }

    fun saveLocal(evento: Evento) {
        calendarLocalDAO.save(evento)
    }

    //Chat
    fun getAllMensaxes(): RealmResults<Mensaxe> {
        return chatLocalDAO.getAllMensaxes()
    }

    fun getPendingMensaxes(): RealmResults<Mensaxe> {
        return chatLocalDAO.getPendingMensaxes()
    }

    fun getNumPendingMensaxes(): Long {
        return chatLocalDAO.getNumPendingMensaxes()
    }

    fun getMensaxe(id: String): Mensaxe? {
        return chatLocalDAO.getMensaxe(id)
    }

    fun removeMensaxe(id: String) {
        return chatLocalDAO.removeMensaxe(id)
    }

    fun updateId(mensaxe: Mensaxe?, id: String?) {
        chatLocalDAO.updateId(mensaxe, id)
    }

    fun updateDataEnvio(mensaxe: Mensaxe) {
        return chatLocalDAO.updateDataEnvio(mensaxe)
    }

    fun updateDataLectura(mensaxe: Mensaxe) {
        return chatLocalDAO.updateDataLectura(mensaxe)
    }

    fun saveLocal(mensaxe: Mensaxe) {
        return chatLocalDAO.save(mensaxe)
    }

    //Configurations
    fun getAllConfigurations(): RealmResults<Configuracion> {
        return configurationsLocalDAO.getAllConfigurations()
    }

    fun getConfiguration(id: String): Configuracion? {
        return configurationsLocalDAO.getConfiguration(id)
    }

    fun removeConfiguracion(id: String) {
        configurationsLocalDAO.removeConfiguracion(id)
    }

    fun getNotificationId(notificacion: Notificacion): Int {
        return configurationsLocalDAO.getNotificationId(notificacion)
    }

    fun updateId(configuracion: Configuracion?, id: String?) {
        return configurationsLocalDAO.updateId(configuracion, id)
    }

    fun saveLocal(configuracion: Configuracion) {
        configurationsLocalDAO.save(configuracion)
    }

    //Feedbacks
    fun getAllFeedbacks(): RealmResults<Feedback> {
        return feedbacksLocalDAO.getAllFeedbacks()
    }

    fun getFeedback(id: String): Feedback? {
        return feedbacksLocalDAO.getFeedback(id)
    }

    fun getFeedbacksFromModelo(id: String): RealmResults<Feedback> {
        return feedbacksLocalDAO.getFeedbacksFromModelo(id)
    }

    fun getModelo(id: String): Modelo? {
        return feedbacksLocalDAO.getModelo(id)
    }

    fun getPregunta(id: String): Pregunta? {
        return feedbacksLocalDAO.getPregunta(id)
    }

    fun getPreguntasFromPosiblesRespostas(id: String): RealmResults<Pregunta> {
        return feedbacksLocalDAO.getPreguntasFromPosiblesRespostas(id)
    }

    fun getRespostasFromPregunta(id: String): RealmResults<Resposta> {
        return feedbacksLocalDAO.getRespostasFromPregunta(id)
    }

    fun getPosibleResposta(id: String): PosibleResposta? {
        return feedbacksLocalDAO.getPosibleResposta(id)
    }

    fun getPosiblesRespostas(id: String): PosiblesRespostas? {
        return feedbacksLocalDAO.getPosiblesRespostas(id)
    }

    fun getResposta(id: String): Resposta? {
        return feedbacksLocalDAO.getResposta(id)
    }

    fun removeFeedback(id: String) {
        feedbacksLocalDAO.removeFeedback(id)
    }

    fun removeModelo(id: String) {
        feedbacksLocalDAO.removeModelo(id)
    }

    fun removePregunta(id: String) {
        feedbacksLocalDAO.removePregunta(id)
    }

    fun removePosibleResposta(id: String) {
        feedbacksLocalDAO.removePosibleResposta(id)
    }

    fun removePosiblesRespostas(id: String) {
        feedbacksLocalDAO.removePosiblesRespostas(id)
    }

    fun removeResposta(id: String) {
        feedbacksLocalDAO.removeResposta(id)
    }

    fun updateId(feedback: Feedback?, id: String?) {
        feedbacksLocalDAO.updateId(feedback, id)
    }

    fun updateId(resposta: Resposta?, id: String?) {
        feedbacksLocalDAO.updateId(resposta, id)
    }

    fun updateResposta(updateResposta: Feedback, resposta: Resposta, posibleResposta: PosibleResposta) {
        feedbacksLocalDAO.updateResposta(updateResposta, resposta, posibleResposta)
    }

    fun updateCovered(feedbacks: RealmResults<Feedback>) {
        feedbacksLocalDAO.updateCovered(feedbacks)
    }

    fun updateCovered(feedback: Feedback) {
        feedbacksLocalDAO.updateCovered(feedback)
    }

    fun initRespostas(feedback: Feedback) {
        feedbacksLocalDAO.initRespostas(feedback)
    }

    fun send(feedback: Feedback) {
        feedbacksLocalDAO.send(feedback)
    }

    fun saveLocal(feedback: Feedback) {
        feedbacksLocalDAO.save(feedback)
    }

    fun saveLocal(modelo: Modelo) {
        feedbacksLocalDAO.save(modelo)
    }

    fun saveLocal(pregunta: Pregunta) {
        feedbacksLocalDAO.save(pregunta)
    }

    fun saveLocal(posibleResposta: PosibleResposta) {
        feedbacksLocalDAO.save(posibleResposta)
    }

    fun saveLocal(posiblesRespostas: PosiblesRespostas) {
        feedbacksLocalDAO.save(posiblesRespostas)
    }

    fun saveLocal(resposta: Resposta) {
        feedbacksLocalDAO.save(resposta)
    }

    //Remote DAOs Public Functions

    //Notification
    fun saveRemote(notificacion: Notificacion) {
        notificationsRemoteDAO.save(notificacion)
    }

    //Calendar
    fun saveRemote(evento: Evento) {
        calendarRemoteDAO.save(evento)
    }

    //Chat
    fun saveRemote(mensaxe: Mensaxe) {
        chatRemoteDAO.save(mensaxe)
    }

    //Configurations
    fun saveRemote(configuracion: Configuracion) {
        configurationsRemoteDAO.save(configuracion)
    }

    //Feedbacks
    fun saveRemote(feedback: Feedback) {
        feedbacksRemoteDAO.save(feedback)
    }

    fun saveRemote(resposta: Resposta) {
        feedbacksRemoteDAO.save(resposta)
    }

    //Mixed DAOs Public Functions

    //Notifications
    fun save(notificacion: Notificacion) {
        saveRemote(notificacion)
//        saveLocal(notificacion)
    }

    //Calendar
    fun save(evento: Evento) {
        saveRemote(evento)
//        saveLocal(evento)
    }

    //Chat
    fun save(mensaxe: Mensaxe) {
        saveRemote(mensaxe)
//        saveLocal(mensaxe)
    }

    //Configurations
    fun save(configuracion: Configuracion) {
        saveRemote(configuracion)
//        saveLocal(configuracion)
    }

    //Feedbacks
    fun save(feedback: Feedback) {
        saveRemote(feedback)
//        saveLocal(feedback)
    }

    fun save(resposta: Resposta) {
        saveRemote(resposta)
//        saveLocal(resposta)
    }

    //Local DAOs Private Functions

    //Remote DAOs Private Functions

    //Mixed DAOs Private Functions

}