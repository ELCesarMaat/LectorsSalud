package com.cetis22.lectorsalud;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

/* loaded from: classes5.dex */
public class TokenDeSeguridadActivity extends AppCompatActivity {
    TextInputLayout nombrehospital;
    TextInputLayout tokenSeguridad;
    Button usuarioEnLinea;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_de_seguridad);
        this.usuarioEnLinea = (Button) findViewById(R.id.btnUsuarioRegisrado);
        this.tokenSeguridad = (TextInputLayout) findViewById(R.id.tokenRegist);
        this.usuarioEnLinea.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.TokenDeSeguridadActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TokenDeSeguridadActivity.this.IniciarSeccionn();
            }
        });
    }

    private Boolean validateToken() {
        String val = this.tokenSeguridad.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.tokenSeguridad.setError("No puede estar vacio");
            return false;
        }
        this.tokenSeguridad.setError(null);
        this.tokenSeguridad.setErrorEnabled(false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void IniciarSeccionn() {
        if (!validateToken().booleanValue()) {
            return;
        }
        accesoPermitido();
    }

    private void accesoPermitido() {
    }
}
