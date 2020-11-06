package com.example.alejandro.myapplication.model.feedback;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Resposta extends RealmObject {

    @PrimaryKey
    private String id;
    private Pregunta pregunta;
    private PosibleResposta posibleResposta;

    public Resposta(){
        super();
    }

    public Resposta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Resposta(Resposta resposta, Pregunta pregunta) {
        this.id = resposta.id;
        this.pregunta = pregunta;
        this.posibleResposta = resposta.posibleResposta;
    }

    public Resposta(String id, Pregunta pregunta, PosibleResposta posibleResposta) {
        this.id = id;
        this.pregunta = pregunta;
        this.posibleResposta = posibleResposta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public PosibleResposta getPosibleResposta() {
        return posibleResposta;
    }

    public void setPosibleResposta(PosibleResposta posibleResposta) {
        this.posibleResposta = posibleResposta;
    }

    public String getPosibleRespostaId() {
        return posibleResposta != null ? posibleResposta.getId() : "";
    }

    public String getPreguntaId() {
        return pregunta != null ? pregunta.getId() : "";
    }
}
