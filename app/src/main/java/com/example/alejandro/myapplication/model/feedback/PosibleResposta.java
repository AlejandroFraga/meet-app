package com.example.alejandro.myapplication.model.feedback;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PosibleResposta extends RealmObject {

    @PrimaryKey
    private String id;
    private String representacion;
    private String valor;

    public PosibleResposta(){
        super();
    }

    public PosibleResposta(String id, String representacion, String valor) {
        this.id = id;
        this.representacion = representacion;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepresentacion() {
        return representacion;
    }

    public void setRepresentacion(String representacion) {
        this.representacion = representacion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
