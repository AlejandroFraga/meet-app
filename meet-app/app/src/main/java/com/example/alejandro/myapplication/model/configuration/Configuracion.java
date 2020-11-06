package com.example.alejandro.myapplication.model.configuration;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Configuracion extends RealmObject {

    @PrimaryKey
    private String id = "";
    private String descripcion = "";
    private String valor = "";

    public Configuracion() {
        super();
    }

    public Configuracion(String id, String descripcion, String valor) {
        this.id = id;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
