package com.example.alejandro.myapplication.listeners.calendar

import android.content.Intent
import android.view.View
import com.example.alejandro.myapplication.activities.calendar.CalendarEventActivity
import com.example.alejandro.myapplication.utils.ID_EVENTO_DB

class CalendarOnClickListener(_idEvento: String) : View.OnClickListener {

    private val idEvento: String = _idEvento

    override fun onClick(view: View) {
        val intent = Intent(view.context, CalendarEventActivity::class.java)
        intent.putExtra(ID_EVENTO_DB, idEvento)
        view.context.startActivity(intent)
    }
}