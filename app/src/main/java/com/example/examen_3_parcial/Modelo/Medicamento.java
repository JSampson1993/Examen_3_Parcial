package com.example.examen_3_parcial.Modelo;

import android.graphics.Bitmap;

import java.sql.Blob;

public class Medicamento {
    Integer id;
    String descripcion;
    Integer cantidad;
    String tiempo;
    Integer periocidad;
    Bitmap imagen;

    public Medicamento() {
    }

    public Medicamento(Integer id, String descripcion, Integer cantidad, String tiempo, Integer periocidad, Bitmap imagen) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.tiempo = tiempo;
        this.periocidad = periocidad;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getPeriocidad() {
        return periocidad;
    }

    public void setPeriocidad(Integer periocidad) {
        this.periocidad = periocidad;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
