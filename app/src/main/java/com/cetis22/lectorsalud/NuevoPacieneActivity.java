package com.cetis22.lectorsalud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NuevoPacieneActivity extends AppCompatActivity {

    TextInputLayout nombrecomP, fechanacimientoP, curpP, tiposangreP, afiliadoP, examenultimoP, enferpacienteP, medicamentosconP, dosismedP, fechaconsmediP,fechaulcirujiaP, alergiasP, enfermedadcronicaP, antecfamiliaresP;

    //BTN1 - FUNCIONALIDAD PARA REGISTRAR
    Button BTN1;

    //BTN2 - FUNCIONALIDAD PARA PASAR A SIGUIENTE VENTANA PARA GENERAR EL CODIGO QR.
    Button BTN2;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_paciene);

        nombrecomP = findViewById(R.id.nombrecomP);
        fechanacimientoP = findViewById(R.id.fechanacimientoP);
        curpP = findViewById(R.id.curpP);
        tiposangreP = findViewById(R.id.tiposangreP);
        afiliadoP = findViewById(R.id.afiliadoP);
        examenultimoP = findViewById(R.id.examenultimoP);
        enferpacienteP = findViewById(R.id.enferpacienteP);
        medicamentosconP = findViewById(R.id.medicamentosconP);
        dosismedP = findViewById(R.id.dosismedP);
        fechaconsmediP = findViewById(R.id.fechaconsmediP);
        fechaulcirujiaP = findViewById(R.id.fechaulcirujiaP);
        alergiasP = findViewById(R.id.alergiasP);
        enfermedadcronicaP = findViewById(R.id.enfermedadcronicaP);
        antecfamiliaresP = findViewById(R.id.antecfamiliaresP);



        BTN1 = findViewById(R.id.BTN1);
        BTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarDatosPacienteNuevo();
            }
        });

        BTN2 = findViewById(R.id.BTN2);
        BTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrVentanaGeneradorQR();
            }
        });
    }

    private void IrVentanaGeneradorQR() {
        Intent intent = new Intent(new Intent(NuevoPacieneActivity.this, QRgeneradoActivity.class));
        startActivity(intent);
    }

    private Boolean validateNombreCompleto(){
        String val = nombrecomP.getEditText().getText().toString();

        if (val.isEmpty()){
            nombrecomP.setError("No puede estar vacio");
            return false;
        }
        else{
            nombrecomP.setError(null);
            nombrecomP.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateFechadenacimiento(){
        String val = fechanacimientoP.getEditText().getText().toString();

        if (val.isEmpty()){
            fechanacimientoP.setError("No puede estar vacio");
            return false;
        }
        else{
            fechanacimientoP.setError(null);
            fechanacimientoP.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatecurp(){
        String val = curpP.getEditText().getText().toString();

        if (val.isEmpty()){
            curpP.setError("No puede estar vacio");
            return false;
        }
        else{
            curpP.setError(null);
            curpP.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatetiposangre(){
        String val = tiposangreP.getEditText().getText().toString();

        if (val.isEmpty()){
            tiposangreP.setError("No puede estar vacio");
            return false;
        }
        else{
            tiposangreP.setError(null);
            tiposangreP.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateafiliado(){
        String val = afiliadoP.getEditText().getText().toString();

        if (val.isEmpty()){
            afiliadoP.setError("No puede estar vacio");
            return false;
        }
        else{
            afiliadoP.setError(null);
            afiliadoP.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatefechaultimoexamen(){
        String val = examenultimoP.getEditText().getText().toString();

        if (val.isEmpty()){
            examenultimoP.setError("No puede estar vacio");
            return false;
        }
        else{
            examenultimoP.setError(null);
            examenultimoP.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateenfermedadesdepaciente(){
        String val = enferpacienteP.getEditText().getText().toString();

        if (val.isEmpty()){
            enferpacienteP.setError("No puede estar vacio");
            return false;
        }
        else{
            enferpacienteP.setError(null);
            enferpacienteP.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatemedicamentosconsumidos(){
        String val = medicamentosconP.getEditText().getText().toString();

        if (val.isEmpty()){
            medicamentosconP.setError("No puede estar vacio");
            return false;
        }
        else{
            medicamentosconP.setError(null);
            medicamentosconP.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatedosisdemedicamentos(){
        String val = dosismedP.getEditText().getText().toString();

        if (val.isEmpty()){
            dosismedP.setError("No puede estar vacio");
            return false;
        }
        else{
            dosismedP.setError(null);
            dosismedP.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateultimafechaconsumomedicamento(){
        String val = fechaconsmediP.getEditText().getText().toString();

        if (val.isEmpty()){
            fechaconsmediP.setError("No puede estar vacio");
            return false;
        }
        else{
            fechaconsmediP.setError(null);
            fechaconsmediP.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatefechadesuultimacirujia(){
        String val = fechaulcirujiaP.getEditText().getText().toString();

        if (val.isEmpty()){
            fechaulcirujiaP.setError("No puede estar vacio");
            return false;
        }
        else{
            fechaulcirujiaP.setError(null);
            fechaulcirujiaP.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatealergia(){
        String val = alergiasP.getEditText().getText().toString();

        if (val.isEmpty()){
            alergiasP.setError("No puede estar vacio");
            return false;
        }
        else{
            alergiasP.setError(null);
            alergiasP.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateenfermedadescronicas(){
        String val = enfermedadcronicaP.getEditText().getText().toString();

        if (val.isEmpty()){
            enfermedadcronicaP.setError("No puede estar vacio");
            return false;
        }
        else{
            enfermedadcronicaP.setError(null);
            enfermedadcronicaP.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateantecedentesfamiliares(){
        String val = antecfamiliaresP.getEditText().getText().toString();

        if (val.isEmpty()){
            antecfamiliaresP.setError("No puede estar vacio");
            return false;
        }
        else{
            antecfamiliaresP.setError(null);
            antecfamiliaresP.setErrorEnabled(false);
            return true;
        }
    }

    private void RegistrarDatosPacienteNuevo() {
        if (!validateNombreCompleto() | !validateFechadenacimiento() | !validatecurp()
                | !validatetiposangre() | !validateafiliado() | !validatefechaultimoexamen()
                | !validateenfermedadesdepaciente() | !validatemedicamentosconsumidos() | !validatedosisdemedicamentos() |
                !validateultimafechaconsumomedicamento() | !validatefechadesuultimacirujia() | !validatealergia() | !validateenfermedadescronicas() |
                !validateantecedentesfamiliares()){
            return;
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String nombrecompleto = nombrecomP.getEditText().getText().toString();
        String fechadenacimiento = fechanacimientoP.getEditText().getText().toString();
        String curp = curpP.getEditText().getText().toString();
        String tipodesangre = tiposangreP.getEditText().getText().toString();
        String fechaultexamen = examenultimoP.getEditText().getText().toString();
        String enfermedadesp = enferpacienteP.getEditText().getText().toString();
        String medicamentoscons = medicamentosconP.getEditText().getText().toString();
        String dosismedicamentos = dosismedP.getEditText().getText().toString();
        String ufechacons = fechaconsmediP.getEditText().getText().toString();
        String fechacirujiault = fechaulcirujiaP.getEditText().getText().toString();
        String alergias = alergiasP.getEditText().getText().toString();
        String enfercronicas = enfermedadcronicaP.getEditText().getText().toString();
        String antefamiliares = antecfamiliaresP.getEditText().getText().toString();
        String afiliado = afiliadoP.getEditText().getText().toString();

        Map<String, Object> res = new HashMap<>();
        res.put("nombre", nombrecompleto);
        res.put("fecha_nacimienti", fechadenacimiento);
        res.put("curp", curp);
        res.put("tipo_de_sangre", tipodesangre);
        res.put("fech_ultimo_examen", fechaultexamen);
        res.put("enfermedades", enfermedadesp);
        res.put("medicamentos_consumidos", medicamentoscons);
        res.put("dosis_medicamentos", dosismedicamentos);
        res.put("fecha_ultimo_medicamento", ufechacons);
        res.put("fecha_ultima_cirujia", fechacirujiault);
        res.put("alergias", alergias);
        res.put("enfermedades_cronicas", enfercronicas);
        res.put("antecedentes_familiares", antefamiliares);
        res.put("afiliado", afiliado);

        db.collection("doctores").document(SessionInfo.getCedula())
                .collection("pacientes").document(curp).set(res).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(NuevoPacieneActivity.this, PantallaInicio.class);
                        Toast.makeText(this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });



    }

}