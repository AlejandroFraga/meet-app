package com.example.alejandro.myapplication.model.feedback;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PosiblesRespostas extends RealmObject {

    @PrimaryKey
    private String id;
    private String representacion;
    private RealmList<PosibleResposta> posibleRespostas;

    public PosiblesRespostas(){
        super();
    }

    public PosiblesRespostas(String id, String representacion, RealmList<PosibleResposta> posibleRespostas) {
        this.id = id;
        this.representacion = representacion;
        this.posibleRespostas = posibleRespostas;
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

    public RealmList<PosibleResposta> getPosibleRespostas() {
        return posibleRespostas;
    }

    public void setPosibleRespostas(RealmList<PosibleResposta> posibleRespostas) {
        this.posibleRespostas = posibleRespostas;
    }
}
