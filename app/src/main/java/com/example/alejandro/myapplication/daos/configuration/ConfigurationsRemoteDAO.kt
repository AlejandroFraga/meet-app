package com.example.alejandro.myapplication.daos.configuration

import com.example.alejandro.myapplication.daos.RemoteDAO
import com.example.alejandro.myapplication.model.configuration.Configuracion

interface ConfigurationsRemoteDAO : RemoteDAO {

    fun save(configuracion: Configuracion)
}