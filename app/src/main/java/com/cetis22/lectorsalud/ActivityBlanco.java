package com.cetis22.lectorsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityBlanco extends AppCompatActivity {

    Button btnPrueba;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blanco);

        btnPrueba = findViewById(R.id.btnprueba);
        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccionbtnPrueba();
            }
        });

    }

    private void AccionbtnPrueba() {
        Toast.makeText(this, "Bienvenido a Actblanco", Toast.LENGTH_SHORT).show();
    }
}