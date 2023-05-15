package com.cetis22.lectorsalud;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cetis22.lectorsalud.data.AdaptadorPersonas;
import com.cetis22.lectorsalud.data.Personas;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ListaPacientesActivity extends AppCompatActivity {
    AdaptadorPersonas adaptador;
    String afiliado;
    String curp;
    Bitmap imagen;
    ArrayList<Personas> lista_personas;
    String nombre;
    RecyclerView recyclerView;
    TextView txtNoPacientes;
    int cont = 0;
    ArrayList<byte[]> bytesImgList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);
        this.txtNoPacientes = (TextView) findViewById(R.id.tv_lista_vacia);
        this.lista_personas = new ArrayList<>();
        this.adaptador = new AdaptadorPersonas(this.lista_personas);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_pacientes);
        this.recyclerView = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.adaptador);
        dataInitialize();
    }

    void dataInitialize() {
        this.lista_personas = new ArrayList<>();
        CollectionReference collectionRef = this.db.collection("pacientes");
        collectionRef.whereEqualTo("doctor", SessionInfo.getCedula()).get().addOnSuccessListener(new OnSuccessListener() { // from class: com.cetis22.lectorsalud.ListaPacientesActivity$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                ListaPacientesActivity.this.m116x9f831cf7((QuerySnapshot) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$dataInitialize$2$com-cetis22-lectorsalud-ListaPacientesActivity  reason: not valid java name */
    public /* synthetic */ void m116x9f831cf7(QuerySnapshot it) {
        for (DocumentSnapshot document : it.getDocuments()) {
            this.cont++;
            final String curp = document.getData().get("curp").toString();
            final String name = document.getData().get("nombre").toString();
            final String afiliado = document.getData().get("afiliado").toString();
            FirebaseStorage.getInstance().getReference().child(curp + ".jpg").getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener() { // from class: com.cetis22.lectorsalud.ListaPacientesActivity$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    ListaPacientesActivity.this.m114x2bedd939((byte[]) obj);
                }
            }).addOnSuccessListener(new OnSuccessListener() { // from class: com.cetis22.lectorsalud.ListaPacientesActivity$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    ListaPacientesActivity.this.m115x65b87b18(name, afiliado, curp, (byte[]) obj);
                }
            });
        }
        if (this.cont == 0) {
            this.txtNoPacientes.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$dataInitialize$0$com-cetis22-lectorsalud-ListaPacientesActivity  reason: not valid java name */
    public /* synthetic */ void m114x2bedd939(byte[] bytes) {
        this.imagen = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        this.bytesImgList.add(bytes);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$dataInitialize$1$com-cetis22-lectorsalud-ListaPacientesActivity  reason: not valid java name */
    public /* synthetic */ void m115x65b87b18(String name, String afiliado, String curp, byte[] task) {
        this.lista_personas.add(new Personas(name, afiliado, curp, this.imagen));
        AdaptadorPersonas adaptadorPersonas = new AdaptadorPersonas(this.lista_personas);
        this.adaptador = adaptadorPersonas;
        this.recyclerView.setAdapter(adaptadorPersonas);
        this.adaptador.setOnItemClickListener(new AdaptadorPersonas.onItemClickListener() { // from class: com.cetis22.lectorsalud.ListaPacientesActivity.1
            @Override // com.cetis22.lectorsalud.data.AdaptadorPersonas.onItemClickListener
            public void onItemClick(int position) {
                Intent i = new Intent(ListaPacientesActivity.this, InfPACIENTEActivity.class);
                i.putExtra("curp", ListaPacientesActivity.this.lista_personas.get(position).getCurp());
                SessionInfo.setImg_user(ListaPacientesActivity.this.bytesImgList.get(position));
                ListaPacientesActivity.this.startActivity(i);
            }
        });
    }
}
