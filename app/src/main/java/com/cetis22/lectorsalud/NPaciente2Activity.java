package com.cetis22.lectorsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NPaciente2Activity extends AppCompatActivity {

    Button BTN2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npaciente2);

        BTN2 = findViewById(R.id.BTN2);
        BTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NPaciente2Activity.this, NPaciente3Activity.class);
                startActivity(intent);
            }
        });

    }
}