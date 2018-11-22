package com.example.mario.ahorramas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item2 {

    @SerializedName("idcabeceralista")
    @Expose
    private Integer idcabeceralista;

    @SerializedName("correo")
    @Expose
    private String correo;

    @SerializedName("nombrelista")
    @Expose
    private String nombrelista;

    @SerializedName("fechalista")
    @Expose
    private String fechalista;


    public Item2() {
    }

    public Item2(Integer idcabeceralista, String correo, String nombrelista, String fechalista) {
        this.idcabeceralista = idcabeceralista;
        this.correo = correo;
        this.nombrelista = nombrelista;
        this.fechalista = fechalista;
    }

    public Integer getIdcabeceralista() {
        return idcabeceralista;
    }

    public void setIdcabeceralista(Integer idcabeceralista) {
        this.idcabeceralista = idcabeceralista;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getNombreLista() {
        return nombrelista;
    }

    public void setNombreLista(String nombrelista) {
        this.nombrelista = nombrelista;
    }

    public String getFechaLista() {
        return fechalista;
    }

    public void setFechalista(String fechalista) {
        this.fechalista = fechalista;
    }

    @Override
    public String toString() {
        return "Item2{" +
                "idcabeceralista=" + idcabeceralista +
                ", correo='" + correo + '\'' +
                ", nombrelista='" + nombrelista + '\'' +
                ", fechalista='" + fechalista + '\'' +
                '}';
    }
}