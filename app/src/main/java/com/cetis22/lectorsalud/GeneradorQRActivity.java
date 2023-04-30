package com.cetis22.lectorsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GeneradorQRActivity extends AppCompatActivity {

    Button BTNgenerador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generador_qractivity);

        BTNgenerador= findViewById(R.id.BTNgenerador);
        BTNgenerador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeneradorQRActivity.this, QRgeneradoActivity.class);
                startActivity(intent);
            }
        });

    }
}