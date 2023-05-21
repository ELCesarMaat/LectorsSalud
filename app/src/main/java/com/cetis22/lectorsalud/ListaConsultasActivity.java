package com.cetis22.lectorsalud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cetis22.lectorsalud.data.AdaptadorExpediente;
import com.cetis22.lectorsalud.data.Expediente;
import com.cetis22.lectorsalud.databinding.ActivityListaConsultasBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class ListaConsultasActivity extends AppCompatActivity {

    ActivityListaConsultasBinding b;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String curp;
    String hospital;

    boolean grid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = ActivityListaConsultasBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        curp = getIntent().getStringExtra("curp");
        hospital = getIntent().getStringExtra("hospital");

        Toast.makeText(this, curp + " " + hospital, Toast.LENGTH_SHORT).show();

        ArrayList<Expediente> arrayList = new ArrayList<>();
        AdaptadorExpediente adapter = new AdaptadorExpediente(arrayList);
        b.recyclerViewCitas.setAdapter(adapter);
        b.recyclerViewCitas.setLayoutManager(new GridLayoutManager(this, 2));
        b.recyclerViewCitas.setHasFixedSize(true);

        b.btnAjustar.setOnClickListener(view -> {
            if(grid)
                b.recyclerViewCitas.setLayoutManager(new GridLayoutManager(this, 1));
            else
                b.recyclerViewCitas.setLayoutManager(new GridLayoutManager(this, 2));

            grid = !grid;
        });

        adapter.setOnItemClickListener(i -> {
            Toast.makeText(ListaConsultasActivity.this, adapter.getExpedientesList().get(i).getId(), Toast.LENGTH_SHORT).show();
        });

        db.collection("pacientes").document(curp).collection("expedientes").document(hospital).collection("consultas").get().addOnCompleteListener(it ->{
           if (it.isSuccessful()){
               for (QueryDocumentSnapshot ds : it.getResult()){
                   arrayList.add(new Expediente(ds.getId(), ds.getString("motivo"), ds.getString("fecha")));
                   adapter.notifyDataSetChanged();
               }

            }
        });

    }
}