package com.example.alejandro.myapplication.controllers.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.example.alejandro.myapplication.InitActivity
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.R.string.*
import com.example.alejandro.myapplication.facades.ControllerFacade
import com.example.alejandro.myapplication.model.notifications.Notificacion
import com.example.alejandro.myapplication.utils.NOTTFICACION_ID_CHAT

//Clase creada para o controlado das notificacións

class Notifications(private val controllerFacade: ControllerFacade) {

    private var notificationManager: NotificationManager? = null
    private var generalChannel: NotificationChannel? = null
    private var chatChannel: NotificationChannel? = null
    private var calendarChannel: NotificationChannel? = null
    private var feedbackChannel: NotificationChannel? = null
    private var patientdataChannel: NotificationChannel? = null

    fun initNotifications() {

        //Comprobamos que esteamos nunha versión actual de Android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager = controllerFacade.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            //Creación das distintas canles de notificacións
            generalChannel = NotificationChannel(getString(notifications_general_channel_id), getString(notifications_general_channel_name), importance).apply {
                description = getString(notifications_general_channel_description)
            }
            chatChannel = NotificationChannel(getString(notifications_chat_channel_id), getString(notifications_chat_channel_name), importance).apply {
                description = getString(notifications_chat_channel_description)
            }
            calendarChannel = NotificationChannel(getString(notifications_calendar_channel_id), getString(notifications_calendar_channel_name), importance).apply {
                description = getString(notifications_calendar_channel_description)
            }
            feedbackChannel = NotificationChannel(getString(notifications_feedback_channel_id), getString(notifications_feedback_channel_name), importance).apply {
                description = getString(notifications_feedback_channel_description)
            }
            patientdataChannel = NotificationChannel(getString(notifications_patientdata_channel_id), getString(notifications_patientdata_channel_name), importance).apply {
                description = getString(notifications_patientdata_channel_description)
            }

            //Rexistramos tódalas canles
            notificationManager?.createNotificationChannel(generalChannel)
            notificationManager?.createNotificationChannel(chatChannel)
            notificationManager?.createNotificationChannel(calendarChannel)
            notificationManager?.createNotificationChannel(feedbackChannel)
            notificationManager?.createNotificationChannel(patientdataChannel)

            //Limpamos as notificacións ao iniciar a aplicación
            dismissNotifications()
        }
    }

    private fun getString(id: Int): String? {
        return controllerFacade.context.getString(id)
    }

    //Cancelación de notificación
    fun dismissNotifications() {
        notificationManager?.cancelAll()
    }

    //Cancelación de notificación de chat
    fun dismissNotificationsChat() {
        notificationManager?.cancel(NOTTFICACION_ID_CHAT)
    }

    //Cancelación de notificación co id indicado
    fun dismissNotifications(id: Int) {
        notificationManager?.cancel(id)
    }

    //Función para o envio da notificacion introducida
    fun notifications(context: Context, notificacion: Notificacion) {
        val notificationId = controllerFacade.getNotificationId(notificacion)

        val notificacionAux = controllerFacade.getNotificacion(notificationId)
        if (notificacionAux != null) {
            notificacion.id = notificacionAux.id
        } else if (notificationId < 100) {
            controllerFacade.updateNotificationSent(notificationId, notificacion)
        }

        val notificacionsChannel: String? = getString(notifications_general_channel_id)

        if(notificacionsChannel != null) {
            val intent = Intent(context, InitActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

            var mBuilder = NotificationCompat.Builder(context, notificacionsChannel)
                    .setSmallIcon(R.drawable.ic_menu_send)
                    .setContentTitle(notificacion.title)
                    .setContentText(notificacion.text)
                    .setStyle(NotificationCompat.BigTextStyle().bigText(notificacion.bigText).setBigContentTitle(notificacion.bigTitle))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)

            with(NotificationManagerCompat.from(context)) {
                notify(notificationId, mBuilder.build())
            }
        }
    }
}