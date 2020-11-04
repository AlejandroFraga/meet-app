package com.example.alejandro.myapplication.controllers.calendar

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.calendar.Evento
import com.example.alejandro.myapplication.service.calendar.CalendarServiceImpl
import io.realm.RealmResults
import java.util.*

// Controlador do paquete Calendar

class CalendarController(databaseFacade: DatabaseFacade) {

    //Service
    private val calendarServiceImpl: CalendarServiceImpl = CalendarServiceImpl(databaseFacade)

    //Functions
    fun getAllEventos(): RealmResults<Evento> {
        return calendarServiceImpl.getAllEventos()
    }

    fun getEventosData(date: Date): RealmResults<Evento> {
        return calendarServiceImpl.getEventosData(date)
    }

    fun getEvento(id: String): Evento? {
        return calendarServiceImpl.getEvento(id)
    }

    fun removeEvento(id: String) {
        calendarServiceImpl.removeEvento(id)
    }

    fun saveLocal(evento: Evento) {
        calendarServiceImpl.saveLocal(evento)
    }

    fun save(evento: Evento) {
        calendarServiceImpl.save(evento)
    }
}