package com.example.alejandro.myapplication.daos.calendar

import com.example.alejandro.myapplication.model.calendar.Evento
import io.realm.RealmResults
import java.util.*

interface CalendarLocalDAO {

    fun getAllEventos(): RealmResults<Evento>

    fun getEventosData(date: Date): RealmResults<Evento>

    fun getEvento(id: String): Evento?

    fun removeEvento(id: String)

    fun updateId(evento: Evento?, id: String?)

    fun save(evento: Evento)

}