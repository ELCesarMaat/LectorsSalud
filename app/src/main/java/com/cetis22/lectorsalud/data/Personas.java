package com.cetis22.lectorsalud.data;

import android.graphics.Bitmap;

/* loaded from: classes4.dex */
public class Personas {
    String curp;
    String hospital;
    Bitmap imagen;
    String nombre;

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
        return this.imagen;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHospital() {
        return this.hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getCurp() {
        return this.curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
}
