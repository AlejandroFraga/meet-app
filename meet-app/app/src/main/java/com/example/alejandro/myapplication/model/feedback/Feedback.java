package com.example.alejandro.myapplication.model.feedback;

import com.example.alejandro.myapplication.utils.ConstantesKt;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Feedback extends RealmObject {

    @PrimaryKey
    private String id;
    private String title;
    private Date dataFin;
    private Modelo modelo;
    private RealmList<Resposta> respostas;
    private String estado = ConstantesKt.ESTADO_INICIADO_DB;

    public Feedback(){
        super();
    }

    public Feedback(String id, String title, Date dataFin, Modelo modelo, RealmList<Resposta> respostas, String estado) {
        this.id = id;
        this.title = title;
        this.dataFin = dataFin;
        this.modelo = modelo;
        this.respostas = respostas;
        if(estado != null) {
            this.estado = estado;
        }
    }

    public Feedback(Feedback feedback, Modelo modelo) {
        this.id = feedback.id;
        this.title = feedback.title;
        this.dataFin = feedback.dataFin;
        this.modelo = modelo;
        this.respostas = feedback.respostas;
        this.estado = feedback.estado;
    }

    public Feedback(Feedback feedback, RealmList<Resposta> respostas, String estado) {
        this.id = feedback.id;
        this.title = feedback.title;
        this.dataFin = feedback.dataFin;
        this.modelo = feedback.modelo;
        this.respostas = respostas;
        this.estado = estado;
    }

    public Feedback(Feedback feedback, String estado) {
        this.id = feedback.id;
        this.title = feedback.title;
        this.dataFin = feedback.dataFin;
        this.modelo = feedback.modelo;
        this.respostas = feedback.respostas;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDataFin() {
        return dataFin;
    }

    public void setDataFin(Date dataFin) {
        this.dataFin = dataFin;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public RealmList<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(RealmList<Resposta> respostas) {
        this.respostas = respostas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getModeloId() {
        return modelo != null ? modelo.getId() : "";
    }
}
