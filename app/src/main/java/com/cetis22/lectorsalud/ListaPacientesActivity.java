package com.cetis22.lectorsalud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cetis22.lectorsalud.data.AdaptadorPersonas;
import com.cetis22.lectorsalud.data.Personas;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ListaPacientesActivity extends AppCompatActivity {

    int cont = 0;
    RecyclerView recyclerView;
    ArrayList<Personas> lista_personas;
    AdaptadorPersonas adaptador;

    TextView txtNoPacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);

        txtNoPacientes = findViewById(R.id.tv_lista_vacia);

        lista_personas = new ArrayList();
        adaptador = new AdaptadorPersonas(lista_personas);
        recyclerView = findViewById(R.id.recycler_view_pacientes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptador);

        Log.d("SessionData", SessionInfo.getCedula());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("doctores").document(SessionInfo.getCedula()).collection("pacientes").get().addOnCompleteListener(it -> {
            if(it.isSuccessful()){
                for(DocumentSnapshot ds : it.getResult().getDocuments()){
                    cont++;
                    lista_personas.add(new Personas(ds.getString("nombre"), ds.getString("afiliado"), ds.getString("curp"), null));
                    adaptador = new AdaptadorPersonas(lista_personas);
                    recyclerView.setAdapter(adaptador);
                    adaptador.setOnItemClickListener(new AdaptadorPersonas.onItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Toast.makeText(ListaPacientesActivity.this, lista_personas.get(position).getCurp(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(ListaPacientesActivity.this, InfPACIENTEActivity.class);
                            i.putExtra("curp", lista_personas.get(position).getCurp());
                            startActivity(i);
                        }
                    });


                    Log.d("nombre", ds.getString("nombre"));
                    Log.d("afiliado", ds.getString("afiliado"));
                    Log.d("curp", ds.getString("curp"));
                }

                if(cont==0){
                    if (recyclerView.getChildCount() == 0) {
                        txtNoPacientes.setVisibility(ViewGroup.VISIBLE);
                    }

                    Toast.makeText(this, "No tiene pacientes registrados", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}