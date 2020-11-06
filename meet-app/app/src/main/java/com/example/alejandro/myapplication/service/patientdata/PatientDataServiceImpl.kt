package com.example.alejandro.myapplication.service.patientdata

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.patientdata.Etapa
import com.example.alejandro.myapplication.model.patientdata.FichaPaciente
import io.realm.RealmResults


class PatientDataServiceImpl(private val databaseFacade: DatabaseFacade) : PatientDataService {

    //Functions
    override fun getFichaPaciente(): FichaPaciente? {
        return databaseFacade.getFichaPaciente()
    }

    override fun getAllEtapas(): RealmResults<Etapa> {
        return databaseFacade.getAllEtapas()
    }

    override fun getEtapa(id: String): Etapa? {
        return databaseFacade.getEtapa(id)
    }

    override fun getLastEtapa(): Etapa? {
        return databaseFacade.getLastEtapa()
    }

    override fun getEtapaFichaPaciente(num: Int): Etapa? {
        return databaseFacade.getEtapaFichaPaciente(num)
    }

    override fun getNumEtapas(): Int {
        return databaseFacade.getNumEtapas()
    }

    override fun getNumEtapaActual(): Int {
        return databaseFacade.getNumEtapaActual()
    }

    override fun getEtapaActual(): Etapa? {
        return databaseFacade.getEtapaActual()
    }

    override fun removeFichaPaciente(id: String) {
        databaseFacade.removeFichaPaciente(id)
    }

    override fun removeEtapa(id: String) {
        databaseFacade.removeEtapa(id)
    }

    override fun saveLocal(fichaPaciente: FichaPaciente) {
        databaseFacade.saveLocal(fichaPaciente)
    }

    override fun saveLocal(etapa: Etapa) {
        databaseFacade.saveLocal(etapa)
    }
}