package com.example.alejandro.myapplication.service.configuration

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.configuration.Configuracion
import com.example.alejandro.myapplication.model.notifications.Notificacion
import io.realm.RealmResults


class ConfigurationsServiceImpl(private val databaseFacade: DatabaseFacade) : ConfigurationsService {

    //Functions
    override fun getAllConfigurations(): RealmResults<Configuracion> {
        return databaseFacade.getAllConfigurations()
    }

    override fun getConfiguration(id: String): Configuracion? {
        return databaseFacade.getConfiguration(id)
    }

    override fun removeConfiguracion(id: String) {
        databaseFacade.removeConfiguracion(id)
    }

    override fun getNotificationId(notificacion: Notificacion): Int {
        return databaseFacade.getNotificationId(notificacion)
    }

    override fun updateId(configuracion: Configuracion?, id: String?) {
        return databaseFacade.updateId(configuracion, id)
    }

    override fun saveLocal(configuracion: Configuracion) {
        databaseFacade.saveLocal(configuracion)
    }

    override fun save(configuracion: Configuracion) {
        databaseFacade.save(configuracion)
    }
}