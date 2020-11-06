package com.example.alejandro.myapplication.daos.configuration

import com.example.alejandro.myapplication.model.configuration.Configuracion
import com.example.alejandro.myapplication.model.notifications.Notificacion
import io.realm.RealmResults

interface ConfigurationsLocalDAO {

    fun getAllConfigurations(): RealmResults<Configuracion>

    fun getConfiguration(id: String): Configuracion?

    fun removeConfiguracion(id: String)

    fun updateId(configuracion: Configuracion?, id: String?)

    fun getNotificationId(notificacion: Notificacion): Int

    fun save(configuracion: Configuracion)
}