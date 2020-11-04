package com.example.alejandro.myapplication.daos.configuration

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.configuration.Configuracion
import com.example.alejandro.myapplication.model.notifications.Notificacion
import com.example.alejandro.myapplication.utils.NOTIFICACION_ID_DB
import com.example.alejandro.myapplication.utils.NOTIFICACION_ID_DESCRIPCION_DB
import com.example.alejandro.myapplication.utils.toInt
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort

class ConfigurationsLocalDAOImpl(private val realm: Realm, private val databaseFacade: DatabaseFacade) : ConfigurationsLocalDAO {

    override fun getAllConfigurations(): RealmResults<Configuracion> {
        return realm.where(Configuracion::class.java).sort("id", Sort.ASCENDING).findAll()
    }

    override fun getConfiguration(id: String): Configuracion? {
        return realm.where(Configuracion::class.java).equalTo("id", id).findFirst()
    }

    override fun removeConfiguracion(id: String) {
        realm.beginTransaction()
        realm.where(Configuracion::class.java)?.equalTo("id", id)?.findAll()?.deleteFirstFromRealm()
        realm.commitTransaction()
    }

    override fun updateId(configuracion: Configuracion?, id: String?) {
        if (configuracion != null && id != null) {
            realm.beginTransaction()
            configuracion.id = id
            realm.commitTransaction()

            save(configuracion)
        }
    }

    override fun getNotificationId(notificacion: Notificacion): Int {
        var notificacionId: Int? = notificacion.notificacionId

        if (notificacionId == null) {

            var configuracion: Configuracion? = getConfiguration(NOTIFICACION_ID_DB)
            if (configuracion == null) {
                configuracion = Configuracion(NOTIFICACION_ID_DB, NOTIFICACION_ID_DESCRIPCION_DB, "0")
            }

            notificacionId = toInt(configuracion.valor)
            if (notificacionId == null || notificacionId >= 100) {
                notificacionId = 0
            }

            notificacionId += 1

            realm.beginTransaction()
            configuracion.valor = notificacionId.toString()
            realm.commitTransaction()

            realm.beginTransaction()
            notificacion.notificacionId = notificacionId
            realm.commitTransaction()

            databaseFacade.save(configuracion)
        }

        return notificacionId
    }

    override fun save(configuracion: Configuracion) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(configuracion)
        realm.commitTransaction()
    }
}