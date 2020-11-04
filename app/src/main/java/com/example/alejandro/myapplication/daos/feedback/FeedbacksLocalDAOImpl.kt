package com.example.alejandro.myapplication.daos.feedback

import com.example.alejandro.myapplication.facades.DatabaseFacade
import com.example.alejandro.myapplication.model.feedback.*
import com.example.alejandro.myapplication.utils.ESTADO_BORRADOR_DB
import com.example.alejandro.myapplication.utils.ESTADO_ENVIADO_DB
import com.example.alejandro.myapplication.utils.ESTADO_INICIADO_DB
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import io.realm.Sort

class FeedbacksLocalDAOImpl(private val realm: Realm, private val databaseFacade: DatabaseFacade) : FeedbacksLocalDAO {

    override fun getAllFeedbacks(): RealmResults<Feedback> {
        val orders: Array<String> = arrayOf("estado", "dataFin")
        val sorts: Array<Sort> = arrayOf(Sort.ASCENDING, Sort.ASCENDING)

        return realm.where(Feedback::class.java).sort(orders, sorts).findAll()
    }

    override fun getFeedback(id: String): Feedback? {
        return realm.where(Feedback::class.java).equalTo("id", id).findFirst()
    }

    override fun getFeedbacksFromModelo(id: String): RealmResults<Feedback> {
        return realm.where(Feedback::class.java).equalTo("modelo.id", id).findAll()
    }

    override fun getModelo(id: String): Modelo? {
        return realm.where(Modelo::class.java).equalTo("id", id).findFirst()
    }

    override fun getPregunta(id: String): Pregunta? {
        return realm.where(Pregunta::class.java).equalTo("id", id).findFirst()
    }

    override fun getPreguntasFromPosiblesRespostas(id: String): RealmResults<Pregunta> {
        return realm.where(Pregunta::class.java).equalTo("posiblesRespostas.id", id).findAll()
    }

    override fun getRespostasFromPregunta(id: String): RealmResults<Resposta> {
        return realm.where(Resposta::class.java).equalTo("pregunta.id", id).findAll()
    }

    override fun getPosibleResposta(id: String): PosibleResposta? {
        return realm.where(PosibleResposta::class.java).equalTo("id", id).findFirst()
    }

    override fun getPosiblesRespostas(id: String): PosiblesRespostas? {
        return realm.where(PosiblesRespostas::class.java).equalTo("id", id).findFirst()
    }

    override fun getResposta(id: String): Resposta? {
        return realm.where(Resposta::class.java).equalTo("id", id).findFirst()
    }

    override fun removeFeedback(id: String?) {
        realm.beginTransaction()
        realm.where(Feedback::class.java)?.equalTo("id", id)?.findAll()?.deleteFirstFromRealm()
        realm.commitTransaction()
    }

    override fun removeModelo(id: String?) {
        realm.beginTransaction()
        realm.where(Modelo::class.java)?.equalTo("id", id)?.findAll()?.deleteFirstFromRealm()
        realm.commitTransaction()
    }

    override fun removePregunta(id: String?) {
        realm.beginTransaction()
        realm.where(Pregunta::class.java)?.equalTo("id", id)?.findAll()?.deleteFirstFromRealm()
        realm.commitTransaction()
    }

    override fun removePosibleResposta(id: String?) {
        realm.beginTransaction()
        realm.where(PosibleResposta::class.java)?.equalTo("id", id)?.findAll()?.deleteFirstFromRealm()
        realm.commitTransaction()
    }

    override fun removePosiblesRespostas(id: String?) {
        realm.beginTransaction()
        realm.where(PosiblesRespostas::class.java)?.equalTo("id", id)?.findAll()?.deleteFirstFromRealm()
        realm.commitTransaction()
    }

    override fun removeResposta(id: String?) {
        realm.beginTransaction()
        realm.where(Resposta::class.java)?.equalTo("id", id)?.findAll()?.deleteFirstFromRealm()
        realm.commitTransaction()
    }

    override fun updateId(feedback: Feedback?, id: String?) {
        if (feedback != null && id != null) {
            realm.beginTransaction()
            feedback.id = id
            realm.commitTransaction()

            save(feedback)
        }
    }

    override fun updateId(resposta: Resposta?, id: String?) {
        if (resposta != null && id != null) {
            realm.beginTransaction()
            resposta.id = id
            realm.commitTransaction()

            save(resposta)
        }
    }

    override fun updateResposta(feedback: Feedback, resposta: Resposta, posibleResposta: PosibleResposta) {
        realm.beginTransaction()
        resposta.posibleResposta = posibleResposta
        realm.commitTransaction()

        databaseFacade.save(resposta)
        updateCovered(feedback)
    }

    override fun updateCovered(feedbacks: RealmResults<Feedback>) {
        for (feedback in feedbacks) {
            updateCovered(feedback)
        }
    }

    override fun updateCovered(feedback: Feedback) {
        if (feedback.estado != ESTADO_ENVIADO_DB) {
            val estadoFromCuberto = getEstadoFromCuberto(isCovered(feedback))

            realm.beginTransaction()
            feedback.estado = estadoFromCuberto
            realm.commitTransaction()

            databaseFacade.save(feedback)
        }
    }

    private fun isCovered(feedback: Feedback): Boolean {
        val preguntas = feedback.modelo?.preguntas
        if (preguntas != null) {
            val respostas = feedback.respostas
            if (respostas != null && respostas.size == preguntas.size) {
                for (resposta in respostas) {
                    if (resposta.posibleResposta == null) {
                        return false
                    }
                }
                return true
            } else {
                initRespostas(feedback)
                return false
            }
        }
        return false
    }

    override fun initRespostas(feedback: Feedback) {
        val respostasFinais = RealmList<Resposta>()
        val respostas = feedback.respostas
        val preguntas = feedback.modelo?.preguntas
        var cuberto = true

        if (respostas != null && preguntas != null) {
            for (pregunta in preguntas) {
                if (pregunta != null) {
                    var resposta = findRespostaByPregunta(respostas, pregunta)
                    if (resposta == null) {
                        resposta = Resposta(pregunta)
                        cuberto = false
                    }
                    if (resposta.posibleResposta == null) {
                        cuberto = false
                    }
                    databaseFacade.save(resposta)
                    respostasFinais.add(resposta)
                }
            }
        }

        val feedbackNew = Feedback(feedback, respostasFinais, getEstadoFromCuberto(cuberto))
        databaseFacade.save(feedbackNew)
    }

    private fun getEstadoFromCuberto(cuberto: Boolean): String {
        return if (cuberto) {
            ESTADO_BORRADOR_DB
        } else {
            ESTADO_INICIADO_DB
        }
    }

    private fun findRespostaByPregunta(respostas: RealmList<Resposta>, pregunta: Pregunta): Resposta? {
        for (resposta in respostas) {
            if (resposta != null && resposta.pregunta == pregunta) {
                return resposta
            }
        }
        return null
    }

    override fun send(feedback: Feedback) {
        if (feedback.estado != ESTADO_ENVIADO_DB) {
            realm.beginTransaction()
            feedback.estado = ESTADO_ENVIADO_DB
            realm.commitTransaction()

            databaseFacade.save(feedback)
        }
    }

    override fun save(feedback: Feedback) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(feedback)
        realm.commitTransaction()
    }

    override fun save(modelo: Modelo) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(modelo)
        realm.commitTransaction()

        val feedbacks = getFeedbacksFromModelo(modelo.id)
        for (feedback in feedbacks) {
            val feedbackAux = Feedback(feedback, modelo)
            save(feedbackAux)
        }
    }

    override fun save(pregunta: Pregunta) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(pregunta)
        realm.commitTransaction()

        val respostas = getRespostasFromPregunta(pregunta.id)
        for (resposta in respostas) {
            val respostaAux = Resposta(resposta, pregunta)
            save(respostaAux)
        }
    }

    override fun save(posibleResposta: PosibleResposta) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(posibleResposta)
        realm.commitTransaction()
    }

    override fun save(posiblesRespostas: PosiblesRespostas) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(posiblesRespostas)
        realm.commitTransaction()

        val respostas = getPreguntasFromPosiblesRespostas(posiblesRespostas.id)
        for (pregunta in respostas) {
            val preguntaAux = Pregunta(pregunta, posiblesRespostas)
            save(preguntaAux)
        }
    }

    override fun save(resposta: Resposta) {
        if (resposta.id != null) {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(resposta)
            realm.commitTransaction()
        }
    }
}