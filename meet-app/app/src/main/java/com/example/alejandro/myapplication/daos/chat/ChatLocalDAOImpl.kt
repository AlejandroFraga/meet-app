package com.example.alejandro.myapplication.daos.chat

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.chat.Mensaxe
import com.example.alejandro.myapplication.model.feedback.Feedback
import com.example.alejandro.myapplication.utils.DATA_CREACION_DB
import com.example.alejandro.myapplication.utils.DATA_LECTURA_DB
import com.example.alejandro.myapplication.utils.E_PROPIA_DB
import com.example.alejandro.myapplication.utils.ID_DB
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import java.util.*

class ChatLocalDAOImpl(private val realm: Realm, private val databaseFacade: DatabaseFacade) : ChatLocalDAO {

    override fun getAllMensaxes(): RealmResults<Mensaxe> {
        return realm.where(Mensaxe::class.java).sort(DATA_CREACION_DB, Sort.ASCENDING).findAllAsync()
    }

    override fun getPendingMensaxes(): RealmResults<Mensaxe> {
        return realm.where(Mensaxe::class.java).isNull(DATA_LECTURA_DB).equalTo(E_PROPIA_DB, false).findAll()
    }

    override fun getNumPendingMensaxes(): Long {
        return realm.where(Mensaxe::class.java).isNull(DATA_LECTURA_DB).equalTo(E_PROPIA_DB, false).count()
    }

    override fun getMensaxe(id: String): Mensaxe? {
        return realm.where(Mensaxe::class.java).equalTo(ID_DB, id).findFirst()
    }

    override fun removeMensaxe(id: String) {
        realm.beginTransaction()
        realm.where(Feedback::class.java)?.equalTo(ID_DB, id)?.findAll()?.deleteFirstFromRealm()
        realm.commitTransaction()
    }

    override fun updateId(mensaxe: Mensaxe?, id: String?){
        if(mensaxe != null && id != null) {
            realm.beginTransaction()
            mensaxe.id = id
            realm.commitTransaction()

            save(mensaxe)
        }
    }

    override fun updateDataEnvio(mensaxe: Mensaxe) {
        if(mensaxe.ePropia && mensaxe.dataEnvio == null) {
            realm.beginTransaction()
            mensaxe.dataEnvio = Date()
            realm.commitTransaction()

            //FIXME
//            databaseFacade.save(mensaxe)
        }
    }

    override fun updateDataLectura(mensaxe: Mensaxe) {
        if(!mensaxe.ePropia && mensaxe.dataLectura == null) {
            realm.beginTransaction()
            mensaxe.dataLectura = Date()
            realm.commitTransaction()

            databaseFacade.save(mensaxe)
        }
    }

    override fun save(mensaxe: Mensaxe) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(mensaxe)
        realm.commitTransaction()
    }
}