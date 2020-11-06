package com.example.alejandro.myapplication.daos.notifications

import com.example.alejandro.myapplication.daos.RemoteDAO
import com.example.alejandro.myapplication.model.notifications.Notificacion

interface NotificationsRemoteDAO : RemoteDAO {

    fun save(notificacion: Notificacion)

}