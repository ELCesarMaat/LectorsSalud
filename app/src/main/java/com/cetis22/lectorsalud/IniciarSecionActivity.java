package com.cetis22.lectorsalud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class IniciarSecionActivity extends AppCompatActivity {

    // LOGICA PARA QUE EL USUARIO (DOCTOR) PUEDA INGRESAR A SU CUENTA.

    String cedula, nombre, usuario;

    Button btnIniciarseccion;
    TextInputLayout usuarioIS;
    TextInputLayout contraseñaIS;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, SelectorActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_secion);

        btnIniciarseccion = findViewById(R.id.btnIniciarseccion);
        usuarioIS = findViewById(R.id.usuarioIS);
        contraseñaIS = findViewById(R.id.contraseñaIS);


        btnIniciarseccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Iniciarseccion();
            }
        });

        usuarioIS.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usuarioIS.setError(null);
                usuarioIS.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    // VALIDACIONES DE USUARIO (USERNAME) - USUARIO YA DADO DE ALTA CON LA BASE DE DATOS.
    private Boolean validateUsername(){
        String val = usuarioIS.getEditText().getText().toString();

        if (val.isEmpty()){
            usuarioIS.setError("No puede estar vacio");
            return false;
        }else{
            usuarioIS.setError(null);
            usuarioIS.setErrorEnabled(false);
            return true;
        }
    }

    // VALIDACIONES DE PASSWORD (CONTRASEÑA) - USARIO YA DADO DE ALTA CON LA BASE DE DATOS
    private Boolean validatePassword(){
        String val = contraseñaIS.getEditText().getText().toString();

        if (val.isEmpty()){
            contraseñaIS.setError("No puede estar vacio");
            return  false;
        }else{
            contraseñaIS.setError(null);
            contraseñaIS.setErrorEnabled(false);
            return true;
        }
    }

    /*
    VERIFICAR SI LAS VALIDACIONES SON CORRECTAS Y SI LO SON EL USAURIO PODRA INICIAR SECCION.
    CASO CONTRARIO EL USARIO NO PODRA INICIAR SECCION YA QUE LOS DATOS SON ICORRECTOS.
    TODOS ESTOS DATOS SE ENCENTRAN EN LA BASE DE DATOS.
     */
    private void Iniciarseccion() {
        if (!validateUsername() | !validatePassword()){
            return;
        } else{
            isUser();
        }
    }

    /*
     AQUI VIENE LA MAGIA YA QUE ESTAS PRE - VERIFICACIONES VEREMOS SI SON VERDADERAS
     PARA ELLO HAREMOS NUESTRA CONEXION DE BASE DE DATOS DE NUESTRA APP A FIREBASE
     */
    private void isUser(){

        final String userEnteredUsername = usuarioIS.getEditText().getText().toString().trim();
        final String userEnteredPassword = contraseñaIS.getEditText().getText().toString().trim();


        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        auth.signInWithEmailAndPassword(userEnteredUsername, userEnteredPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    db.collection("doctores").whereEqualTo("correo", userEnteredUsername).get().addOnCompleteListener(it -> {
                        if(it.isSuccessful()){
                            //if(it.getResult().getDocuments().isEmpty()){
                              //  Toast.makeText(IniciarSecionActivity.this, "Porfavor vuelva a registrarse", Toast.LENGTH_SHORT).show();
                            //}
                            for(DocumentSnapshot ds : it.getResult().getDocuments()){
                                if(ds.getString("correo").equals(userEnteredUsername)){
                                    cedula = ds.getId().toString();
                                    nombre = ds.getString("nombre");
                                    usuario = ds.getString("usuario");
                                    SharedPreferences prefs = getSharedPreferences("user_data", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putString("email", userEnteredUsername);
                                    editor.putString("cedula", cedula);
                                    editor.putString("nombre", nombre);
                                    editor.putString("usuario", usuario);
                                    editor.commit();

                                    SessionInfo.setName(nombre);
                                    SessionInfo.setEmail(userEnteredUsername);
                                    SessionInfo.setUsername(usuario);
                                    SessionInfo.setCedula(cedula);

                                    Intent intent = new Intent(getApplicationContext(), PantallaInicio.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                    });
                }
                else {
                    usuarioIS.setError("Usuario no registrado");
                }
            }
        });



    }
}