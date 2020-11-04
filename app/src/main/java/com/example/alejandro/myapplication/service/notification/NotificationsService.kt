package com.example.alejandro.myapplication.service.notification

import com.example.alejandro.myapplication.model.notifications.Notificacion
import io.realm.RealmResults

interface NotificationsService {

    fun getAllNotificacions(): RealmResults<Notificacion>

    fun getPendingNotificacions(): RealmResults<Notificacion>

    fun getNumPendingNotificacions(): Long

    fun getNotificacion(id: String): Notificacion?

    fun getNotificacion(notificacionId: Int): Notificacion?

    fun removeNotificacion(id: String)

    fun updateNotificationSent(notificacionId: Int, notificacion: Notificacion)

    fun toggleSent(notificacion: Notificacion)

    fun saveLocal(notificacion: Notificacion)

    fun save(notificacion: Notificacion)
}