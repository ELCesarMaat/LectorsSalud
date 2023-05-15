package com.cetis22.lectorsalud;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import com.cetis22.lectorsalud.data.AdaptadorExpediente;
import com.cetis22.lectorsalud.data.Expediente;
import com.cetis22.lectorsalud.databinding.ActivityExpedientePacienteBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ActivityExpedientePaciente extends AppCompatActivity {
    ActivityExpedientePacienteBinding b;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String curp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityExpedientePacienteBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        curp = getIntent().getStringExtra("curp");

        //Toast.makeText(this, getIntent().getStringExtra("curp"), Toast.LENGTH_SHORT).show();
        b.txtExpedienteDe.append(getIntent().getStringExtra("curp"));
        ArrayList<Expediente> expedienteArrayList = new ArrayList<>();
        AdaptadorExpediente adaptadorExpediente = new AdaptadorExpediente(expedienteArrayList);
        b.recyclerViewExpedientes.setAdapter(adaptadorExpediente);
        b.recyclerViewExpedientes.setLayoutManager(new GridLayoutManager(this, 1));
        b.recyclerViewExpedientes.setHasFixedSize(true);

        db.collection("pacientes").document(curp).collection("expedientes").get().addOnCompleteListener(it ->{
            if(it.isSuccessful()){
                for (QueryDocumentSnapshot ds : it.getResult()){
                    expedienteArrayList.add(new Expediente(ds.getId(), ds.getId(), ""));
                    adaptadorExpediente.notifyDataSetChanged();
                }
            }
        });

        adaptadorExpediente.setOnItemClickListener(position -> {
            Intent i = new Intent(this, ListaConsultasActivity.class);
            i.putExtra("curp", curp);
            i.putExtra("hospital", adaptadorExpediente.getExpedientesList().get(position).getId());
            startActivity(i);
            Toast.makeText(ActivityExpedientePaciente.this, adaptadorExpediente.getExpedientesList().get(position).getTitulo(), Toast.LENGTH_SHORT).show();
        });
    }
}
