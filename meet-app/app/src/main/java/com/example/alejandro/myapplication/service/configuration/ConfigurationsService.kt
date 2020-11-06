package com.example.alejandro.myapplication.service.configuration

import com.example.alejandro.myapplication.model.configuration.Configuracion
import com.example.alejandro.myapplication.model.notifications.Notificacion
import io.realm.RealmResults

interface ConfigurationsService {

    fun getAllConfigurations(): RealmResults<Configuracion>

    fun getConfiguration(id: String): Configuracion?

    fun removeConfiguracion(id: String)

    fun getNotificationId(notificacion: Notificacion): Int

    fun updateId(configuracion: Configuracion?, id: String?)

    fun saveLocal(configuracion: Configuracion)

    fun save(configuracion: Configuracion)

}