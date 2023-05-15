package com.cetis22.lectorsalud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class RegistroActivity extends AppCompatActivity {
    TextInputLayout TCedula;
    TextInputLayout TLemail;
    TextInputLayout TLhospital;
    TextInputLayout TLnombrecompleto;
    TextInputLayout TLpassword;
    TextInputLayout TLusername;
    Button btnRegistro;
    Button btnUsuaExistente;
    DatabaseReference reference;
    FirebaseDatabase rootNode;
    Boolean correoExiste = false;
    Boolean cedulaExiste = false;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        this.TLnombrecompleto = (TextInputLayout) findViewById(R.id.NomCompletoRegist);
        this.TLusername = (TextInputLayout) findViewById(R.id.usernameRegist);
        this.TLemail = (TextInputLayout) findViewById(R.id.emailRegist);
        this.TLpassword = (TextInputLayout) findViewById(R.id.passwordRegist);
        this.TCedula = (TextInputLayout) findViewById(R.id.cedulaRegist);
        this.TLhospital = (TextInputLayout) findViewById(R.id.txt_hospital_register);
        this.btnRegistro = (Button) findViewById(R.id.btnRegistro);
        this.btnUsuaExistente = (Button) findViewById(R.id.btnUsuaExistente);
        this.btnRegistro.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.RegistroActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RegistroActivity.this.RegistrarUsuario();
            }
        });
        this.btnUsuaExistente.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.RegistroActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RegistroActivity.this.NuevoUsuario();
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, SelectorActivity.class);
        startActivity(i);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void NuevoUsuario() {
        Intent intent = new Intent(this, IniciarSecionActivity.class);
        startActivity(intent);
        finish();
    }

    private Boolean validateName() {
        String val = this.TLnombrecompleto.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.TLnombrecompleto.setError("No puede estar vacio");
            return false;
        }
        this.TLnombrecompleto.setError(null);
        this.TLnombrecompleto.setErrorEnabled(false);
        return true;
    }

    private Boolean validateUsername() {
        String val = this.TLusername.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.TLusername.setError("No puede estar vacio");
            return false;
        } else if (val.length() >= 15) {
            this.TLusername.setError("Solo 15 letras");
            return false;
        } else if (!val.matches("\\A\\w{4,20}\\z")) {
            this.TLusername.setError("Sin espacios");
            return false;
        } else {
            this.TLusername.setError(null);
            this.TLusername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = this.TLemail.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.TLemail.setError("Este campo no puede estar vacio");
            return false;
        } else if (!val.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            this.TLemail.setError("Correo invalido");
            return false;
        } else {
            this.TLnombrecompleto.setError(null);
            this.TLemail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateCedula() {
        if (this.TCedula.getEditText().getText().length() < 8) {
            this.TCedula.setError("Numero de cedula no valida");
            this.TCedula.setErrorEnabled(true);
            return false;
        }
        this.TCedula.setError(null);
        this.TCedula.setErrorEnabled(false);
        return true;
    }

    private Boolean validatePassword() {
        String val = this.TLpassword.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.TLpassword.setError("No puede estar vacio");
            return false;
        } else if (!val.matches("^.*(?=.{8,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$")) {
            this.TLpassword.setError("Contraseña es invalida");
            return false;
        } else {
            this.TLpassword.setError(null);
            this.TLpassword.setErrorEnabled(false);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void RegistrarUsuario() {
        if (!((!validateName().booleanValue()) | (!validateUsername().booleanValue()) | (!validateEmail().booleanValue()) | (!validatePassword().booleanValue()) | (!validateCedula().booleanValue()) | (!validateHospital()))) {
            this.correoExiste = false;
            this.cedulaExiste = false;
            this.btnRegistro.setEnabled(false);
            Object name = this.TLnombrecompleto.getEditText().getText().toString();
            Object username = this.TLusername.getEditText().getText().toString();
            final String email = this.TLemail.getEditText().getText().toString();
            final String password = this.TLpassword.getEditText().getText().toString();
            final String cedula = this.TCedula.getEditText().getText().toString();
            Object hospital = this.TLhospital.getEditText().getText().toString();
            final Map<String, Object> res = new HashMap<>();
            res.put("nombre", name);
            res.put("correo", email);
            res.put("usuario", username);
            res.put("cedula", cedula);
            res.put("hospital", hospital);
            final FirebaseAuth auth = FirebaseAuth.getInstance();
            final FirebaseFirestore db = FirebaseFirestore.getInstance();
            auth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener() { // from class: com.cetis22.lectorsalud.RegistroActivity$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    RegistroActivity.this.m126x35cb6e05(db, cedula, auth, email, password, res, task);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$RegistrarUsuario$1$com-cetis22-lectorsalud-RegistroActivity  reason: not valid java name */
    public /* synthetic */ void m126x35cb6e05(final FirebaseFirestore db, final String cedula, final FirebaseAuth auth, final String email, final String password, final Map res, Task it) {
        if (it.isSuccessful()) {
            List<String> r = ((SignInMethodQueryResult) it.getResult()).getSignInMethods();
            if (r != null && !r.isEmpty()) {
                this.correoExiste = true;
                Toast.makeText(this, "Este correo ya esta registrado", 0).show();
                this.TLemail.setError("Este correo ya se encuentra registrado");
                this.TLemail.setErrorEnabled(true);
                this.btnRegistro.setEnabled(true);
                return;
            }
            db.collection("doctores").document(cedula).get().addOnCompleteListener(new OnCompleteListener() { // from class: com.cetis22.lectorsalud.RegistroActivity$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    RegistroActivity.this.m125x2e6638e6(auth, email, password, db, cedula, res, task);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$RegistrarUsuario$0$com-cetis22-lectorsalud-RegistroActivity  reason: not valid java name */
    public /* synthetic */ void m125x2e6638e6(FirebaseAuth auth, String email, String password, FirebaseFirestore db, String cedula, Map res, Task task) {
        if (((DocumentSnapshot) task.getResult()).exists()) {
            this.cedulaExiste = true;
            this.btnRegistro.setEnabled(true);
            Toast.makeText(this, "Esta cedula ya esta registrada", 0).show();
            this.TCedula.setError("Este numero de cedula ya esta registrado");
            return;
        }
        auth.createUserWithEmailAndPassword(email, password).isSuccessful();
        db.collection("doctores").document(cedula).set(res);
        Toast.makeText(this, "Usuario registrado", 0).show();
    }

    private boolean validateHospital() {
        return this.TLhospital.getEditText().getText().length() > 0;
    }
}
