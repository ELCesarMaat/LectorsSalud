package com.cetis22.lectorsalud;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes5.dex */
public class PantallaInicio extends AppCompatActivity {
    Button btnActivityEsacaneo;
    Button btnAgrearpaciente;
    Button btnCerrarSesion;
    Button btnMisPacientes;
    Intent intent;
    TextView txtDoctorNombre;
    TextView txtHospital;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);
        TextView textView = (TextView) findViewById(R.id.txt_hospital_principal);
        this.txtHospital = textView;
        textView.setText(SessionInfo.getHospital());
        TextView textView2 = (TextView) findViewById(R.id.txt_nombre_doctor_principal);
        this.txtDoctorNombre = textView2;
        textView2.setText(SessionInfo.getName());
        Button button = (Button) findViewById(R.id.btnIrAlectorcodigo);
        this.btnActivityEsacaneo = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.PantallaInicio.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PantallaInicio.this.intent = new Intent(PantallaInicio.this, LectorCodigoActivity.class);
                PantallaInicio pantallaInicio = PantallaInicio.this;
                pantallaInicio.startActivity(pantallaInicio.intent);
            }
        });
        Button button2 = (Button) findViewById(R.id.btnAgrearpaciente);
        this.btnAgrearpaciente = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.PantallaInicio.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PantallaInicio.this.intent = new Intent(PantallaInicio.this, NuevoPacieneActivity.class);
                PantallaInicio pantallaInicio = PantallaInicio.this;
                pantallaInicio.startActivity(pantallaInicio.intent);
            }
        });
        Button button3 = (Button) findViewById(R.id.btn_mis_pacientes);
        this.btnMisPacientes = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.PantallaInicio$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PantallaInicio.this.m119lambda$onCreate$0$comcetis22lectorsaludPantallaInicio(view);
            }
        });
        Button button4 = (Button) findViewById(R.id.btn_cerrar_sesion);
        this.btnCerrarSesion = button4;
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.PantallaInicio$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PantallaInicio.this.m120lambda$onCreate$1$comcetis22lectorsaludPantallaInicio(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-cetis22-lectorsalud-PantallaInicio  reason: not valid java name */
    public /* synthetic */ void m119lambda$onCreate$0$comcetis22lectorsaludPantallaInicio(View view) {
        Intent intent = new Intent(this, ListaPacientesActivity.class);
        this.intent = intent;
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$1$com-cetis22-lectorsalud-PantallaInicio  reason: not valid java name */
    public /* synthetic */ void m120lambda$onCreate$1$comcetis22lectorsaludPantallaInicio(View it) {
        Intent intent = new Intent(this, SelectorActivity.class);
        this.intent = intent;
        startActivity(intent);
        SharedPreferences prefs = getSharedPreferences("user_data", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        finish();
    }
}
