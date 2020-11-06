package com.example.alejandro.myapplication.daos.feedback

import com.example.alejandro.myapplication.daos.RemoteDAO
import com.example.alejandro.myapplication.model.feedback.*

interface FeedbacksRemoteDAO : RemoteDAO {

    fun save(feedback: Feedback)

    fun save(resposta: Resposta)

}