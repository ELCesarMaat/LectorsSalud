package com.cetis22.lectorsalud;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes5.dex */
public class SelectorActivity extends AppCompatActivity {
    Button ISecion;
    Button RegSecion;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        SharedPreferences prefs = getSharedPreferences("user_data", 0);
        if (!prefs.getAll().isEmpty()) {
            SessionInfo.setCedula(prefs.getString("cedula", ""));
            SessionInfo.setEmail(prefs.getString("email", ""));
            SessionInfo.setUsername(prefs.getString("usuario", ""));
            SessionInfo.setName(prefs.getString("nombre", ""));
            SessionInfo.setHospital(prefs.getString("hospital", ""));
            Intent i = new Intent(this, PantallaInicio.class);
            startActivity(i);
            finish();
        }
        Button button = (Button) findViewById(R.id.btnSelectorIniciarSecion);
        this.ISecion = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.SelectorActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SelectorActivity.this.ISeccionprincipal();
            }
        });
        Button button2 = (Button) findViewById(R.id.btnSelectorRegistrarte);
        this.RegSecion = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.SelectorActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SelectorActivity.this.RegSeccionpricipal();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ISeccionprincipal() {
        Intent intent = new Intent(this, IniciarSecionActivity.class);
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void RegSeccionpricipal() {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
        finish();
    }
}
