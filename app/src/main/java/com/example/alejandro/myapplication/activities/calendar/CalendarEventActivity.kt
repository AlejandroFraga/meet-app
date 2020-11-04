package com.example.alejandro.myapplication.activities.calendar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.alejandro.myapplication.BaseActivity
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.facades.ControllerFacade
import com.example.alejandro.myapplication.model.calendar.Evento
import com.example.alejandro.myapplication.utils.BLANK
import com.example.alejandro.myapplication.utils.ID_EVENTO_DB
import com.example.alejandro.myapplication.utils.formatCompleteDate
import com.example.alejandro.myapplication.utils.formatDateRange
import kotlinx.android.synthetic.main.fragment_calendar_event.*
import java.util.*

//Actividade dun evento do almanaque

class CalendarEventActivity : BaseActivity() {

    val controllerFacade: ControllerFacade = ControllerFacade(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_event)

        val evento: Evento? = getEventoFromExtra()
        if (evento != null) {
            setDatosVista(evento)
        } else {
            //FIXME
        }
    }

    //Recuperación do evento, a través do paso do seu id dentro do extra do intent
    private fun getEventoFromExtra(): Evento? {
        val idEvento = intent.getStringExtra(ID_EVENTO_DB)
        return controllerFacade.getEvento(idEvento)
    }

    //Inicializador da información en pantalla
    private fun setDatosVista(evento: Evento) {
        val locale: Locale = resources.configuration.locale!!

        patient_data_name.text = evento.titulo
        calendar_event_event_data.text = formatDateRange(evento.dataComezo, evento.dataFin, locale)
        patient_data_state_title.text = getString(R.string.location)
        patient_data_state_description.text = evento.ubicacion

        val dataNotificacion = formatCompleteDate(evento.dataNotificacion, locale)
        calendar_event_event_notificacion.text = when(dataNotificacion){
            BLANK -> getString(R.string.not_notification_configured)
            else -> dataNotificacion
        }
    }
}
