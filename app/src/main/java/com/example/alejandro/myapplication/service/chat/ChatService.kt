package com.example.alejandro.myapplication.service.chat

import com.example.alejandro.myapplication.model.chat.Mensaxe
import io.realm.RealmResults

interface ChatService {

    fun getAllMensaxes(): RealmResults<Mensaxe>

    fun getPendingMensaxes(): RealmResults<Mensaxe>

    fun getNumPendingMensaxes(): Long

    fun getMensaxe(id: String): Mensaxe?

    fun removeMensaxe(id: String)

    fun updateDataEnvio(mensaxe: Mensaxe)

    fun updateDataLectura(mensaxe: Mensaxe)

    fun saveLocal(mensaxe: Mensaxe)

    fun save(mensaxe: Mensaxe)
}