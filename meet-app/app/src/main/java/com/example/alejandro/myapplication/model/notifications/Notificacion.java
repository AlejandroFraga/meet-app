package com.example.alejandro.myapplication.model.notifications;

import com.example.alejandro.myapplication.model.calendar.Evento;
import com.example.alejandro.myapplication.model.chat.Mensaxe;
import com.example.alejandro.myapplication.utils.ConstantesKt;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

public class Notificacion extends RealmObject {

    @PrimaryKey
    private String id = null;
    private Integer notificacionId = null;
    private String title = null;
    private String bigTitle = null;
    private String text = null;
    private String bigText = null;
    private Date data = new Date();
    private boolean enviada = false;


    public Notificacion() {
        super();
    }

    public Notificacion(String id, Integer notificacionId, String title, String bigTitle, String text, String bigText, Date data, Boolean enviada) {
        this.id = id;
        this.notificacionId = notificacionId;
        this.title = title;
        this.bigTitle = bigTitle;
        this.text = text;
        this.bigText = bigText;

        if(data != null) {
            this.data = data;
        }

        if(enviada != null) {
            this.enviada = enviada;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNotificacionId() {
        return notificacionId;
    }

    public void setNotificacionId(Integer notificacionId) {
        this.notificacionId = notificacionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBigTitle() {
        return bigTitle;
    }

    public void setBigTitle(String bigTitle) {
        this.bigTitle = bigTitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBigText() {
        return bigText;
    }

    public void setBigText(String bigText) {
        this.bigText = bigText;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean getEnviada() {
        return enviada;
    }

    public void setEnviada(boolean enviada) {
        this.enviada = enviada;
    }
}
