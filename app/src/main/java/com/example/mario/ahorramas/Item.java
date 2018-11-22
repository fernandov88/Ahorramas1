package com.example.mario.ahorramas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("subcategoria")
    @Expose
    private String subcategoria;
    @SerializedName("producto")
    @Expose
    private String producto;
    @SerializedName("categoria")
    @Expose
    private String categoria;
    @SerializedName("walmart")
    @Expose
    private String walmart;
    @SerializedName("superselectos")
    @Expose
    private String superselectos;



    @SerializedName("idcabeceralista")
    @Expose
    private String idcabeceralista;

    @SerializedName("correo")
    @Expose
    private String correo;

    @SerializedName("nombrelista")
    @Expose
    private String nombrelista;

    @SerializedName("fechalista")
    @Expose
    private String fechalista;

    @Override
    public String toString() {
        return "Item{" +
                "subcategoria='" + subcategoria + '\'' +
                ", producto='" + producto + '\'' +
                ", categoria='" + categoria + '\'' +
                ", walmart='" + walmart + '\'' +
                ", superselectos='" + superselectos + '\'' +
                ", idcabeceralista='" + idcabeceralista + '\'' +
                ", correo='" + correo + '\'' +
                ", nombrelista='" + nombrelista + '\'' +
                ", fechalista='" + fechalista + '\'' +
                '}';
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getWalmart() {
        return walmart;
    }

    public void setWalmart(String walmart) {
        this.walmart = walmart;
    }

    public String getSuperselectos() {
        return superselectos;
    }

    public void setSuperselectos(String superselectos) {
        this.superselectos = superselectos;
    }



    public String getIdcabeceralista() {
        return idcabeceralista;
    }

    public void setIdcabeceralista(String idcabeceralista) {
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

}