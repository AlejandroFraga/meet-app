package com.example.alejandro.myapplication.model.feedback;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Modelo extends RealmObject {

    @PrimaryKey
    private String id;
    private double version;
    private String titulo;
    private RealmList<Pregunta> preguntas;

    public Modelo(){
        super();
    }

    public Modelo(String id, double version, String titulo, RealmList<Pregunta> preguntas) {
        super();
        this.id = id;
        this.version = version;
        this.titulo = titulo;
        this.preguntas = preguntas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public RealmList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(RealmList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}
