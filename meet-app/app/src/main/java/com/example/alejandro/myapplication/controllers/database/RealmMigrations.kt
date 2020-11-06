package com.example.alejandro.myapplication.controllers.database

import io.realm.DynamicRealm
import io.realm.RealmMigration
import io.realm.RealmSchema
import java.util.*

// Clase encargada de controlar as migracións de BBDD Local

class RealmMigrations : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
//        val schema = realm.schema
//
//        if(oldVersion < 2L) {
//            val fichaPaciente = schema.get("FichaPaciente")
//            if (fichaPaciente != null) {
//                fichaPaciente.addField("nacemento", Date::class.java)
//            }
//        }
//
//        if (oldVersion < 3L) {
//            val feedback = schema.get("Feedback")
//            if (feedback != null) {
//                feedback.removeField("finalizado")
//                feedback.addField("estado", String::class.java)
//            }
//        }
    }
}