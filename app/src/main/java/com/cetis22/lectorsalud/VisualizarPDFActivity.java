package com.cetis22.lectorsalud;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class VisualizarPDFActivity extends AppCompatActivity {
    private ArrayList<String> libros;
    private ListView listView;
    private StorageReference mStroge;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_pdfactivity);
        this.listView = (ListView) findViewById(R.id.listview);
        this.libros = new ArrayList<>();
        StorageReference reference = FirebaseStorage.getInstance().getReference();
        this.mStroge = reference;
        StorageReference ref = reference.child("JORGEpaciente");
        ref.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() { // from class: com.cetis22.lectorsalud.VisualizarPDFActivity.1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(ListResult listResult) {
                for (StorageReference item : listResult.getItems()) {
                    VisualizarPDFActivity.this.libros.add(item.getName() + "");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(VisualizarPDFActivity.this.getApplicationContext(), 17367043, VisualizarPDFActivity.this.libros);
                VisualizarPDFActivity.this.listView.setAdapter((ListAdapter) adapter);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.cetis22.lectorsalud.VisualizarPDFActivity$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                VisualizarPDFActivity.this.m127lambda$onCreate$0$comcetis22lectorsaludVisualizarPDFActivity(exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-cetis22-lectorsalud-VisualizarPDFActivity  reason: not valid java name */
    public /* synthetic */ void m127lambda$onCreate$0$comcetis22lectorsaludVisualizarPDFActivity(Exception e) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Ha ocurrido un error al cargar el documento. Revisa Tu conexion a Internet");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() { // from class: com.cetis22.lectorsalud.VisualizarPDFActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
