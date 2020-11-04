package com.example.alejandro.myapplication.model.chat;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Mensaxe extends RealmObject {

    @PrimaryKey
    private String id = null;
    private String mensaxe = null;
    private Date dataCreacion = new Date();
    private Date dataEnvio = null;
    private Date dataRecepcion = null;
    private Date dataLectura = null;
    private boolean ePropia = false;
    private Mensaxe respostaA = null;

    public Mensaxe() {
        super();
    }

    public Mensaxe(String mensaxe, boolean ePropia) {
        this.mensaxe = mensaxe;
        this.ePropia = ePropia;
    }

    public Mensaxe(String id, String mensaxe, Date dataCreacion, Date dataEnvio, Date dataRecepcion, Date dataLectura, boolean ePropia, Mensaxe respostaA) {
        this.id = id;
        this.mensaxe = mensaxe;
        this.dataCreacion = dataCreacion;
        this.dataEnvio = dataEnvio;
        this.dataRecepcion = dataRecepcion;
        this.dataLectura = dataLectura;
        this.ePropia = ePropia;
        this.respostaA = respostaA;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaxe() {
        return mensaxe;
    }

    public void setMensaxe(String mensaxe) {
        this.mensaxe = mensaxe;
    }

    public Date getDataCreacion() {
        return dataCreacion;
    }

    public void setDataCreacion(Date dataCreacion) {
        this.dataCreacion = dataCreacion;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Date getDataRecepcion() {
        return dataRecepcion;
    }

    public void setDataRecepcion(Date dataRecepcion) {
        this.dataRecepcion = dataRecepcion;
    }

    public Date getDataLectura() {
        return dataLectura;
    }

    public void setDataLectura(Date dataLectura) {
        this.dataLectura = dataLectura;
    }

    public boolean getEPropia() {
        return ePropia;
    }

    public void setEPropia(boolean ePropia) {
        this.ePropia = ePropia;
    }

    public Mensaxe getRespostaA() {
        return respostaA;
    }

    public void setRespostaA(Mensaxe respostaA) {
        this.respostaA = respostaA;
    }

    public boolean marcarLida() {
        return dataLectura == null && !ePropia;
    }

}
