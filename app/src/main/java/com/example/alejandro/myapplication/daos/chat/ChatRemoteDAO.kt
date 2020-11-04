package com.example.alejandro.myapplication.daos.chat

import com.example.alejandro.myapplication.daos.RemoteDAO
import com.example.alejandro.myapplication.model.chat.Mensaxe

interface ChatRemoteDAO : RemoteDAO {

    fun save(mensaxe: Mensaxe)
}