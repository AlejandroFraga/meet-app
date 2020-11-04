package com.example.alejandro.myapplication.controllers.notifications

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.notifications.Notificacion
import com.example.alejandro.myapplication.service.notification.NotificationsService
import com.example.alejandro.myapplication.service.notification.NotificationsServiceImpl
import io.realm.RealmResults

// Controlador do paquete Notifications

class NotificationsController(databaseFacade: DatabaseFacade) {

    //Service
    private val notificationsService: NotificationsService = NotificationsServiceImpl(databaseFacade)

    //Functions
    fun getAllNotificacions(): RealmResults<Notificacion> {
        return notificationsService.getAllNotificacions()
    }

    fun getPendingNotificacions(): RealmResults<Notificacion> {
        return notificationsService.getPendingNotificacions()
    }

    fun getNumPendingNotificacions(): Long {
        return notificationsService.getNumPendingNotificacions()
    }

    fun getNotificacion(id: String): Notificacion? {
        return notificationsService.getNotificacion(id)
    }

    fun getNotificacion(notificacionId: Int): Notificacion? {
        return notificationsService.getNotificacion(notificacionId)
    }

    fun removeNotificacion(id: String) {
        notificationsService.removeNotificacion(id)
    }

    fun updateNotificationSent(notificacionId: Int, notificacion: Notificacion) {
        notificationsService.updateNotificationSent(notificacionId, notificacion)
    }

    fun toggleSent(notificacion: Notificacion) {
        notificationsService.toggleSent(notificacion)
    }

    fun saveLocal(notificacion: Notificacion) {
        notificationsService.saveLocal(notificacion)
    }

    fun save(notificacion: Notificacion) {
        notificationsService.save(notificacion)
    }
}