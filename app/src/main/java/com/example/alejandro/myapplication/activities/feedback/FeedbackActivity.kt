package com.example.alejandro.myapplication.activities.feedback

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.alejandro.myapplication.BaseActivity
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.adapters.feedback.FeedbackPreguntasAdapter
import com.example.alejandro.myapplication.facades.ControllerFacade
import com.example.alejandro.myapplication.model.feedback.Feedback
import com.example.alejandro.myapplication.utils.ESTADO_BORRADOR_DB
import com.example.alejandro.myapplication.utils.ESTADO_ENVIADO_DB
import com.example.alejandro.myapplication.utils.ESTADO_INICIADO_DB
import com.example.alejandro.myapplication.utils.ID_FEEDBACK_DB

//Actividade de feedback

class FeedbackActivity : BaseActivity() {

    val controllerFacade: ControllerFacade = ControllerFacade(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val feedback: Feedback? = getFeedbackFromExtra()
        if (feedback != null) {
            setDatosVista(feedback)
        } else {
            //FIXME
        }
    }

    //Recuperación do feedback, a través do paso do seu id dentro do extra do intent
    private fun getFeedbackFromExtra(): Feedback? {
        val idFeedback = intent.getStringExtra(ID_FEEDBACK_DB)
        return controllerFacade.getFeedback(idFeedback)
    }

    //Inicializador da información en pantalla
    private fun setDatosVista(feedback: Feedback) {
        val feedbackTituloTV: TextView = findViewById(R.id.feedback_title)
        feedbackTituloTV.text = feedback.title

        controllerFacade.updateCovered(feedback)
        setEstado(feedback.estado)
        initFeedbackPreguntasAdapter(feedback)

        setSendAction(feedback)
    }

    //Inicializador do adapter que controlará as preguntas do feedback
    private fun initFeedbackPreguntasAdapter(feedback: Feedback) {
        val recyclerView: RecyclerView = findViewById(R.id.feedback_preguntas)
        val feedbackPreguntasAdapter = FeedbackPreguntasAdapter(this, feedback)
        recyclerView.adapter = feedbackPreguntasAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        feedbackPreguntasAdapter.setRespostas(feedback.respostas)
    }

    //Setter da información do feedback pasandolle o estado actual do mesmo
    fun setEstado(estado: String) {
        val cubertoText: TextView = findViewById(R.id.feedback_cuberto_text)
        val cubertoIcon: ImageView = findViewById(R.id.feedback_cuberto_icon)
        val feedbackSend: Button = findViewById(R.id.feedback_send)

        when (estado) {
            ESTADO_ENVIADO_DB -> {
                feedbackSend.isEnabled = false
                cubertoText.text = getString(R.string.covered)
                cubertoIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.checked))
            }
            ESTADO_BORRADOR_DB -> {
                feedbackSend.isEnabled = true
                cubertoText.text = getString(R.string.draft)
                cubertoIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.warning))
            }
            ESTADO_INICIADO_DB -> {
                feedbackSend.isEnabled = false
                cubertoText.text = getString(R.string.not_covered)
                cubertoIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.unchecked))
            }
        }
    }

    //Función que se chamará cando se queira enviar o feedback finalizado
    fun setSendAction(feedback: Feedback){
        val feedbackSend: Button = findViewById(R.id.feedback_send)

        feedbackSend.setOnClickListener {
            controllerFacade.send(feedback)
            finish()
        }
    }
}
