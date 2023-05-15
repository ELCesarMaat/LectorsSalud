package com.cetis22.lectorsalud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes5.dex */
public class ActivityBienvenida extends AppCompatActivity {
    Button btnIS;
    TextView textviewregister;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        Button button = (Button) findViewById(R.id.btnIS);
        this.btnIS = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.ActivityBienvenida.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ActivityBienvenida.this.AccionbtnIS();
            }
        });
        TextView textView = (TextView) findViewById(R.id.textviewregister);
        this.textviewregister = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.ActivityBienvenida.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ActivityBienvenida.this.AccionTextViewRegister();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void AccionbtnIS() {
        Intent intent = new Intent(this, IniciarSecionActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Llena los campos!", Toast.LENGTH_SHORT).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void AccionTextViewRegister() {
        Toast.makeText(this, "SOLICITUD DE REGITRO", 0).show();
    }
}
