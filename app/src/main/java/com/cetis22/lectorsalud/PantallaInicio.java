package com.cetis22.lectorsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PantallaInicio extends AppCompatActivity {

    Intent intent;
    Button btnActivityEsacaneo;
    Button btnAgrearpaciente;
    Button btnMisPacientes;
    Button btnCerrarSesion;
    TextView txtDoctorNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);


        txtDoctorNombre = findViewById(R.id.txt_nombre_doctor_principal);
        txtDoctorNombre.setText(SessionInfo.getName());

        btnActivityEsacaneo = findViewById(R.id.btnIrAlectorcodigo);
        btnActivityEsacaneo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(PantallaInicio.this, LectorCodigoActivity.class);
                startActivity(intent);
            }
        });

        btnAgrearpaciente = findViewById(R.id.btnAgrearpaciente);
        btnAgrearpaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(PantallaInicio.this, NuevoPacieneActivity.class);
                startActivity(intent);
            }
        });

        btnMisPacientes = findViewById(R.id.btn_mis_pacientes);
        btnMisPacientes.setOnClickListener(view -> {
            intent = new Intent(this, ListaPacientesActivity.class);
            startActivity(intent);
        });

        btnCerrarSesion = findViewById(R.id.btn_cerrar_sesion);
        btnCerrarSesion.setOnClickListener(it -> {
            intent = new Intent(this, SelectorActivity.class);
            startActivity(intent);
            SharedPreferences prefs = getSharedPreferences("user_data", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();
            finish();
        });
    }
}