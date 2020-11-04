package com.example.alejandro.myapplication.daos.chat

import com.example.alejandro.myapplication.model.chat.Mensaxe
import io.realm.RealmResults

interface ChatLocalDAO {

    fun getAllMensaxes(): RealmResults<Mensaxe>

    fun getPendingMensaxes(): RealmResults<Mensaxe>

    fun getNumPendingMensaxes(): Long

    fun getMensaxe(id: String): Mensaxe?

    fun removeMensaxe(id: String)

    fun updateId(mensaxe: Mensaxe?, id: String?)

    fun updateDataEnvio(mensaxe: Mensaxe)

    fun updateDataLectura(mensaxe: Mensaxe)

    fun save(mensaxe: Mensaxe)
}