package com.example.alejandro.myapplication.adapters.chat

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import com.example.alejandro.myapplication.R
import com.example.alejandro.myapplication.activities.chat.ChatActivity
import com.example.alejandro.myapplication.model.chat.Mensaxe
import com.example.alejandro.myapplication.utils.formatHM24H
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter

// Adapter das mensaxes do chat

class ChatAdapter(private val context: ChatActivity, data: OrderedRealmCollection<Mensaxe>?) : RealmBaseAdapter<Mensaxe>(data), ListAdapter {

    override fun getItem(i: Int): Mensaxe? {
        return adapterData?.get(i)
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, _convertView: View?, viewGroup: ViewGroup?): View? {
        var convertView = _convertView
        val holder = MessageViewHolder()
        val messageInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val mensaxe = getItem(i)

        if (mensaxe != null) {
            if (mensaxe.marcarLida()) {
                context.controllerFacade.updateDataLectura(mensaxe)
            }

            convertView = messageInflater.inflate(if (mensaxe.ePropia) {
                R.layout.chat_my_message
            } else {
                R.layout.chat_their_message
            }, null)

            setHolderViews(holder, convertView)
            convertView.tag = holder
            setHolderViewsTexts(holder, mensaxe)
        }

        return convertView
    }

    private fun setHolderViews(holder: MessageViewHolder, convertView: View) {
        holder.messageBody = convertView.findViewById(R.id.chat_mensaxe_mensaxe)
        holder.dataCreacion = convertView.findViewById(R.id.chat_mensaxe_data_creacion)
    }

    private fun setHolderViewsTexts(holder: MessageViewHolder, mensaxe: Mensaxe) {
        holder.messageBody?.text = mensaxe.mensaxe
        holder.dataCreacion?.text = formatHM24H(mensaxe.dataCreacion)
    }

    internal class MessageViewHolder {
        var messageBody: TextView? = null
        var dataCreacion: TextView? = null
    }
}