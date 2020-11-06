package com.example.alejandro.myapplication.service.calendar

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.calendar.Evento
import com.example.alejandro.myapplication.model.chat.Mensaxe
import com.example.alejandro.myapplication.utils.*
import com.google.firebase.firestore.DocumentReference
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.Sort
import java.util.*


class CalendarServiceImpl(private val databaseFacade: DatabaseFacade) : CalendarService {

    //Functions
    override fun getAllEventos(): RealmResults<Evento> {
        return databaseFacade.getAllEventos()
    }

    override fun getEventosData(date: Date): RealmResults<Evento> {
        return databaseFacade.getEventosData(date)
    }

    override fun getEvento(id: String): Evento? {
        return databaseFacade.getEvento(id)
    }

    override fun removeEvento(id: String) {
        databaseFacade.removeEvento(id)
    }

    override fun saveLocal(evento: Evento) {
        databaseFacade.saveLocal(evento)
    }

    override fun save(evento: Evento) {
        databaseFacade.save(evento)
    }
}