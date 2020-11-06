package com.example.alejandro.myapplication.activities.patientdata

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import com.example.alejandro.myapplication.BaseActivity
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.facades.ControllerFacade
import com.example.alejandro.myapplication.listeners.patientdata.StatesOnSeekBarChangeListener
import com.example.alejandro.myapplication.model.patientdata.FichaPaciente
import com.example.alejandro.myapplication.service.patientdata.PatientDataServiceImpl
import com.example.alejandro.myapplication.utils.*
import kotlinx.android.synthetic.main.activity_patient_data.*
import java.util.*

//Actividade de ficha do paciente

class PatientDataActivity : BaseActivity() {

    val controllerFacade: ControllerFacade = ControllerFacade(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_data)

        setDatosVista()
    }

    // Función que introduce na vista os datos do paciente
    private fun setDatosVista() {

        setDatosPaciente()

        val patientDataProgressTimeText: TextView = patient_data_progress_time_text
        val patientDataProgressTime: ProgressBar = patient_data_progress_time

        val patientDataActualState: TextView = patient_data_actual_state
        val patientDataStates: SeekBar = patient_data_states
        val patientDataStateTitle: TextView = patient_data_state_title
        val patientDataStateData: TextView = patient_data_state_data
        val patientDataStateDescription: TextView = patient_data_state_description

        val fichaPaciente: FichaPaciente? = controllerFacade.getFichaPaciente()
        if (fichaPaciente != null) {
            val size = fichaPaciente.etapas.size

            // Introducimos tódolos valores das etapas
            val primeiraEtapa = controllerFacade.getEtapaFichaPaciente(0)
            val ultimaEtapa = controllerFacade.getEtapaFichaPaciente(size - 1)
            if(primeiraEtapa != null && ultimaEtapa != null) {
                val duracionTratamento = ultimaEtapa.dataFin.time - primeiraEtapa.dataComezo.time
                val situacionActual = Date().time - primeiraEtapa.dataComezo.time
                val duracionTratamentoDias = (duracionTratamento / (1000*60*60*24)).toInt()
                val situacionActualDias = (situacionActual / (1000*60*60*24)).toInt()

                patientDataProgressTimeText.text = (getString(R.string.temporal_progress) + SPACE + OPEN_PAREN + situacionActualDias + BARRA + duracionTratamentoDias + SPACE + getString(R.string.days) + CLOSE_PAREN)
                patientDataProgressTime.max = duracionTratamentoDias
                patientDataProgressTime.progress = situacionActualDias
            }
            patientDataStates.max = if (size > 0) {
                size - 1
            } else {
                0
            }
            patientDataStates.progress = controllerFacade.getNumEtapaActual()
            patientDataStates.setOnSeekBarChangeListener(StatesOnSeekBarChangeListener(patientDataActualState,
                    patientDataStateTitle, patientDataStateDescription, patientDataStateData, controllerFacade, this))
        }

        val numEtapaActual = controllerFacade.getNumEtapaActual()
        patientDataActualState.text = (getString(R.string.actual_state) + SPACE + OPEN_PAREN + (numEtapaActual + 1) + BARRA + controllerFacade.getNumEtapas() + CLOSE_PAREN)

        val etapaActual = controllerFacade.getEtapaActual()
        if (etapaActual != null) {
            patientDataStateTitle.text = etapaActual.title
            patientDataStateData.text = formatCompleteDateRange(etapaActual.dataComezo, etapaActual.dataFin, resources.configuration.locale!!)
            patientDataStateDescription.text = etapaActual.descripcion
        }
    }

    // Función que introduce os datos do paciente
    private fun setDatosPaciente(){
        val patientDataName: TextView = patient_data_name
        val patientDataBorn: TextView = patient_data_born

        val fichaPaciente: FichaPaciente? = controllerFacade.getFichaPaciente()
        if (fichaPaciente != null) {
            patientDataName.text = fichaPaciente.nomeDePila
            patientDataBorn.text = formatDateYear(fichaPaciente.nacemento)
        }
    }
}
