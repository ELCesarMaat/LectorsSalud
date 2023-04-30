package com.cetis22.lectorsalud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class TokenDeSeguridadActivity extends AppCompatActivity {

    // LOGICA PARA QUE EL USUARIO PUEDA INGRESAR EL TOKEN Y PODER INGRESAR A LA APP

    Button usuarioEnLinea;
    TextInputLayout tokenSeguridad;
    TextInputLayout nombrehospital;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_de_seguridad);

        usuarioEnLinea = findViewById(R.id.btnUsuarioRegisrado);
        tokenSeguridad = findViewById(R.id.tokenRegist);

        usuarioEnLinea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IniciarSeccionn();
            }
        });
    }

    // VALIDACIONES DE PASSWORD (CONTRASEÑA) - USARIO YA DADO DE ALTA CON LA BASE DE DATOS
    private Boolean validateToken(){
        String val = tokenSeguridad.getEditText().getText().toString();

        if (val.isEmpty()){
            tokenSeguridad.setError("No puede estar vacio");
            return  false;
        }else{
            tokenSeguridad.setError(null);
            tokenSeguridad.setErrorEnabled(false);
            return true;
        }
    }

    private void IniciarSeccionn(){
        if (!validateToken()){
            return;
        }else {
            accesoPermitido();
        }
    }

    private void accesoPermitido() {



    }

}