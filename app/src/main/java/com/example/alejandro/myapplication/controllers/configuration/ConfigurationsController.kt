package com.example.alejandro.myapplication.controllers.configuration

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.configuration.Configuracion
import com.example.alejandro.myapplication.model.notifications.Notificacion
import com.example.alejandro.myapplication.service.configuration.ConfigurationsService
import com.example.alejandro.myapplication.service.configuration.ConfigurationsServiceImpl
import io.realm.RealmResults

// Controlador do paquete Configurations

class ConfigurationsController(databaseFacade: DatabaseFacade) {

    //Service
    private val configurationsService: ConfigurationsService = ConfigurationsServiceImpl(databaseFacade)

    //Functions
    fun getAllConfigurations(): RealmResults<Configuracion> {
        return configurationsService.getAllConfigurations()
    }

    fun getConfiguration(id: String): Configuracion? {
        return configurationsService.getConfiguration(id)
    }

    fun removeConfiguracion(id: String) {
        configurationsService.removeConfiguracion(id)
    }

    fun getNotificationId(notificacion: Notificacion): Int {
        return configurationsService.getNotificationId(notificacion)
    }

    fun updateId(configuracion: Configuracion?, id: String?) {
        return configurationsService.updateId(configuracion, id)
    }

    fun saveLocal(configuracion: Configuracion) {
        configurationsService.saveLocal(configuracion)
    }

    fun save(configuracion: Configuracion) {
        configurationsService.save(configuracion)
    }
}