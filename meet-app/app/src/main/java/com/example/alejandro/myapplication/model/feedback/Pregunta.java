package com.example.alejandro.myapplication.model.feedback;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Pregunta extends RealmObject {

    @PrimaryKey
    private String id;
    private String enunciado;
    private PosiblesRespostas posiblesRespostas;

    public Pregunta(){
        super();
    }

    public Pregunta(String id, String enunciado, PosiblesRespostas posiblesRespostas) {
        this.id = id;
        this.enunciado = enunciado;
        this.posiblesRespostas = posiblesRespostas;
    }

    public Pregunta(Pregunta pregunta, PosiblesRespostas posiblesRespostas) {
        this.id = pregunta.id;
        this.enunciado = pregunta.enunciado;
        this.posiblesRespostas = posiblesRespostas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public PosiblesRespostas getPosiblesRespostas() {
        return posiblesRespostas;
    }

    public void setPosiblesRespostas(PosiblesRespostas posiblesRespostas) {
        this.posiblesRespostas = posiblesRespostas;
    }

    public String getPosiblesRespostasId() {
        return posiblesRespostas != null ? posiblesRespostas.getId() : "";
    }
}
