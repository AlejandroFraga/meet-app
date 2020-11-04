package com.example.alejandro.myapplication.listeners.patientdata

import android.content.Context
import android.widget.SeekBar
import android.widget.TextView
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.facades.ControllerFacade
import com.example.alejandro.myapplication.service.patientdata.PatientDataServiceImpl
import com.example.alejandro.myapplication.utils.*

class StatesOnSeekBarChangeListener(private val patientDataActualState: TextView, private val patientDataStateTitle: TextView,
                                    private val patientDataStateDescription: TextView, private val patientDataStateData: TextView,
                                    private val patientDataServiceImpl: ControllerFacade, private val context: Context) : SeekBar.OnSeekBarChangeListener {

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        val etapa = patientDataServiceImpl.getEtapaFichaPaciente(p1)
        if (etapa != null) {
            val numEtapaActual = patientDataServiceImpl.getNumEtapaActual()

            patientDataActualState.text = getActualState(p1, numEtapaActual)
            patientDataStateTitle.text = etapa.title
            patientDataStateData.text = formatCompleteDateRange(etapa.dataComezo, etapa.dataFin, context.resources.configuration.locale!!)
            patientDataStateDescription.text = etapa.descripcion
        }
    }

    private fun getActualState(p1: Int, numEtapaActual: Int): String {
        val strEnd = SPACE + OPEN_PAREN + (p1 + 1) + BARRA + patientDataServiceImpl.getNumEtapas() + CLOSE_PAREN
        if(p1 < numEtapaActual){
            return context.getString(R.string.previous_state) + strEnd
        } else if (p1 > numEtapaActual) {
            return context.getString(R.string.later_state) + strEnd
        } else {
            return context.getString(R.string.actual_state) + strEnd
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }
}