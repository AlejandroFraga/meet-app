package com.example.alejandro.myapplication.activities.calendar

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.alejandro.myapplication.BaseActivity
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.adapters.calendar.CalendarAdapter
import com.example.alejandro.myapplication.facades.ControllerFacade
import com.example.alejandro.myapplication.utils.formatMonth
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.util.*

//Actividade do almanaque

class CalendarActivity : BaseActivity() {

    val controllerFacade: ControllerFacade = ControllerFacade(this)

    //Almanaque, indicador de mes e adaptar controlador da lista de eventos
    var compactCalendarView: CompactCalendarView? = null
    var calendarioMes: TextView? = null
    var eventosAdapter: CalendarAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        setDatosVista()
    }

    //Función que introduce os datos da vista
    private fun setDatosVista() {
        compactCalendarView = compactcalendar_view
        calendarioMes = calendario_mes

        initAdapter()

        val locale: Locale = resources.configuration.locale!!

        crearCalendario(locale)
    }

    //Iniciador do adapter da lista de eventos do calendario
    private fun initAdapter() {
        val recyclerView: RecyclerView = calendar_events
        eventosAdapter = CalendarAdapter(this)
        recyclerView.adapter = eventosAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    //Función para a creación do calendario
    private fun crearCalendario(locale: Locale) {
        val hoxe = Date()

        compactCalendarView?.setLocale(TimeZone.getDefault(), locale)

        setTextCalendario(formatMonth(hoxe, locale))
        compactCalendarView?.setCurrentDate(hoxe)

        initEventos()
        setEvents(Date())

        setListenerCalendario(locale)
    }

    //Cárganse no calendario os eventos da BBDD
    private fun initEventos() {
        val allEventos = controllerFacade.getAllEventos()
        for (evento in allEventos) {
            val cor = when (evento.cor) {
                "BLACK" -> Color.BLACK
                "BLUE" -> Color.BLUE
                "CYAN" -> Color.CYAN
                "DKGRAY" -> Color.DKGRAY
                "GRAY" -> Color.GRAY
                "GREEN" -> Color.GREEN
                "LTGRAY" -> Color.LTGRAY
                "MAGENTA" -> Color.MAGENTA
                "RED" -> Color.RED
                "WHITE" -> Color.WHITE
                "YELLOW" -> Color.YELLOW
                else -> Color.BLACK
            }

            compactCalendarView?.addEvent(Event(cor, evento.dataComezo.time, evento.id))
        }
    }

    //Engádense as funcionalidades ao facer scroll no mes ou seleccionar un dia correspondente
    private fun setListenerCalendario(locale: Locale) {

        compactCalendarView?.setListener(object : CompactCalendarView.CompactCalendarViewListener {

            override fun onDayClick(dateClicked: Date) {
                setEvents(dateClicked)
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
                setTextCalendario(formatMonth(firstDayOfNewMonth, locale))
                setEvents(firstDayOfNewMonth)
            }
        })
    }

    private fun setTextCalendario(text: String) {
        if (calendarioMes != null) {
            calendarioMes?.text = text
        }
    }

    //Función que pobla o adapter cos eventos recuperados do dia marcado
    private fun setEvents(date: Date) {
        val events: List<Event>? = compactCalendarView?.getEvents(date)
        val eventsAux: List<Event> = if (events != null) {
            events
        } else {
            ArrayList()
        }
        eventosAdapter?.setEvents(eventsAux)
    }
}
