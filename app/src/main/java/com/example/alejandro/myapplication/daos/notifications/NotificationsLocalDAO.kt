package com.example.alejandro.myapplication.daos.notifications

import com.example.alejandro.myapplication.model.notifications.Notificacion
import io.realm.RealmResults

interface NotificationsLocalDAO {

    fun getAllNotificacions(): RealmResults<Notificacion>

    fun getPendingNotificacions(): RealmResults<Notificacion>

    fun getNumPendingNotificacions(): Long

    fun getNotificacion(id: String): Notificacion?

    fun getNotificacion(notificacionId: Int): Notificacion?

    fun removeNotificacion(id: String)

    fun updateId(notificacion: Notificacion?, id: String?)

    fun save(notificacion: Notificacion)

    fun updateNotificationSent(notificacionId: Int, notificacion: Notificacion)

    fun toggleSent(notificacion: Notificacion)

}