package com.example.alejandro.myapplication.listeners.feedback

import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.alejandro.myapplication.activities.feedback.FeedbackActivity
import com.example.alejandro.myapplication.facades.ControllerFacade
import com.example.alejandro.myapplication.model.feedback.Feedback
import com.example.alejandro.myapplication.model.feedback.Resposta


class RespostaOnCheckedChangeListener(private val context: FeedbackActivity, private val feedback: Feedback,
                                      private val resposta: Resposta, private val ids: List<String>) : RadioGroup.OnCheckedChangeListener {

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        if(p0 != null){
            val radioButton = p0.findViewById<RadioButton>(p1)
            val index = ids.indexOf(radioButton.id.toString())
            val posibleResposta = resposta.pregunta.posiblesRespostas.posibleRespostas[index]
            if(posibleResposta != null){
                context.controllerFacade.updateResposta(feedback, resposta, posibleResposta)
                context.setEstado(feedback.estado)
            }
        }
    }
}