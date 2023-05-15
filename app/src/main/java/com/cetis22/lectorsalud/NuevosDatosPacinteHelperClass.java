package com.cetis22.lectorsalud;

/* loaded from: classes5.dex */
public class NuevosDatosPacinteHelperClass {
    String alergias;
    String antefamiliares;
    String curp;
    String dosismedicamentos;
    String enfercronicas;
    String enfermedadesp;
    String fechacirujiault;
    String fechadenacimiento;
    String fechaultexamen;
    String medicamentoscons;
    String nombrecompleto;
    String tipodesangre;
    String ufechacons;

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
        return this.nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getFechadenacimiento() {
        return this.fechadenacimiento;
    }

    public void setFechadenacimiento(String fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;
    }

    public String getCurp() {
        return this.curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getTipodesangre() {
        return this.tipodesangre;
    }

    public void setTipodesangre(String tipodesangre) {
        this.tipodesangre = tipodesangre;
    }

    public String getFechaultexamen() {
        return this.fechaultexamen;
    }

    public String getEnfermedadesp() {
        return this.enfermedadesp;
    }

    public String getMedicamentoscons() {
        return this.medicamentoscons;
    }

    public String getDosismedicamentos() {
        return this.dosismedicamentos;
    }

    public String getUfechacons() {
        return this.ufechacons;
    }

    public String getFechacirujiault() {
        return this.fechacirujiault;
    }

    public String getAlergias() {
        return this.alergias;
    }

    public String getEnfercronicas() {
        return this.enfercronicas;
    }

    public String getAntefamiliares() {
        return this.antefamiliares;
    }
}
