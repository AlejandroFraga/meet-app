package com.example.alejandro.myapplication.service.calendar

import com.example.alejandro.myapplication.model.calendar.Evento
import io.realm.RealmResults
import java.util.*

interface CalendarService {

    fun getAllEventos(): RealmResults<Evento>

    fun getEventosData(date: Date): RealmResults<Evento>

    fun getEvento(id: String): Evento?

    fun removeEvento(id: String)

    fun saveLocal(evento: Evento)

    fun save(evento: Evento)
}