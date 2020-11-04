package com.example.alejandro.myapplication.model.patientdata;

import com.example.alejandro.myapplication.utils.ConstantesKt;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FichaPaciente extends RealmObject {

    @PrimaryKey
    private String id = "";
    private String nome = "";
    private String primeiroApelido = "";
    private String segundoApelido = "";
    private Date nacemento = null;
    private RealmList<Etapa> etapas = new RealmList();

    public FichaPaciente() {
        super();
    }

    public FichaPaciente(String id, String nome, String primeiroApelido, String segundoApelido, Date nacemento, RealmList<Etapa> etapas) {
        this.id = id;
        this.nome = nome;
        this.primeiroApelido = primeiroApelido;
        this.segundoApelido = segundoApelido;
        this.nacemento = nacemento;
        this.etapas = etapas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrimeiroApelido() {
        return primeiroApelido;
    }

    public void setPrimeiroApelido(String primeiroApelido) {
        this.primeiroApelido = primeiroApelido;
    }

    public String getSegundoApelido() {
        return segundoApelido;
    }

    public void setSegundoApelido(String segundoApelido) {
        this.segundoApelido = segundoApelido;
    }

    public Date getNacemento() {
        return nacemento;
    }

    public void setNacemento(Date nacemento) {
        this.nacemento = nacemento;
    }

    public RealmList<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(RealmList<Etapa> etapas) {
        this.etapas = etapas;
    }

    public String getNomeDePila(){
        return nome != null && primeiroApelido != null && segundoApelido != null
                ? primeiroApelido + ConstantesKt.SPACE + segundoApelido + ConstantesKt.COMMA + ConstantesKt.SPACE + nome
                : ConstantesKt.BLANK;
    }
}
