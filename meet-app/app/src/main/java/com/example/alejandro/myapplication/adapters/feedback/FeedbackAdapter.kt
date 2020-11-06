package com.example.alejandro.myapplication.adapters.feedback

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.listeners.feedback.FeedbackOnClickListener
import com.example.alejandro.myapplication.model.feedback.Feedback
import com.example.alejandro.myapplication.utils.*
import java.util.*

// Adapter da lista de feedbacks

class FeedbackAdapter(private val context: Context) : RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>() {

    private val locale: Locale = context.resources.configuration.locale!!

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var feedbacks: MutableList<Feedback>? = ArrayList()

    inner class FeedbackViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val feedback: CardView = itemView.findViewById(R.id.feedback_card)
        val cuberto: ImageView = itemView.findViewById(R.id.feedback_cuberto_icon)
        val titulo: TextView = itemView.findViewById(R.id.feedback_title)
        val dataFin: TextView = itemView.findViewById(R.id.feedback_data_fin)
        val dataFinText: TextView = itemView.findViewById(R.id.feedback_data_fin_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackViewHolder {
        val itemView = inflater.inflate(R.layout.feedback, parent, false)
        return FeedbackViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedbackViewHolder, position: Int) {
        val feedback = feedbacks!![position]
        setInfoBasica(holder, feedback)
    }

    private fun setInfoBasica(holder: FeedbackViewHolder, feedback: Feedback) {
        holder.feedback.setOnClickListener(FeedbackOnClickListener(feedback.id))

        when (feedback.estado) {
            ESTADO_ENVIADO_DB -> {
                holder.cuberto.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.checked))
            }
            ESTADO_BORRADOR_DB -> {
                holder.cuberto.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.warning))
            }
            ESTADO_INICIADO_DB -> {
                holder.cuberto.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.unchecked))
            }
        }
        holder.titulo.text = feedback.title
        holder.dataFin.text = context.getString(R.string.limit_data) + DOUBLE_DOT
        holder.dataFinText.text = formatCompleteDate(feedback.dataFin, locale)
    }

    fun setFeedbacks(feedbacks: MutableList<Feedback>) {
        this.feedbacks = feedbacks
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (feedbacks != null)
            feedbacks!!.size
        else
            0
    }
}