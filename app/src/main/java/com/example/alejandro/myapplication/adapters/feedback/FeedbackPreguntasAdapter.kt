package com.example.alejandro.myapplication.adapters.feedback

import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.activities.feedback.FeedbackActivity
import com.example.alejandro.myapplication.listeners.feedback.RespostaOnCheckedChangeListener
import com.example.alejandro.myapplication.model.feedback.Feedback
import com.example.alejandro.myapplication.model.feedback.Resposta
import com.example.alejandro.myapplication.utils.ESTADO_ENVIADO_DB
import io.realm.RealmList

// Adapter das preguntas dun feedback

class FeedbackPreguntasAdapter(private val context: FeedbackActivity, private val feedback: Feedback) : RecyclerView.Adapter<FeedbackPreguntasAdapter.FeedbackPreguntasViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var respostas: RealmList<Resposta>? = null

    inner class FeedbackPreguntasViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val enunciado: TextView = itemView.findViewById(R.id.pregunta_enunciado)
        val respostas: RadioGroup = itemView.findViewById(R.id.pregunta_posible_respostas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackPreguntasViewHolder {
        val itemView = inflater.inflate(R.layout.feedback_pregunta, parent, false)
        return FeedbackPreguntasViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedbackPreguntasViewHolder, position: Int) {
        val pregunta = respostas!![position]
        if (pregunta != null) {
            setInfoBasica(holder, pregunta)
        }
    }

    private fun setInfoBasica(holder: FeedbackPreguntasViewHolder, resposta: Resposta) {
        holder.enunciado.text = resposta.pregunta.enunciado
        setRadioButtons(holder.respostas, resposta)
    }

    fun setRespostas(respostas: RealmList<Resposta>) {
        this.respostas = respostas
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (respostas != null)
            respostas!!.size
        else
            0
    }

    //FIXME
    private fun setRadioButtons(respostas: RadioGroup, resposta: Resposta) {
        val list = ArrayList<String>()

        val ll = RadioGroup(context)
        ll.orientation = LinearLayout.VERTICAL
        val pregunta = resposta.pregunta
        val posiblesRespostas = pregunta?.posiblesRespostas
        val posibleRespostas = posiblesRespostas?.posibleRespostas

        if (posibleRespostas != null) {
            for (posibleResposta in posibleRespostas) {
                val rdbtn = RadioButton(context)
                rdbtn.id = View.generateViewId()
                list.add(rdbtn.id.toString())
                rdbtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.text3))
                rdbtn.text = posibleResposta.representacion
                rdbtn.isChecked = posibleResposta.id == resposta.posibleRespostaId
                rdbtn.isEnabled = feedback.estado != ESTADO_ENVIADO_DB
                ll.addView(rdbtn)
            }
        }

        ll.setOnCheckedChangeListener(RespostaOnCheckedChangeListener(context, feedback, resposta, list))

        respostas.addView(ll)
    }
}