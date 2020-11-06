package com.example.alejandro.myapplication.model.patientdata;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Etapa extends RealmObject implements Comparable<Etapa> {

    @PrimaryKey
    private String id = "";
    private String title = "";
    private String descripcion = "";
    private Date dataComezo;
    private Date dataFin;

    public Etapa() {
        super();
    }

    public Etapa(String id, String title, String descripcion, Date dataComezo, Date dataFin) {
        this.id = id;
        this.title = title;
        this.descripcion = descripcion;
        this.dataComezo = dataComezo;
        this.dataFin = dataFin;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @Override
    public int compareTo(Etapa etapa) {
        if (etapa != null) {
            return ((int)(dataComezo.getTime() - etapa.dataComezo.getTime()));
        }
        return 0;
    }
}
