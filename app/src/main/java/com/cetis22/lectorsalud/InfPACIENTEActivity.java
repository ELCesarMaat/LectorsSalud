package com.cetis22.lectorsalud;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cetis22.lectorsalud.databinding.ActivityInfPacienteactivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

/* loaded from: classes5.dex */
public class InfPACIENTEActivity extends AppCompatActivity {
    ActivityInfPacienteactivityBinding b;
    String curp;
    Bitmap imagen;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityInfPacienteactivityBinding inflate = ActivityInfPacienteactivityBinding.inflate(getLayoutInflater());
        this.b = inflate;
        setContentView(inflate.getRoot());
        this.curp = getIntent().getExtras().getString("curp");
        FirebaseFirestore.getInstance().collection("pacientes").document(this.curp).get().addOnCompleteListener(new OnCompleteListener() { // from class: com.cetis22.lectorsalud.InfPACIENTEActivity$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                InfPACIENTEActivity.this.m108lambda$onCreate$1$comcetis22lectorsaludInfPACIENTEActivity(task);
            }
        });
        this.b.btnMostarExpedientes.setOnClickListener(view -> { // from class: com.cetis22.lectorsalud.InfPACIENTEActivity$$ExternalSyntheticLambda1
            Intent i = new Intent(this, ActivityExpedientePaciente.class);
            i.putExtra("curp", this.curp);
            startActivity(i);
        });
        this.b.btnNuevoExpediente.setOnClickListener(view -> {
            Intent i = new Intent(this, NuevaConsulta.class);
            i.putExtra("curp", this.curp);
            startActivity(i);
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$1$com-cetis22-lectorsalud-InfPACIENTEActivity  reason: not valid java name */
    public /* synthetic */ void m108lambda$onCreate$1$comcetis22lectorsaludInfPACIENTEActivity(Task it) {
        if (it.isSuccessful()) {
            DocumentSnapshot ds = (DocumentSnapshot) it.getResult();
            this.b.txtTituloNombre.setText(ds.getString("nombre"));
            this.b.txtCorreo.setText(ds.getString("correo"));
            this.b.txtNombre.getEditText().setText(ds.getString("nombre"));
            this.b.txtFechaNacimiento.getEditText().setText(ds.getString("fecha_nacimienti"));
            this.b.txtCurp.getEditText().setText(ds.getString("curp"));
            this.b.txtSangre.getEditText().setText(ds.getString("tipo_de_sangre"));
            this.b.txtUltimoExamen.getEditText().setText(ds.getString("fech_ultimo_examen"));
            this.b.txtEnfermedades.getEditText().setText(ds.getString("enfermedades"));
            this.b.txtmedicamentos.getEditText().setText(ds.getString("medicamentos_consumidos"));
            this.b.txtDosis.getEditText().setText(ds.getString("dosis_medicamentos"));
            this.b.txtFechaUltimoMedicamento.getEditText().setText(ds.getString("fecha_ultimo_medicamento"));
            this.b.txtFechaUltimaCirujia.getEditText().setText(ds.getString("fecha_ultima_cirujia"));
            this.b.txtAlergias.getEditText().setText(ds.getString("alergias"));
            this.b.txtEnfermadesCronicas.getEditText().setText(ds.getString("enfermedades_cronicas"));
            this.b.txtAntecedentes.setText(ds.getString("antecedentes_familiares"));
            FirebaseStorage.getInstance().getReference().child(this.curp + ".jpg").getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener() { // from class: com.cetis22.lectorsalud.InfPACIENTEActivity$$ExternalSyntheticLambda3
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    InfPACIENTEActivity.this.m107lambda$onCreate$0$comcetis22lectorsaludInfPACIENTEActivity((byte[]) obj);
                }
            });
            Toast.makeText(this, ((DocumentSnapshot) it.getResult()).getString("nombre"), Toast.LENGTH_SHORT).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-cetis22-lectorsalud-InfPACIENTEActivity  reason: not valid java name */
    public /* synthetic */ void m107lambda$onCreate$0$comcetis22lectorsaludInfPACIENTEActivity(byte[] ok) {
        this.imagen = BitmapFactory.decodeByteArray(ok, 0, ok.length);
        this.b.profileImageJ.setImageBitmap(this.imagen);
    }
}
