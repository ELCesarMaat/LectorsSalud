package com.cetis22.lectorsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectorActivity extends AppCompatActivity {

    Button ISecion;
    Button RegSecion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        SharedPreferences prefs = getSharedPreferences("user_data", MODE_PRIVATE);
        if(!prefs.getAll().isEmpty()){
            SessionInfo.setCedula(prefs.getString("cedula", ""));
            SessionInfo.setEmail(prefs.getString("email" ,""));
            SessionInfo.setUsername(prefs.getString("usuario", ""));
            SessionInfo.setName(prefs.getString("nombre", ""));
            Intent i = new Intent(this, PantallaInicio.class);
            startActivity(i);
            finish();
        }

        ISecion = findViewById(R.id.btnSelectorIniciarSecion);
        ISecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ISeccionprincipal();
            }
        });

        RegSecion = findViewById(R.id.btnSelectorRegistrarte);
        RegSecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegSeccionpricipal();
            }
        });

    }

    private void ISeccionprincipal(){
        Intent intent = new Intent(SelectorActivity.this, IniciarSecionActivity.class);
        startActivity(intent);
        finish();
    }

    private void RegSeccionpricipal(){
        Intent intent = new Intent(SelectorActivity.this, RegistroActivity.class);
        startActivity(intent);
        finish();
    }
}