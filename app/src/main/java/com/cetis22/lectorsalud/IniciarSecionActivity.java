package com.cetis22.lectorsalud;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.cetis22.lectorsalud.IniciarSecionActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

/* loaded from: classes5.dex */
public class IniciarSecionActivity extends AppCompatActivity {
    Button btnIniciarseccion;
    String cedula;

    /* renamed from: contraseñaIS  reason: contains not printable characters */
    TextInputLayout f0contraseaIS;
    String hospital;
    String nombre;
    String usuario;
    TextInputLayout usuarioIS;

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, SelectorActivity.class);
        startActivity(i);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_secion);
        this.btnIniciarseccion = (Button) findViewById(R.id.btnIniciarseccion);
        this.usuarioIS = (TextInputLayout) findViewById(R.id.usuarioIS);
        this.f0contraseaIS = (TextInputLayout) findViewById(R.id.contraseñaIS);
        this.btnIniciarseccion.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.IniciarSecionActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IniciarSecionActivity.this.Iniciarseccion();
            }
        });
        this.usuarioIS.getEditText().addTextChangedListener(new TextWatcher() { // from class: com.cetis22.lectorsalud.IniciarSecionActivity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                IniciarSecionActivity.this.usuarioIS.setError(null);
                IniciarSecionActivity.this.usuarioIS.setErrorEnabled(false);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private Boolean validateUsername() {
        String val = this.usuarioIS.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.usuarioIS.setError("No puede estar vacio");
            return false;
        }
        this.usuarioIS.setError(null);
        this.usuarioIS.setErrorEnabled(false);
        return true;
    }

    private Boolean validatePassword() {
        String val = this.f0contraseaIS.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.f0contraseaIS.setError("No puede estar vacio");
            return false;
        }
        this.f0contraseaIS.setError(null);
        this.f0contraseaIS.setErrorEnabled(false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Iniciarseccion() {
        if ((!validateUsername().booleanValue()) | (!validatePassword().booleanValue())) {
            return;
        }
        isUser();
    }

    private void isUser() {
        String userEnteredUsername = this.usuarioIS.getEditText().getText().toString().trim();
        String userEnteredPassword = this.f0contraseaIS.getEditText().getText().toString().trim();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        auth.signInWithEmailAndPassword(userEnteredUsername, userEnteredPassword).addOnCompleteListener(new AnonymousClass3(db, userEnteredUsername));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.cetis22.lectorsalud.IniciarSecionActivity$3  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass3 implements OnCompleteListener<AuthResult> {
        final /* synthetic */ FirebaseFirestore val$db;
        final /* synthetic */ String val$userEnteredUsername;

        AnonymousClass3(FirebaseFirestore firebaseFirestore, String str) {
            this.val$db = firebaseFirestore;
            this.val$userEnteredUsername = str;
        }

        @Override // com.google.android.gms.tasks.OnCompleteListener
        public void onComplete(Task<AuthResult> task) {
            if (task.isSuccessful()) {
                Task<QuerySnapshot> task2 = this.val$db.collection("doctores").whereEqualTo("correo", this.val$userEnteredUsername).get();
                final String str = this.val$userEnteredUsername;
                task2.addOnCompleteListener(new OnCompleteListener() { // from class: com.cetis22.lectorsalud.IniciarSecionActivity$3$$ExternalSyntheticLambda0
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public final void onComplete(Task task3) {
                        IniciarSecionActivity.AnonymousClass3.this.m111xbd538003(str, task3);
                    }
                });
                return;
            }
            IniciarSecionActivity.this.usuarioIS.setError("Usuario no registrado");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onComplete$0$com-cetis22-lectorsalud-IniciarSecionActivity$3  reason: not valid java name */
        public /* synthetic */ void m111xbd538003(String userEnteredUsername, Task it) {
            if (it.isSuccessful()) {
                for (DocumentSnapshot ds : ((QuerySnapshot) it.getResult()).getDocuments()) {
                    if (ds.getString("correo").equals(userEnteredUsername)) {
                        IniciarSecionActivity.this.cedula = ds.getId().toString();
                        IniciarSecionActivity.this.nombre = ds.getString("nombre");
                        IniciarSecionActivity.this.usuario = ds.getString("usuario");
                        IniciarSecionActivity.this.hospital = ds.getString("hospital");
                        SharedPreferences prefs = IniciarSecionActivity.this.getSharedPreferences("user_data", 0);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("email", userEnteredUsername);
                        editor.putString("cedula", IniciarSecionActivity.this.cedula);
                        editor.putString("nombre", IniciarSecionActivity.this.nombre);
                        editor.putString("usuario", IniciarSecionActivity.this.usuario);
                        editor.putString("hospital", IniciarSecionActivity.this.hospital);
                        editor.commit();
                        SessionInfo.setName(IniciarSecionActivity.this.nombre);
                        SessionInfo.setEmail(userEnteredUsername);
                        SessionInfo.setUsername(IniciarSecionActivity.this.usuario);
                        SessionInfo.setCedula(IniciarSecionActivity.this.cedula);
                        SessionInfo.setHospital(IniciarSecionActivity.this.hospital);
                        Intent intent = new Intent(IniciarSecionActivity.this.getApplicationContext(), PantallaInicio.class);
                        IniciarSecionActivity.this.startActivity(intent);
                        IniciarSecionActivity.this.finish();
                    }
                }
            }
        }
    }
}
