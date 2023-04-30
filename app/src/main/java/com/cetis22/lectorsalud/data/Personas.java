package com.cetis22.lectorsalud.data;

import android.graphics.Bitmap;

public class Personas {
    String nombre;
    String hospital;
    String curp;
    Bitmap imagen;


    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Personas(String nombre, String hospital, String curp, Bitmap imagen) {
        this.nombre = nombre;
        this.hospital = hospital;
        this.curp = curp;
        this.imagen = imagen;
    }


    public Bitmap getImagen() {
        return imagen;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

}
