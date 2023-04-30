package com.cetis22.lectorsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NPaciente3Activity extends AppCompatActivity {

    Button BTN3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npaciente3);

        BTN3 = findViewById(R.id.BTN3);
        BTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NPaciente3Activity.this, NPaciente5Activity.class);
                startActivity(intent);
            }
        });

    }
}