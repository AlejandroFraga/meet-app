package com.example.alejandro.myapplication.utils

import com.example.alejandro.myapplication.model.calendar.Evento
import com.example.alejandro.myapplication.model.chat.Mensaxe
import com.example.alejandro.myapplication.model.configuration.Configuracion
import com.example.alejandro.myapplication.model.feedback.*
import com.example.alejandro.myapplication.model.notifications.Notificacion
import com.example.alejandro.myapplication.model.patientdata.Etapa
import com.example.alejandro.myapplication.model.patientdata.FichaPaciente
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import io.realm.RealmList
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

//Arquivo de Utils que se utilizará para funcións comúns dende moitas partes diferentes da aplicación

fun getUserUID() : String? {
    return FirebaseAuth.getInstance()?.currentUser?.uid
}

fun getUserDocumentReference(collection: String, document: String): DocumentReference? {
    val userUID = getUserUID()
    if(userUID != null) {
        return FirebaseFirestore.getInstance().collection(PACIENTE_DB).document(userUID).collection(collection).document(document)
    }
    return null
}

fun getUserCollectionReference(collection: String): CollectionReference? {
    val userUID = getUserUID()
    if(userUID != null) {
        return FirebaseFirestore.getInstance().collection(PACIENTE_DB).document(userUID).collection(collection)
    }
    return null
}

fun getPublicDocumentReference(collection: String, document: String): DocumentReference {
    return FirebaseFirestore.getInstance().collection(PUBLIC_DB).document(PUBLIC_DB).collection(collection).document(document)
}

fun getPublicCollectionReference(collection: String): CollectionReference {
    return FirebaseFirestore.getInstance().collection(PUBLIC_DB).document(PUBLIC_DB).collection(collection)
}

fun getDocumentReference(collection1: String, collection2: String, document: String): DocumentReference? {
    when (collection1) {
        PACIENTE_DB -> return getUserDocumentReference(collection2, document)
        PUBLIC_DB -> return getPublicDocumentReference(collection2, document)
        else -> return null
    }
}

fun getCollectionReference(collection1: String, collection2: String): CollectionReference? {
    when (collection1) {
        PACIENTE_DB -> return getUserCollectionReference(collection2)
        PUBLIC_DB -> return getPublicCollectionReference(collection2)
        else -> return null
    }
}

fun getDocumentReference(collection: String, document: String): DocumentReference {
    return FirebaseFirestore.getInstance().collection(collection).document(document)
}

fun getCollectionReference(collection: String): CollectionReference {
    return FirebaseFirestore.getInstance().collection(collection)
}


//Converters

fun toString(any: Any?): String? {
    var string: String? = null
    var int: Int? = toInt(any)
    if (int != null) {
        string = int.toString()
    } else if (any != null) {
        string = any.toString()
    }
    return string
}

fun toInt(any: Any?): Int? {
    var int: Int? = null
    if (any != null) {
        try {
            int = any.toString().toDouble().toInt()
        } catch (e: NumberFormatException) {
            //FIXME
        }
    }
    return int
}

fun toLong(any: Any?): Long? {
    var long: Long? = null
    if (any != null) {
        try {
            long = any.toString().toDouble().toLong()
        } catch (e: NumberFormatException) {
            //FIXME
        }
    }
    return long
}

fun toFloat(any: Any?): Float? {
    var float: Float? = null
    if (any != null) {
        try {
            float = any.toString().toDouble().toFloat()
        } catch (e: NumberFormatException) {
            //FIXME
        }
    }
    return float
}

fun toDouble(any: Any?): Double? {
    var double: Double? = null
    if (any != null) {
        try {
            double = any.toString().toDouble()
        } catch (e: NumberFormatException) {
            //FIXME
        }
    }
    return double
}

fun toDate(any: Any?): Date? {
    var date: Date? = null
    if (any != null) {
        try {
            date = Date(any.toString())
        } catch (e: NumberFormatException) {
            //FIXME
        }
    }
    return date
}

fun toBoolean(any: Any?): Boolean? {
    var boolean: Boolean? = null
    if (any != null) {
        try {
            boolean = any.toString().toBoolean()
        } catch (e: NumberFormatException) {
            //FIXME
        }
    }
    return boolean
}

fun toMap(mensaxe: Mensaxe): HashMap<String, Any> {
    val modeloMap = HashMap<String, Any>()
    modeloMap.put(MENSAXE_DB, mensaxe.mensaxe)
    modeloMap.put(DATA_CREACION_DB, mensaxe.dataCreacion)
    modeloMap.put(DATA_ENVIO_DB, mensaxe.dataEnvio)
    modeloMap.put(DATA_RECEPCION_DB, mensaxe.dataRecepcion)
    modeloMap.put(DATA_LECTURA_DB, mensaxe.dataLectura)
    modeloMap.put(E_PROPIA_DB, mensaxe.ePropia)
    modeloMap.put(RESPOSTA_A_DB, mensaxe.respostaA)

    return modeloMap
}

fun toMap(evento: Evento): HashMap<String, Any> {
    val modeloMap = HashMap<String, Any>()
    modeloMap.put(TITULO_DB, evento.titulo)
    modeloMap.put(DATA_COMEZO_DB, evento.dataComezo)
    modeloMap.put(DATA_FIN_DB, evento.dataFin)
    modeloMap.put(DATA_NOTIFICACION_DB, evento.dataNotificacion)
    modeloMap.put(UBICACION_DB, evento.ubicacion)
    modeloMap.put(COR_DB, evento.cor)

    return modeloMap
}

fun toMap(notificacion: Notificacion): HashMap<String, Any> {
    val modeloMap = HashMap<String, Any>()
    modeloMap.put(NOTIFICACION_ID_DB, notificacion.notificacionId)
    modeloMap.put(TITLE_DB, notificacion.title)
    modeloMap.put(BIG_TITLE_DB, notificacion.bigTitle)
    modeloMap.put(TEXT_DB, notificacion.text)
    modeloMap.put(BIG_TEXT_DB, notificacion.bigText)
    modeloMap.put(DATA_DB, notificacion.data)
    modeloMap.put(ENVIADA_DB, notificacion.enviada)

    return modeloMap
}

fun toMap(configuracion: Configuracion): HashMap<String, Any> {
    val modeloMap = HashMap<String, Any>()
    modeloMap.put(DESCRIPCION_DB, configuracion.descripcion)
    modeloMap.put(VALOR_DB, configuracion.valor)

    return modeloMap
}

fun toMap(fichaPaciente: FichaPaciente): HashMap<String, Any> {
    val modeloMap = HashMap<String, Any>()
    modeloMap.put(NOME_DB, fichaPaciente.nome)
    modeloMap.put(PRIMEIRO_APELIDO_DB, fichaPaciente.primeiroApelido)
    modeloMap.put(SEGUNDO_APELIDO_DB, fichaPaciente.segundoApelido)
    modeloMap.put(NACEMENTO_DB, fichaPaciente.nacemento)
    modeloMap.put(ETAPAS_DB, convertEtapasToString(fichaPaciente.etapas))

    return modeloMap
}

fun toMap(etapa: Etapa): HashMap<String, Any> {
    val modeloMap = HashMap<String, Any>()
    modeloMap.put(TITLE_DB, etapa.title)
    modeloMap.put(DESCRIPCION_DB, etapa.descripcion)
    modeloMap.put(DATA_COMEZO_DB, etapa.dataComezo)
    modeloMap.put(DATA_FIN_DB, etapa.dataFin)

    return modeloMap
}

fun toMap(feedback: Feedback): HashMap<String, Any> {
    val modeloMap = HashMap<String, Any>()
    modeloMap.put(TITLE_DB, feedback.title)
    modeloMap.put(DATA_FIN_DB, feedback.dataFin)
    modeloMap.put(MODELO_DB, feedback.modeloId)
    modeloMap.put(RESPOSTAS_DB, convertRespostasToString(feedback.respostas))
    modeloMap.put(ESTADO_DB, feedback.estado)

    return modeloMap
}

fun toMap(modelo: Modelo): HashMap<String, Any> {
    val modeloMap = HashMap<String, Any>()
    modeloMap.put(VERSION_DB, modelo.version)
    modeloMap.put(TITULO_DB, modelo.titulo)
    modeloMap.put(PREGUNTAS_DB, convertPreguntasToString(modelo.preguntas))

    return modeloMap
}

fun toMap(pregunta: Pregunta): HashMap<String, Any> {
    val modeloMap = HashMap<String, Any>()
    modeloMap.put(ENUNCIADO_DB, pregunta.enunciado)
    modeloMap.put(POSIBLES_RESPOSTAS_DB, pregunta.posiblesRespostasId)

    return modeloMap
}

fun toMap(posiblesRespostas: PosiblesRespostas): HashMap<String, Any> {
    val modeloMap = HashMap<String, Any>()
    modeloMap.put(REPRESENTACION_DB, posiblesRespostas.representacion)
    modeloMap.put(POSIBLE_RESPOSTAS_DB, convertPosibleRespostasToString(posiblesRespostas.posibleRespostas))

    return modeloMap
}

fun toMap(posibleResposta: PosibleResposta): HashMap<String, Any> {
    val modeloMap = HashMap<String, Any>()
    modeloMap.put(REPRESENTACION_DB, posibleResposta.representacion)
    modeloMap.put(VALOR_DB, posibleResposta.valor)

    return modeloMap
}

fun toMap(resposta: Resposta): HashMap<String, Any> {
    val modeloMap = HashMap<String, Any>()
    modeloMap.put(PREGUNTA_DB, resposta.preguntaId)
    modeloMap.put(POSIBLE_RESPOSTA_DB, resposta.posibleRespostaId)

    return modeloMap
}

//Dates operations

fun getOnlyDate(date: Date): Date {
    val formatDateS = "dd/MM/yyyy"
    val formatDate = SimpleDateFormat(formatDateS)

    return formatDate.parse(formatDate.format(date))
}

fun add1Hour(date: Date): Date {
    val c = Calendar.getInstance()
    c.time = date
    c.add(Calendar.HOUR, 1)
    return c.time
}

fun add1Day(date: Date): Date {
    val c = Calendar.getInstance()
    c.time = date
    c.add(Calendar.DATE, 1)
    return c.time
}


//Formatters

fun formatHM12H(date: Date?, locale: Locale): String {
    if (date != null) {
        val formatHourS = "hh:mm a"
        val formatHour = SimpleDateFormat(formatHourS, locale)

        return formatHour.format(date).toUpperCase()
    } else {
        return BLANK
    }
}

fun formatHM24H(date: Date?): String {
    if (date != null) {
        val formatHourS = "HH:mm"
        val formatHour = SimpleDateFormat(formatHourS)

        return formatHour.format(date)
    } else {
        return BLANK
    }
}

fun formatHMS12H(date: Date?, locale: Locale): String {
    if (date != null) {
        val formatHourS = "hh:mm:ss a"
        val formatHour = SimpleDateFormat(formatHourS, locale)

        return formatHour.format(date).toUpperCase()
    } else {
        return BLANK
    }
}

fun formatHMS24H(date: Date?): String {
    if (date != null) {
        val formatHourS = "HH:mm:ss"
        val formatHour = SimpleDateFormat(formatHourS)

        return formatHour.format(date)
    } else {
        return BLANK
    }
}

fun formatCompleteDate(date: Date?, locale: Locale): String {
    if (date != null) {
        val separation = SPACE + CENTER_DOT + SPACE
        return formatDateCalendar(date, locale) + separation + formatHM12H(date, locale).toUpperCase()
    } else {
        return BLANK
    }
}

fun formatFullDate(date: Date?): String {
    if (date != null) {
        return formatHour(date) + SPACE + formatDateYear(date)
    } else {
        return BLANK
    }
}

fun formatHour(date: Date?): String {
    if (date != null) {
        val formatHourS = "hh:mm:ss a"
        val formatHour = SimpleDateFormat(formatHourS)

        return formatHour.format(date).toUpperCase()
    } else {
        return BLANK
    }
}

fun formatRange(date1: Date?, date2: Date?, locale: Locale): String {
    if (date1 != null && date2 != null) {
        val separation = " - "
        var formatHour12HS = "hh:mm"
        var formatHour12HAS = "hh:mm a"
        val formatHour12H = SimpleDateFormat(formatHour12HS, locale)
        val formatHour12HA = SimpleDateFormat(formatHour12HAS, locale)

        if ((date1.hours > 12 && date2.hours <= 12)
                || (date1.hours <= 12 && date2.hours > 12)) {
            return formatHour12HA.format(date1).toUpperCase() + separation + formatHour12HA.format(date2).toUpperCase()
        } else {
            return formatHour12H.format(date1).toUpperCase() + separation + formatHour12HA.format(date2).toUpperCase()
        }
    } else {
        return BLANK
    }
}

fun formatMonth(date: Date, locale: Locale): String {
    var formatDiffYear = "MMM - yyyy"
    var formatSameYear = "MMMM"
    val dateFormatDiffYear = SimpleDateFormat(formatDiffYear, locale)
    val dateFormatSameYear = SimpleDateFormat(formatSameYear, locale)

    if (date.year == Date().year) {
        return dateFormatSameYear.format(date).capitalize()
    } else {
        return dateFormatDiffYear.format(date).capitalize()
    }
}

fun formatDateCalendar(date: Date?, locale: Locale): String {
    if (date != null) {
        var formatDateS = "EEEE, dd "
        val formatDate = SimpleDateFormat(formatDateS, locale)

        return formatDate.format(date).capitalize() + formatMonth(date, locale)
    } else {
        return BLANK
    }
}

fun formatDateYear(date: Date?): String {
    if (date != null) {
        var formatDateS = "dd/MM/yyyy"
        val formatDate = SimpleDateFormat(formatDateS)

        return formatDate.format(date)
    } else {
        return BLANK
    }
}

fun formatDateRange(date1: Date?, date2: Date?, locale: Locale): String {
    if (date1 != null && date2 != null) {
        val separation = SPACE + CENTER_DOT + SPACE
        return formatDateCalendar(date1, locale) + separation + formatRange(date1, date2, locale)
    } else {
        return BLANK
    }
}

fun formatCompleteDateRange(date1: Date?, date2: Date?, locale: Locale): String {
    if (date1 != null && date2 != null) {
        val separation = SPACE + CENTER_DOT + SPACE
        return formatDateCalendar(date1, locale) + separation + formatDateCalendar(date2, locale)
    } else {
        return BLANK
    }
}


//FIXME convert
fun convertPreguntasToIds(preguntas: RealmList<Pregunta>): ArrayList<String> {
    val preguntasIds: ArrayList<String> = ArrayList()
    for (pregunta in preguntas) {
        if(pregunta != null) {
            preguntasIds.add(pregunta.id)
        }
    }
    return preguntasIds
}

fun convertRespostasToIds(respostas: RealmList<Resposta>): ArrayList<String> {
    val respostasIds: ArrayList<String> = ArrayList()
    for (resposta in respostas) {
        if(resposta != null) {
            respostasIds.add(resposta.id)
        }
    }
    return respostasIds
}

fun convertPosibleRespostasToIds(posibleRespostas: RealmList<PosibleResposta>): ArrayList<String> {
    val posibleRespostasIds: ArrayList<String> = ArrayList()
    for (posibleResposta in posibleRespostas) {
        if(posibleResposta != null) {
            posibleRespostasIds.add(posibleResposta.id)
        }
    }
    return posibleRespostasIds
}

fun convertEtapasToIds(etapas: RealmList<Etapa>): ArrayList<String> {
    val etapasIds: ArrayList<String> = ArrayList()
    for (etapa in etapas) {
        if(etapa != null) {
            etapasIds.add(etapa.id)
        }
    }
    return etapasIds
}

fun convertIdsToString(ids: ArrayList<String>): String {
    return Gson().toJson(ids)
}

fun convertPreguntasToString(preguntas: RealmList<Pregunta>): String {
    return convertIdsToString(convertPreguntasToIds(preguntas))
}

fun convertPosibleRespostasToString(posibleRespostas: RealmList<PosibleResposta>): String {
    return convertIdsToString(convertPosibleRespostasToIds(posibleRespostas))
}

fun convertRespostasToString(respostas: RealmList<Resposta>): String {
    return convertIdsToString(convertRespostasToIds(respostas))
}

fun convertEtapasToString(etapas: RealmList<Etapa>): String {
    return convertIdsToString(convertEtapasToIds(etapas))
}