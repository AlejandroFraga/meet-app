package com.example.alejandro.myapplication.service.notification

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.notifications.Notificacion
import io.realm.RealmResults


class NotificationsServiceImpl(private val databaseFacade: DatabaseFacade) : NotificationsService {

    //Functions
    override fun getAllNotificacions(): RealmResults<Notificacion> {
        return databaseFacade.getAllNotificacions()
    }

    override fun getPendingNotificacions(): RealmResults<Notificacion> {
        return databaseFacade.getPendingNotificacions()
    }

    override fun getNumPendingNotificacions(): Long {
        return databaseFacade.getNumPendingNotificacions()
    }

    override fun getNotificacion(id: String): Notificacion? {
        return databaseFacade.getNotificacion(id)
    }

    override fun getNotificacion(notificacionId: Int): Notificacion? {
        return databaseFacade.getNotificacion(notificacionId)
    }

    override fun removeNotificacion(id: String) {
        databaseFacade.removeNotificacion(id)
    }

    override fun updateNotificationSent(notificacionId: Int, notificacion: Notificacion) {
        databaseFacade.updateNotificationSent(notificacionId, notificacion)
    }

    override fun toggleSent(notificacion: Notificacion) {
        databaseFacade.toggleSent(notificacion)
    }

    override fun saveLocal(notificacion: Notificacion) {
        databaseFacade.saveLocal(notificacion)
    }

    override fun save(notificacion: Notificacion) {
        databaseFacade.save(notificacion)
    }

}