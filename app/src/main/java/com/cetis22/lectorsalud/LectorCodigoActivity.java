package com.cetis22.lectorsalud;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.PointerIconCompat;
import com.cetis22.lectorsalud.LectorCodigoActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/* loaded from: classes5.dex */
public class LectorCodigoActivity extends AppCompatActivity {
    Button BTNinfo;
    Button btnSacan;
    String curp;
    TextInputLayout txtCurp;
    Boolean res = false;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector_codigo);
        this.btnSacan = (Button) findViewById(R.id.btnIrAlectorcodigo);
        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.txt_lector_curp);
        this.txtCurp = textInputLayout;
        textInputLayout.getEditText().setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        this.btnSacan.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.LectorCodigoActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(LectorCodigoActivity.this, "android.permission.CAMERA") != 0) {
                    LectorCodigoActivity.this.solicitarPermisos();
                } else {
                    LectorCodigoActivity.this.abrirScanner();
                }
            }
        });
        Button button = (Button) findViewById(R.id.BTNinfo);
        this.BTNinfo = button;
        button.setOnClickListener(new AnonymousClass2());
        this.txtCurp.getEditText().addTextChangedListener(new TextWatcher() { // from class: com.cetis22.lectorsalud.LectorCodigoActivity.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                LectorCodigoActivity.this.txtCurp.setErrorEnabled(false);
                LectorCodigoActivity.this.txtCurp.setError(null);
                if (LectorCodigoActivity.this.txtCurp.getEditText().getText().length() < 4) {
                    LectorCodigoActivity.this.BTNinfo.setEnabled(false);
                } else {
                    LectorCodigoActivity.this.BTNinfo.setEnabled(true);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            abrirScanner();
        } else {
            solicitarPermisos();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.cetis22.lectorsalud.LectorCodigoActivity$2  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LectorCodigoActivity lectorCodigoActivity = LectorCodigoActivity.this;
            lectorCodigoActivity.curp = lectorCodigoActivity.txtCurp.getEditText().getText().toString();
            LectorCodigoActivity.this.db.collection("pacientes").whereEqualTo("curp", LectorCodigoActivity.this.curp).get().addOnSuccessListener(new OnSuccessListener() { // from class: com.cetis22.lectorsalud.LectorCodigoActivity$2$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    LectorCodigoActivity.AnonymousClass2.this.m113lambda$onClick$0$comcetis22lectorsaludLectorCodigoActivity$2((QuerySnapshot) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$com-cetis22-lectorsalud-LectorCodigoActivity$2  reason: not valid java name */
        public /* synthetic */ void m113lambda$onClick$0$comcetis22lectorsaludLectorCodigoActivity$2(QuerySnapshot it) {
            if (!it.isEmpty()) {
                Toast.makeText(LectorCodigoActivity.this, it.getDocuments().get(0).getId(), 0).show();
                Intent intent = new Intent(LectorCodigoActivity.this, InfPACIENTEActivity.class);
                intent.putExtra("curp", LectorCodigoActivity.this.curp);
                LectorCodigoActivity.this.startActivity(intent);
                LectorCodigoActivity.this.finish();
                return;
            }
            LectorCodigoActivity.this.txtCurp.setErrorEnabled(true);
            LectorCodigoActivity.this.txtCurp.setError("No se encontro paciente con este curp");
            Toast.makeText(LectorCodigoActivity.this, "No se encontro el curp " + LectorCodigoActivity.this.curp, 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.BTNinfo.setEnabled(false);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            if (result.getContents().length() >= 4) {
                this.curp = result.getContents().toUpperCase();
                Log.d("App", "Si escaneo");
                this.db.collection("pacientes").whereEqualTo("curp", this.curp).get().addOnSuccessListener(new OnSuccessListener() { // from class: com.cetis22.lectorsalud.LectorCodigoActivity$$ExternalSyntheticLambda0
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        LectorCodigoActivity.this.m112xec18c4f((QuerySnapshot) obj);
                    }
                });
                return;
            }
            this.txtCurp.setErrorEnabled(true);
            this.txtCurp.setError("El QR leido no es valido");
            Toast.makeText(this, "El QR leido no es valido", 1).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onActivityResult$0$com-cetis22-lectorsalud-LectorCodigoActivity  reason: not valid java name */
    public /* synthetic */ void m112xec18c4f(QuerySnapshot it) {
        if (!it.isEmpty()) {
            Intent i = new Intent(this, InfPACIENTEActivity.class);
            i.putExtra("curp", this.curp);
            startActivity(i);
            finish();
            return;
        }
        this.txtCurp.setErrorEnabled(true);
        this.txtCurp.setError("No se encontro paciente con el curp: " + this.curp);
        Toast.makeText(this, "No se encontro el curp " + this.curp, 0).show();
    }

    void abrirScanner() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Escanee el codigo QR");
        integrator.setCameraId(0);
        integrator.setOrientationLocked(false);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void solicitarPermisos() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA"}, PointerIconCompat.TYPE_CONTEXT_MENU);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001) {
            if (grantResults.length > 0 && grantResults[0] == 0) {
                abrirScanner();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Se requiere acceso a la cámara para tomar fotos.").setTitle("Permisos requeridos");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() { // from class: com.cetis22.lectorsalud.LectorCodigoActivity.4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int id) {
                        LectorCodigoActivity.this.solicitarPermisos();
                    }
                });
                builder.setNegativeButton("Cancelar", (DialogInterface.OnClickListener) null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                solicitarPermisosManualmente();
            }
        }
    }

    private void solicitarPermisosManualmente() {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
        Toast.makeText(this, "Por favor, otorgue los permisos de camara para usar el scanner.", 1).show();
    }
}
