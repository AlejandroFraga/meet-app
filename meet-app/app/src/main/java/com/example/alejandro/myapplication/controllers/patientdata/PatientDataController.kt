package com.example.alejandro.myapplication.controllers.patientdata

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.patientdata.Etapa
import com.example.alejandro.myapplication.model.patientdata.FichaPaciente
import com.example.alejandro.myapplication.service.patientdata.PatientDataService
import com.example.alejandro.myapplication.service.patientdata.PatientDataServiceImpl
import io.realm.RealmResults

// Controlador do paquete PatientData

class PatientDataController(databaseFacade: DatabaseFacade) {

    //Service
    private val patientDataService: PatientDataService = PatientDataServiceImpl(databaseFacade)

    //Functions
    fun getFichaPaciente(): FichaPaciente? {
        return patientDataService.getFichaPaciente()
    }

    fun getAllEtapas(): RealmResults<Etapa> {
        return patientDataService.getAllEtapas()
    }

    fun getEtapa(id: String): Etapa? {
        return patientDataService.getEtapa(id)
    }

    fun getLastEtapa(): Etapa? {
        return patientDataService.getLastEtapa()
    }

    fun getEtapaFichaPaciente(num: Int): Etapa? {
        return patientDataService.getEtapaFichaPaciente(num)
    }

    fun getNumEtapas(): Int {
        return patientDataService.getNumEtapas()
    }

    fun getNumEtapaActual(): Int {
        return patientDataService.getNumEtapaActual()
    }

    fun getEtapaActual(): Etapa? {
        return patientDataService.getEtapaActual()
    }

    fun removeFichaPaciente(id: String) {
        patientDataService.removeFichaPaciente(id)
    }

    fun removeEtapa(id: String) {
        patientDataService.removeEtapa(id)
    }

    fun saveLocal(fichaPaciente: FichaPaciente) {
        patientDataService.saveLocal(fichaPaciente)
    }

    fun saveLocal(etapa: Etapa) {
        patientDataService.saveLocal(etapa)
    }
}