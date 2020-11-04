package com.example.alejandro.myapplication.service.chat

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.chat.Mensaxe
import com.example.alejandro.myapplication.model.feedback.Feedback
import com.example.alejandro.myapplication.utils.MENSAXE_DB
import com.example.alejandro.myapplication.utils.getUserCollectionReference
import com.example.alejandro.myapplication.utils.getUserDocumentReference
import com.example.alejandro.myapplication.utils.toMap
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.Sort
import java.util.*


class ChatServiceImpl(private val databaseFacade: DatabaseFacade) : ChatService {

    //Functions
    override fun getAllMensaxes(): RealmResults<Mensaxe> {
        return databaseFacade.getAllMensaxes()
    }

    override fun getPendingMensaxes(): RealmResults<Mensaxe> {
        return databaseFacade.getPendingMensaxes()
    }

    override fun getNumPendingMensaxes(): Long {
        return databaseFacade.getNumPendingMensaxes()
    }

    override fun getMensaxe(id: String): Mensaxe? {
        return databaseFacade.getMensaxe(id)
    }

    override fun removeMensaxe(id: String) {
        databaseFacade.removeMensaxe(id)
    }

    override fun updateDataEnvio(mensaxe: Mensaxe) {
        databaseFacade.updateDataEnvio(mensaxe)
    }

    override fun updateDataLectura(mensaxe: Mensaxe) {
        databaseFacade.updateDataLectura(mensaxe)
    }

    override fun saveLocal(mensaxe: Mensaxe) {
        databaseFacade.saveLocal(mensaxe)
    }

    override fun save(mensaxe: Mensaxe) {
        databaseFacade.save(mensaxe)
    }
}