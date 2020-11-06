package com.example.alejandro.myapplication.listeners.notifications

import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.activities.notifications.NotificationsActivity
import com.example.alejandro.myapplication.model.notifications.Notificacion

class NotificationOnClickListener(private val notificacion: Notificacion, private val context: NotificationsActivity, private val seen: ImageView) : View.OnClickListener {

    override fun onClick(view: View) {
        context.controllerFacade.toggleSent(notificacion)

        if (!notificacion.enviada) {
            seen.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.unseen))
        } else {
            seen.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.seen))
        }
    }
}