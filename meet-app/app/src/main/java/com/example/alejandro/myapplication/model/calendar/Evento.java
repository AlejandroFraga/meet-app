package com.example.alejandro.myapplication.model.calendar;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Evento extends RealmObject {

    @PrimaryKey
    private String id = null;
    private String titulo = null;
    private Date dataComezo = null;
    private Date dataFin = null;
    private Date dataNotificacion = null;
    private String ubicacion = null;
    private String cor = null;

    public Evento() {
        super();
    }

    public Evento(String titulo, Date dataComezo, Date dataFin) {
        this.titulo = titulo;
        this.dataComezo = dataComezo;
        this.dataFin = dataFin;
    }

    public Evento(String id, String titulo, Date dataComezo, Date dataFin, Date dataNotificacion, String ubicacion, String cor) {
        this.id = id;
        this.titulo = titulo;
        this.dataComezo = dataComezo;
        this.dataFin = dataFin;
        this.dataNotificacion = dataNotificacion;
        this.ubicacion = ubicacion;
        this.cor = cor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataComezo() {
        return dataComezo;
    }

    public void setDataComezo(Date dataComezo) {
        this.dataComezo = dataComezo;
    }

    public Date getDataFin() {
        return dataFin;
    }

    public void setDataFin(Date dataFin) {
        this.dataFin = dataFin;
    }

    public Date getDataNotificacion() {
        return dataNotificacion;
    }

    public void setDataNotificacion(Date dataNotificacion) {
        this.dataNotificacion = dataNotificacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
