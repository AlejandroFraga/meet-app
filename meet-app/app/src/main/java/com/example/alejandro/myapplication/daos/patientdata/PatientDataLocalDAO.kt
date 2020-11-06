package com.example.alejandro.myapplication.daos.patientdata

import com.example.alejandro.myapplication.model.patientdata.Etapa
import com.example.alejandro.myapplication.model.patientdata.FichaPaciente
import io.realm.RealmResults

interface PatientDataLocalDAO {

    fun getFichaPaciente(): FichaPaciente?

    fun getAllEtapas(): RealmResults<Etapa>

    fun getEtapa(id: String): Etapa?

    fun getLastEtapa(): Etapa?

    fun getEtapaFichaPaciente(num: Int): Etapa?

    fun getNumEtapas(): Int

    fun getNumEtapaActual(): Int

    fun getEtapaActual(): Etapa?

    fun removeFichaPaciente(id: String)

    fun removeEtapa(id: String)

    fun updateId(fichaPaciente: FichaPaciente?, id: String?)

    fun save(fichaPaciente: FichaPaciente)

    fun updateId(etapa: Etapa?, id: String?)

    fun save(etapa: Etapa)
}