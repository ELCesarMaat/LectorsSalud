package com.cetis22.lectorsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityBienvenida extends AppCompatActivity {
    
    Button btnIS;
    TextView textviewregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        
        btnIS = findViewById(R.id.btnIS);
        btnIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccionbtnIS();
            }
        });

        textviewregister = findViewById(R.id.textviewregister);
        textviewregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccionTextViewRegister();
            }
        });

    }


    private void AccionbtnIS() {
        Intent intent = new Intent(ActivityBienvenida.this, IniciarSecionActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Llena los campos!", Toast.LENGTH_SHORT).show();
    }

    private void AccionTextViewRegister() {
        Toast.makeText(this, "SOLICITUD DE REGITRO", Toast.LENGTH_SHORT).show();
    }
}
