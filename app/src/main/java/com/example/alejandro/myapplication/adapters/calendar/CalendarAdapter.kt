package com.example.alejandro.myapplication.adapters.calendar

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.activities.calendar.CalendarActivity
import com.example.alejandro.myapplication.listeners.calendar.CalendarOnClickListener
import com.example.alejandro.myapplication.utils.formatRange
import com.github.sundeepk.compactcalendarview.domain.Event
import java.util.*

// Adapter da lista de eventos do almanaque

class CalendarAdapter(private val context: CalendarActivity) : RecyclerView.Adapter<CalendarAdapter.EventViewHolder>() {

    val locale: Locale = context.resources.configuration.locale!!

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var events: List<Event>? = null

    inner class EventViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val evento: CardView = itemView.findViewById(R.id.calendar_event)
        val titulo: TextView = itemView.findViewById(R.id.calendar_event_titulo)
        val data: TextView = itemView.findViewById(R.id.calendar_event_data)
        val ubicacion: TextView = itemView.findViewById(R.id.calendar_event_ubicacion)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = inflater.inflate(R.layout.calendar_event, parent, false)
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events!![position]
        setInfoBasica(holder, event)
    }

    private fun setInfoBasica(holder: EventViewHolder, event: Event) {
        val evento = context.controllerFacade.getEvento(event.data.toString())

        if (evento != null) {
            holder.evento.setOnClickListener(CalendarOnClickListener(evento.id))
            holder.titulo.text = evento.titulo
            holder.data.text = formatRange(evento.dataComezo, evento.dataFin, locale)
            holder.ubicacion.text = evento.ubicacion
        }
    }

    fun setEvents(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (events != null)
            events!!.size
        else
            0
    }
}