package com.cetis22.lectorsalud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.model.FieldIndex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    Boolean correoExiste = false;
    Boolean cedulaExiste = false;
    //LOGICA PARA REGISTRAR LOS USUARIOS

    TextInputLayout TCedula;
    TextInputLayout TLnombrecompleto;
    TextInputLayout TLusername;
    TextInputLayout TLemail;
    TextInputLayout TLpassword;

    Button btnRegistro;
    Button btnUsuaExistente;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        TLnombrecompleto = findViewById(R.id.NomCompletoRegist);
        TLusername = findViewById(R.id.usernameRegist);
        TLemail = findViewById(R.id.emailRegist);
        TLpassword = findViewById(R.id.passwordRegist);
        TCedula = findViewById(R.id.cedulaRegist);
        btnRegistro = findViewById(R.id.btnRegistro);
        btnUsuaExistente = findViewById(R.id.btnUsuaExistente);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarUsuario();
            }
        });

        btnUsuaExistente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NuevoUsuario();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, SelectorActivity.class);
        startActivity(i);
        finish();
    }

    private void NuevoUsuario() {
        Intent intent = new Intent(this, IniciarSecionActivity.class);
        startActivity(intent);
        finish();
    }

    private Boolean validateName() {
        String val = TLnombrecompleto.getEditText().getText().toString();

        if (val.isEmpty()) {
            TLnombrecompleto.setError("No puede estar vacio");
            return false;
        } else {
            TLnombrecompleto.setError(null);
            TLnombrecompleto.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = TLusername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z"; //"(?=\\s+$)"

        if (val.isEmpty()) {
            TLusername.setError("No puede estar vacio");
            return false;
        } else if (val.length() >= 15) {
            TLusername.setError("Solo 15 letras");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            TLusername.setError("Sin espacios");
            return false;
        } else {
            TLusername.setError(null);
            TLusername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = TLemail.getEditText().getText().toString();
        String emailPattrn = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            TLemail.setError("Este campo no puede estar vacio");
            return false;
        } else if (!val.matches(emailPattrn)) {
            TLemail.setError("Correo invalido");
            return false;
        } else {
            TLnombrecompleto.setError(null);
            TLemail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateCedula() {

        if(TCedula.getEditText().getText().length() < 8){
            TCedula.setError("Numero de cedula no valida");
            TCedula.setErrorEnabled(true);
            return false;
        }
        else{
            TCedula.setError(null);
            TCedula.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = TLpassword.getEditText().getText().toString();
        String passwordVal = "^.*(?=.{8,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$";

        //La contraseña debe contener 8 caracteres y al menos un número, una letra y un carácter único como!#$%&?

        if (val.isEmpty()) {
            TLpassword.setError("No puede estar vacio");
            return false;
        } else if (!val.matches(passwordVal)) {
            TLpassword.setError("Contraseña es invalida");
            return false;
        } else {
            TLpassword.setError(null);
            TLpassword.setErrorEnabled(false);
            return true;
        }
    }

    private void RegistrarUsuario() {
        if (!validateName() | !validateUsername() | !validateEmail() | !validatePassword() | !validateCedula()) {
            return;
        }

        correoExiste = false;
        cedulaExiste = false;
        btnRegistro.setEnabled(false);

        String name = TLnombrecompleto.getEditText().getText().toString();
        String username = TLusername.getEditText().getText().toString();
        String email = TLemail.getEditText().getText().toString();
        String password = TLpassword.getEditText().getText().toString();
        String cedula = TCedula.getEditText().getText().toString();
        //String tiposangre = TLtiposangre.getEditText().getText().toString();
        Map<String, Object> res = new HashMap<>();
        res.put("nombre", name);
        res.put("correo", email);
        res.put("usuario", username);
        res.put("cedula", cedula);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener(it -> {
            if (it.isSuccessful()) {
                List<String> r = it.getResult().getSignInMethods();
                if (r != null && !r.isEmpty()) {
                    correoExiste = true;
                    Toast.makeText(this, "Este correo ya esta registrado", Toast.LENGTH_SHORT).show();
                    TLemail.setError("Este correo ya se encuentra registrado");
                    TLemail.setErrorEnabled(true);
                    btnRegistro.setEnabled(true);
                }
                else {
                    db.collection("doctores").document(cedula).get().addOnCompleteListener(task -> {
                        if(task.getResult().exists()){
                            cedulaExiste = true;
                            btnRegistro.setEnabled(true);
                            Toast.makeText(this, "Esta cedula ya esta registrada", Toast.LENGTH_SHORT).show();
                            TCedula.setError("Este numero de cedula ya esta registrado");
                        }
                        else{
                            auth.createUserWithEmailAndPassword(email, password).isSuccessful();
                            db.collection("doctores").document(cedula).set(res);
                            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}