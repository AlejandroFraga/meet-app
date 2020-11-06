package com.example.alejandro.myapplication.listeners.feedback

import android.content.Intent
import android.view.View
import com.example.alejandro.myapplication.activities.feedback.FeedbackActivity
import com.example.alejandro.myapplication.utils.ID_FEEDBACK_DB

class FeedbackOnClickListener(private val idFeedback: String) : View.OnClickListener {

    override fun onClick(view: View) {
        val intent = Intent(view.context, FeedbackActivity::class.java)
        intent.putExtra(ID_FEEDBACK_DB, idFeedback)
        view.context.startActivity(intent)
    }
}