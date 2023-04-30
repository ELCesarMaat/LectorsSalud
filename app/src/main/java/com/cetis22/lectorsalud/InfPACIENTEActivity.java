package com.cetis22.lectorsalud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.cetis22.lectorsalud.databinding.ActivityInfPacienteactivityBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class InfPACIENTEActivity extends AppCompatActivity {

    ActivityInfPacienteactivityBinding b;
    String curp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityInfPacienteactivityBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        curp = getIntent().getExtras().getString("curp");

        FirebaseFirestore.getInstance().collection("doctores").document(SessionInfo.getCedula()).collection("pacientes").document(curp).get().addOnCompleteListener(it -> {
            if (it.isSuccessful()){
                DocumentSnapshot ds = it.getResult();
                b.txtTituloNombre.setText(ds.getString("nombre"));
                b.txtCorreo.setText(ds.getString("correo"));
                b.txtNombre.getEditText().setText(ds.getString("nombre"));
                b.txtFechaNacimiento.getEditText().setText(ds.getString("fecha_nacimienti"));
                b.txtCurp.getEditText().setText(ds.getString("curp"));
                b.txtSangre.getEditText().setText(ds.getString("tipo_de_sangre"));
                b.txtUltimoExamen.getEditText().setText(ds.getString("fech_ultimo_examen"));
                b.txtEnfermedades.getEditText().setText(ds.getString("enfermedades"));
                b.txtmedicamentos.getEditText().setText(ds.getString("medicamentos_consumidos"));
                b.txtDosis.getEditText().setText(ds.getString("dosis_medicamentos"));
                b.txtFechaUltimoMedicamento.getEditText().setText(ds.getString("fecha_ultimo_medicamento"));
                b.txtFechaUltimaCirujia.getEditText().setText(ds.getString("fecha_ultima_cirujia"));
                b.txtAlergias.getEditText().setText(ds.getString("alergias"));
                b.txtEnfermadesCronicas.getEditText().setText(ds.getString("enfermedades_cronicas"));
                b.txtAntecedentes.setText(ds.getString("antecedentes_familiares"));

                Toast.makeText(this, it.getResult().getString("nombre"), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
