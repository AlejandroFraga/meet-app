package com.example.alejandro.myapplication.daos.calendar

import com.example.alejandro.myapplication.model.calendar.Evento
import com.example.alejandro.myapplication.utils.add1Day
import com.example.alejandro.myapplication.utils.getOnlyDate
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import java.util.*

class CalendarLocalDAOImpl(private val realm: Realm) : CalendarLocalDAO {

    override fun getAllEventos(): RealmResults<Evento> {
        return realm.where(Evento::class.java).sort("dataComezo", Sort.ASCENDING).findAll()
    }

    override fun getEventosData(date: Date): RealmResults<Evento> {
        val onlyDate1: Date = getOnlyDate(date)
        val onlyDate2 = add1Day(onlyDate1)
        return realm.where(Evento::class.java).between("dataComezo", onlyDate1, onlyDate2).sort("dataComezo", Sort.ASCENDING).findAll()
    }

    override fun getEvento(id: String): Evento? {
        return realm.where(Evento::class.java).equalTo("id", id).findFirst()
    }

    override fun removeEvento(id: String) {
        realm.beginTransaction()
        realm.where(Evento::class.java)?.equalTo("id", id)?.findAll()?.deleteFirstFromRealm()
        realm.commitTransaction()
    }

    override fun updateId(evento: Evento?, id: String?) {
        if (evento != null && id != null) {
            realm.beginTransaction()
            evento.id = id
            realm.commitTransaction()

            save(evento)
        }
    }

    override fun save(evento: Evento) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(evento)
        realm.commitTransaction()
    }
}