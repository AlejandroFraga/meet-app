package com.example.alejandro.myapplication.daos.patientdata

import com.example.alejandro.myapplication.model.patientdata.Etapa
import com.example.alejandro.myapplication.model.patientdata.FichaPaciente
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import java.util.*

class PatientDataLocalDAOImpl(private val realm: Realm) : PatientDataLocalDAO {

    override fun getFichaPaciente(): FichaPaciente? {
        return realm.where(FichaPaciente::class.java).findFirst()
    }

    override fun getAllEtapas(): RealmResults<Etapa> {
        return realm.where(Etapa::class.java).sort("dataComezo", Sort.ASCENDING).findAll()
    }

    override fun getEtapa(id: String): Etapa? {
        return realm.where(Etapa::class.java).equalTo("id", id).findFirst()
    }

    override fun getLastEtapa(): Etapa? {
        return realm.where(Etapa::class.java).sort("dataComezo", Sort.DESCENDING).findFirst()
    }

    override fun getEtapaFichaPaciente(num: Int): Etapa? {
        //FIXME
        return realm.where(Etapa::class.java).sort("dataComezo", Sort.ASCENDING).findAll()[num]
//        val fichaPaciente = getFichaPaciente()
//        if (fichaPaciente != null) {
//
//            val etapas = fichaPaciente.etapas
//            if (etapas != null && etapas.size > num) {
//
//                realm.beginTransaction()
//                etapas.sort()
//                realm.commitTransaction()
//
//                return etapas[num]
//            }
//        }
//        return null
    }

    override fun getNumEtapas(): Int {
        //FIXME
        return realm.where(Etapa::class.java).count().toInt()
//        val fichaPaciente = getFichaPaciente()
//        if (fichaPaciente != null) {
//
//            val etapas = fichaPaciente.etapas
//            if (etapas != null) {
//                return etapas.size
//            }
//        }
//        return 0
    }

    override fun getNumEtapaActual(): Int {
//        val fichaPaciente = getFichaPaciente()
//        if (fichaPaciente != null) {

//            val etapas = fichaPaciente.etapas
            val etapas = getAllEtapas()
//            if (etapas != null) {

//                realm.beginTransaction()
//                etapas.sort()
//                realm.commitTransaction()

                val etapaActual = getEtapaActual()
                if (etapaActual != null) {
                    var posicion = 0
                    for (etapa in etapas) {
                        if (etapa.equals(etapaActual)) {
                            return posicion
                        }
                        posicion += 1
                    }
                }
//            }
//        }
        return 0
    }

    override fun getEtapaActual(): Etapa? {
        val agora = Date()
        return realm.where(Etapa::class.java).lessThanOrEqualTo("dataComezo", agora).greaterThanOrEqualTo("dataFin", agora).findFirst()
    }

    override fun removeFichaPaciente(id: String) {
        realm.beginTransaction()
        realm.where(FichaPaciente::class.java).equalTo("id", id).findAll().deleteFirstFromRealm()
        realm.commitTransaction()
    }

    override fun removeEtapa(id: String) {
        realm.where(Etapa::class.java).equalTo("id", id).findAll().deleteFirstFromRealm()
    }

    override fun updateId(fichaPaciente: FichaPaciente?, id: String?){
        if(fichaPaciente != null && id != null) {
            realm.beginTransaction()
            fichaPaciente.id = id
            realm.commitTransaction()

            save(fichaPaciente)
        }
    }

    override fun save(fichaPaciente: FichaPaciente) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(fichaPaciente)
        realm.commitTransaction()
    }

    override fun updateId(etapa: Etapa?, id: String?){
        if(etapa != null && id != null) {
            realm.beginTransaction()
            etapa.id = id
            realm.commitTransaction()

            save(etapa)
        }
    }

    override fun save(etapa: Etapa) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(etapa)
        realm.commitTransaction()
    }
}