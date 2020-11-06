package com.example.alejandro.myapplication.activities.feedback

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.alejandro.myapplication.BaseActivity
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.adapters.feedback.FeedbackAdapter
import com.example.alejandro.myapplication.facades.ControllerFacade
import com.example.alejandro.myapplication.model.feedback.Feedback
import io.realm.RealmResults

//Actividade de feedbacks

class ListaFeedbacksActivity : BaseActivity() {

    val controllerFacade: ControllerFacade = ControllerFacade(this)

    private var feedbackAdapter: FeedbackAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_feedbacks)

        initAdapter()
    }

    //Inicializador do adapter que controlará a lista de feedbacks
    fun initAdapter() {
        val recyclerView: RecyclerView = findViewById(R.id.feedback_view)
        feedbackAdapter = FeedbackAdapter(this)
        recyclerView.adapter = feedbackAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        updateFeedbacks()
    }

    //Función que actualiza a información dos feedbacks mostrada por pantalla
    private fun updateFeedbacks() {
        val feedbacks: RealmResults<Feedback>? = controllerFacade.getAllFeedbacks()
        if (feedbacks != null) {
            controllerFacade.updateCovered(feedbacks)
            feedbackAdapter?.setFeedbacks(feedbacks)
        }
    }

    //A información actualizase en caso de reiniciar esta actividade
    override fun onRestart() {
        super.onRestart()
        updateFeedbacks()
    }

    //A información actualizase en caso de continuar esta actividade
    override fun onResume() {
        super.onResume()
        updateFeedbacks()
    }
}
