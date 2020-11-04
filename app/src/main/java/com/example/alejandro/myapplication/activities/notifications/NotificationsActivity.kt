package com.example.alejandro.myapplication.activities.notifications

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.alejandro.myapplication.BaseActivity
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.adapters.notifications.NotificationAdapter
import com.example.alejandro.myapplication.facades.ControllerFacade
import com.example.alejandro.myapplication.model.notifications.Notificacion
import io.realm.RealmResults

//Actividade de notificacions

class NotificationsActivity : BaseActivity() {

    val controllerFacade: ControllerFacade = ControllerFacade(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        initAdapter()
    }

    // Función que inicializa o adapter da lista de notificacións
    fun initAdapter() {
        val recyclerView: RecyclerView = findViewById(R.id.notifications_view)
        val notificationAdapter = NotificationAdapter(this)
        recyclerView.adapter = notificationAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val notificacions: RealmResults<Notificacion>? = controllerFacade.getAllNotificacions()
        if (notificacions != null) {
            notificationAdapter.setNotificacions(notificacions)
        }
    }
}
