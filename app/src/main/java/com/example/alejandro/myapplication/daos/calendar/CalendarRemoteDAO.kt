package com.example.alejandro.myapplication.daos.calendar

import com.example.alejandro.myapplication.daos.RemoteDAO
import com.example.alejandro.myapplication.model.calendar.Evento

interface CalendarRemoteDAO : RemoteDAO {

    fun save(evento: Evento)

}