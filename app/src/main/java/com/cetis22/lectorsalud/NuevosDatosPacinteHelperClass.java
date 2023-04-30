package com.cetis22.lectorsalud;

public class NuevosDatosPacinteHelperClass {

    String nombrecompleto, fechadenacimiento, curp, tipodesangre, fechaultexamen, enfermedadesp, medicamentoscons, dosismedicamentos, ufechacons, fechacirujiault, alergias, enfercronicas, antefamiliares;

    public NuevosDatosPacinteHelperClass() {
    }

    public NuevosDatosPacinteHelperClass(String nombrecompleto, String fechadenacimiento, String curp, String tipodesangre, String fechaultexamen, String enfermedadesp, String medicamentoscons, String dosismedicamentos, String ufechacons, String fechacirujiault, String alergias, String enfercronicas, String antefamiliares) {
        this.nombrecompleto = nombrecompleto;
        this.fechadenacimiento = fechadenacimiento;
        this.curp = curp;
        this.tipodesangre = tipodesangre;
        this.fechaultexamen = fechaultexamen;
        this.enfermedadesp = enfermedadesp;
        this.medicamentoscons = medicamentoscons;
        this.dosismedicamentos = dosismedicamentos;
        this.ufechacons = ufechacons;
        this.fechacirujiault = fechacirujiault;
        this.alergias = alergias;
        this.enfercronicas = enfercronicas;
        this.antefamiliares = antefamiliares;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getFechadenacimiento() {
        return fechadenacimiento;
    }

    public void setFechadenacimiento(String fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getTipodesangre() {
        return tipodesangre;
    }

    public void setTipodesangre(String tipodesangre) {
        this.tipodesangre = tipodesangre;
    }

    public String getFechaultexamen() {
        return fechaultexamen;
    }

    public String getEnfermedadesp() {
        return enfermedadesp;
    }

    public String getMedicamentoscons() {
        return medicamentoscons;
    }

    public String getDosismedicamentos() {
        return dosismedicamentos;
    }

    public String getUfechacons() {
        return ufechacons;
    }

    public String getFechacirujiault() {
        return fechacirujiault;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getEnfercronicas() {
        return enfercronicas;
    }

    public String getAntefamiliares() {
        return antefamiliares;
    }
}