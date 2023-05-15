package com.cetis22.lectorsalud;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cetis22.lectorsalud.databinding.ActivityNuevaConsultaBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class NuevaConsulta extends AppCompatActivity {
    ActivityNuevaConsultaBinding b;
    String curp;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityNuevaConsultaBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        curp = getIntent().getStringExtra("curp");

        Toast.makeText(this, curp, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, SessionInfo.getHospital(), Toast.LENGTH_SHORT).show();
        Map<String, Object> res = new HashMap<>();
        b.btnGuardarConsulta.setOnClickListener(view -> {
            res.put("motivo", b.txtMotivo.getText().toString());
            res.put("fecha", "01/01/01");
            db.collection("pacientes").document(curp).collection("expedientes").document(SessionInfo.getHospital()).collection("consultas").add(res);
        });
    }
}
