package com.example.alejandro.myapplication.service.patientdata

import com.example.alejandro.myapplication.model.patientdata.Etapa
import com.example.alejandro.myapplication.model.patientdata.FichaPaciente
import io.realm.RealmResults

interface PatientDataService {

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

    fun saveLocal(fichaPaciente: FichaPaciente)

    fun saveLocal(etapa: Etapa)
}