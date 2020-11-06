package com.example.alejandro.myapplication.adapters.notifications

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.activities.notifications.NotificationsActivity
import com.example.alejandro.myapplication.listeners.notifications.NotificationOnClickListener
import com.example.alejandro.myapplication.model.notifications.Notificacion
import com.example.alejandro.myapplication.utils.formatCompleteDate
import java.util.*

// Adapter da lista de notificacións

class NotificationAdapter(private val context: NotificationsActivity) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    private val locale: Locale = context.resources.configuration.locale!!

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notificacions: MutableList<Notificacion>? = ArrayList()

    inner class NotificationViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val seen: ImageView = itemView.findViewById(R.id.logout_warning_icon)
        val title: TextView = itemView.findViewById(R.id.logout_warning_title)
        val text: TextView = itemView.findViewById(R.id.logout_warning_text)
        val data: TextView = itemView.findViewById(R.id.notification_notification_data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemView = inflater.inflate(R.layout.notification, parent, false)
        return NotificationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notificacions!![position]
        setInfoBasica(holder, notification)
    }

    private fun setInfoBasica(holder: NotificationViewHolder, notificacion: Notificacion) {
        if (!notificacion.enviada) {
            holder.seen.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.unseen))
        } else {
            holder.seen.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.seen))
        }
        holder.seen.setOnClickListener(NotificationOnClickListener(notificacion, context, holder.seen))
        holder.title.text = notificacion.bigTitle
        holder.text.text = notificacion.bigText
        holder.data.text = formatCompleteDate(notificacion.data, locale)
    }

    fun setNotificacions(notificacions: MutableList<Notificacion>) {
        this.notificacions = notificacions
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (notificacions != null)
            notificacions!!.size
        else
            0
    }
}