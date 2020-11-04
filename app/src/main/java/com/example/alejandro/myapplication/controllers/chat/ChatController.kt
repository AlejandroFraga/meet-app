package com.example.alejandro.myapplication.controllers.chat

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.chat.Mensaxe
import com.example.alejandro.myapplication.service.chat.ChatService
import com.example.alejandro.myapplication.service.chat.ChatServiceImpl
import io.realm.RealmResults

// Controlador do paquete Chat

class ChatController(databaseFacade: DatabaseFacade) {

    //Service
    private val chatService: ChatService = ChatServiceImpl(databaseFacade)

    //Functions
    fun getAllMensaxes(): RealmResults<Mensaxe> {
        return chatService.getAllMensaxes()
    }

    fun getPendingMensaxes(): RealmResults<Mensaxe> {
        return chatService.getPendingMensaxes()
    }

    fun getNumPendingMensaxes(): Long {
        return chatService.getNumPendingMensaxes()
    }

    fun getMensaxe(id: String): Mensaxe? {
        return chatService.getMensaxe(id)
    }

    fun removeMensaxe(id: String) {
        chatService.removeMensaxe(id)
    }

    fun updateDataEnvio(mensaxe: Mensaxe) {
        chatService.updateDataEnvio(mensaxe)
    }

    fun updateDataLectura(mensaxe: Mensaxe) {
        chatService.updateDataLectura(mensaxe)
    }

    fun saveLocal(mensaxe: Mensaxe) {
        chatService.saveLocal(mensaxe)
    }

    fun save(mensaxe: Mensaxe) {
        chatService.save(mensaxe)
    }

}