package com.example.alejandro.myapplication.activities.chat

import android.os.Bundle
import android.widget.ListView
import com.example.alejandro.myapplication.BaseActivity
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.adapters.chat.ChatAdapter
import com.example.alejandro.myapplication.facades.ControllerFacade
import com.example.alejandro.myapplication.model.chat.Mensaxe
import kotlinx.android.synthetic.main.chat_send_bar.*
import kotlinx.android.synthetic.main.fragment_chat.*

//Actividade de chat

class ChatActivity : BaseActivity() {

    val controllerFacade: ControllerFacade = ControllerFacade(this)

    private var messagesView: ListView? = null
    private var chatAdapter: ChatAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        controllerFacade.dismissNotificationsChat()

        setDatosVista()
    }

    //Inicializador da información en pantalla
    private fun setDatosVista() {
        floatingActionButton.setOnClickListener {
            sendMessage()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initAdapter()
    }

    //Inicializador do adapter
    fun initAdapter() {
        messagesView = messages_view
        chatAdapter = ChatAdapter(this, controllerFacade.getAllMensaxes())
        messagesView?.adapter = chatAdapter
    }

    //Función chamada para enviar a mensaxe escrita no chat_send_bar_text
    private fun sendMessage() {
        val mensaxeMensaxe = chat_send_bar_text.text.toString()

        if (mensaxeMensaxe.isNotEmpty()) {
            val mensaxe = Mensaxe(mensaxeMensaxe, true)
            controllerFacade.save(mensaxe)
            messagesView!!.setSelection(messagesView!!.getCount() - 1)
            chat_send_bar_text.text.clear()
        }
    }
}
