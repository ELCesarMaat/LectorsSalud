package com.cetis22.lectorsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NPaciente5Activity extends AppCompatActivity {
    Button BTN5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npaciente5);

        BTN5 = findViewById(R.id.BTN5);
        BTN5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NPaciente5Activity.this, GeneradorQRActivity.class);
                startActivity(intent);
            }
        });

    }
}