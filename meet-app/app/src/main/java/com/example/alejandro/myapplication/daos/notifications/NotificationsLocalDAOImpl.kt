package com.example.alejandro.myapplication.daos.notifications

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.notifications.Notificacion
import com.example.alejandro.myapplication.utils.DATA_DB
import com.example.alejandro.myapplication.utils.NOTIFICACION_ID_DB
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import java.util.*

class NotificationsLocalDAOImpl(private val realm: Realm, private val databaseFacade: DatabaseFacade) : NotificationsLocalDAO {

    override fun getAllNotificacions(): RealmResults<Notificacion> {
        return realm.where(Notificacion::class.java).lessThan(NOTIFICACION_ID_DB, 100).sort("data", Sort.ASCENDING).findAll()
    }

    override fun getPendingNotificacions(): RealmResults<Notificacion> {
        return realm.where(Notificacion::class.java).lessThanOrEqualTo(DATA_DB, Date()).equalTo("enviada", false).findAll()
    }

    override fun getNumPendingNotificacions(): Long {
        return realm.where(Notificacion::class.java).lessThanOrEqualTo(DATA_DB, Date()).equalTo("enviada", false).count()
    }

    override fun getNotificacion(id: String): Notificacion? {
        return realm.where(Notificacion::class.java).equalTo("id", id).findFirst()
    }

    override fun getNotificacion(notificacionId: Int): Notificacion? {
        return realm.where(Notificacion::class.java).equalTo(NOTIFICACION_ID_DB, notificacionId).findFirst()
    }

    override fun removeNotificacion(id: String) {
        realm.beginTransaction()
        realm.where(Notificacion::class.java).equalTo("id", id).findAll().deleteFirstFromRealm()
        realm.commitTransaction()
    }

    override fun updateId(notificacion: Notificacion?, id: String?) {
        if (notificacion != null && id != null) {
            realm.beginTransaction()
            notificacion.id = id
            realm.commitTransaction()

            save(notificacion)
        }
    }

    override fun save(notificacion: Notificacion) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(notificacion)
        realm.commitTransaction()
    }

    override fun updateNotificationSent(notificacionId: Int, notificacion: Notificacion) {
        realm.beginTransaction()
        notificacion.notificacionId = notificacionId
        //FIXME
//        notificacion.enviada = true
        //FIXME
//        notificacion.data = add1Hour(notificacion.data)
        realm.commitTransaction()

        databaseFacade.save(notificacion)
    }

    override fun toggleSent(notificacion: Notificacion) {
        realm.beginTransaction()
        notificacion.enviada = !notificacion.enviada
        realm.commitTransaction()

        databaseFacade.save(notificacion)
    }
}