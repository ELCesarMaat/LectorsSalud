package com.cetis22.lectorsalud;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class NuevoPacieneActivity extends AppCompatActivity {
    Button BTN1;
    Button BTN2;
    TextInputLayout afiliadoP;
    TextInputLayout alergiasP;
    TextInputLayout antecfamiliaresP;
    Button btnLoadImage;
    TextInputLayout curpP;
    TextInputLayout dosismedP;
    TextInputLayout enfermedadcronicaP;
    TextInputLayout enferpacienteP;
    TextInputLayout examenultimoP;
    TextInputLayout fechaconsmediP;
    TextInputLayout fechanacimientoP;
    TextInputLayout fechaulcirujiaP;
    Uri imageUri;
    ImageView imgUser;
    TextInputLayout medicamentosconP;
    TextInputLayout nombrecomP;
    DatabaseReference reference;
    FirebaseDatabase rootNode;
    private ActivityResultLauncher<String> selectImageLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() { // from class: com.cetis22.lectorsalud.NuevoPacieneActivity.3
        @Override // androidx.activity.result.ActivityResultCallback
        public void onActivityResult(Uri uri) {
            if (uri != null) {
                NuevoPacieneActivity.this.imageUri = uri;
                NuevoPacieneActivity.this.imgUser.setImageURI(uri);
            }
        }
    });
    TextInputLayout tiposangreP;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_paciene);
        this.nombrecomP = (TextInputLayout) findViewById(R.id.nombrecomP);
        this.fechanacimientoP = (TextInputLayout) findViewById(R.id.fechanacimientoP);
        this.curpP = (TextInputLayout) findViewById(R.id.curpP);
        this.tiposangreP = (TextInputLayout) findViewById(R.id.tiposangreP);
        this.afiliadoP = (TextInputLayout) findViewById(R.id.afiliadoP);
        this.examenultimoP = (TextInputLayout) findViewById(R.id.examenultimoP);
        this.enferpacienteP = (TextInputLayout) findViewById(R.id.enferpacienteP);
        this.medicamentosconP = (TextInputLayout) findViewById(R.id.medicamentosconP);
        this.dosismedP = (TextInputLayout) findViewById(R.id.dosismedP);
        this.fechaconsmediP = (TextInputLayout) findViewById(R.id.fechaconsmediP);
        this.fechaulcirujiaP = (TextInputLayout) findViewById(R.id.fechaulcirujiaP);
        this.alergiasP = (TextInputLayout) findViewById(R.id.alergiasP);
        this.enfermedadcronicaP = (TextInputLayout) findViewById(R.id.enfermedadcronicaP);
        this.antecfamiliaresP = (TextInputLayout) findViewById(R.id.antecfamiliaresP);
        this.btnLoadImage = (Button) findViewById(R.id.btn_load_image);
        this.imgUser = (ImageView) findViewById(R.id.img_save_paciente);
        Button button = (Button) findViewById(R.id.BTN1);
        this.BTN1 = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.NuevoPacieneActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NuevoPacieneActivity.this.RegistrarDatosPacienteNuevo();
            }
        });
        Button button2 = (Button) findViewById(R.id.BTN2);
        this.BTN2 = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.NuevoPacieneActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NuevoPacieneActivity.this.IrVentanaGeneradorQR();
            }
        });
        this.btnLoadImage.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.NuevoPacieneActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NuevoPacieneActivity.this.m118lambda$onCreate$0$comcetis22lectorsaludNuevoPacieneActivity(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-cetis22-lectorsalud-NuevoPacieneActivity  reason: not valid java name */
    public /* synthetic */ void m118lambda$onCreate$0$comcetis22lectorsaludNuevoPacieneActivity(View view) {
        this.selectImageLauncher.launch("image/*");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void IrVentanaGeneradorQR() {
        if ((!validateNombreCompleto().booleanValue()) | (!validateFechadenacimiento().booleanValue()) | (!validatecurp().booleanValue()) | (!validatetiposangre().booleanValue()) | (!validateafiliado().booleanValue()) | (!validatefechaultimoexamen().booleanValue()) | (!validateenfermedadesdepaciente().booleanValue()) | (!validatemedicamentosconsumidos().booleanValue()) | (!validatedosisdemedicamentos().booleanValue()) | (!validateultimafechaconsumomedicamento().booleanValue()) | (!validatefechadesuultimacirujia().booleanValue()) | (!validatealergia().booleanValue()) | (!validateenfermedadescronicas().booleanValue()) | (!validateantecedentesfamiliares().booleanValue()) | (!validateImage())) {
            return;
        }
        Object nombrecompleto = this.nombrecomP.getEditText().getText().toString();
        Object fechadenacimiento = this.fechanacimientoP.getEditText().getText().toString();
        Object curp = this.curpP.getEditText().getText().toString().toUpperCase();
        Object tipodesangre = this.tiposangreP.getEditText().getText().toString();
        Object fechaultexamen = this.examenultimoP.getEditText().getText().toString();
        Object enfermedadesp = this.enferpacienteP.getEditText().getText().toString();
        Object medicamentoscons = this.medicamentosconP.getEditText().getText().toString();
        Object dosismedicamentos = this.dosismedP.getEditText().getText().toString();
        Object ufechacons = this.fechaconsmediP.getEditText().getText().toString();
        Object fechacirujiault = this.fechaulcirujiaP.getEditText().getText().toString();
        Object alergias = this.alergiasP.getEditText().getText().toString();
        Object enfercronicas = this.enfermedadcronicaP.getEditText().getText().toString();
        Object antefamiliares = this.antecfamiliaresP.getEditText().getText().toString();
        Object afiliado = this.afiliadoP.getEditText().getText().toString();
        Map<String, Object> res = new HashMap<>();
        res.put("nombre", nombrecompleto);
        res.put("fecha_nacimienti", fechadenacimiento);
        res.put("curp", curp);
        res.put("tipo_de_sangre", tipodesangre);
        res.put("fech_ultimo_examen", fechaultexamen);
        res.put("enfermedades", enfermedadesp);
        res.put("medicamentos_consumidos", medicamentoscons);
        res.put("dosis_medicamentos", dosismedicamentos);
        res.put("fecha_ultimo_medicamento", ufechacons);
        res.put("fecha_ultima_cirujia", fechacirujiault);
        res.put("alergias", alergias);
        res.put("enfermedades_cronicas", enfercronicas);
        res.put("antecedentes_familiares", antefamiliares);
        res.put("afiliado", afiliado);
        String nombrecompleto2 = SessionInfo.getCedula();
        res.put("doctor", nombrecompleto2);
        Bitmap bitmap = ((BitmapDrawable) this.imgUser.getDrawable()).getBitmap();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bytes);
        byte[] imageBytes = bytes.toByteArray();
        Intent i = new Intent(this, QRgeneradoActivity.class);
        i.putExtra("data", (Serializable) res);
        SessionInfo.setImg_user(imageBytes);
        startActivity(i);
    }

    private boolean validateImage() {
        if (this.imageUri == null) {
            Toast.makeText(this, "Porfavor seleccione una imagen", 0).show();
            return false;
        }
        return true;
    }

    private Boolean validateNombreCompleto() {
        String val = this.nombrecomP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.nombrecomP.setError("No puede estar vacio");
            return false;
        }
        this.nombrecomP.setError(null);
        this.nombrecomP.setErrorEnabled(false);
        return true;
    }

    private Boolean validateFechadenacimiento() {
        String val = this.fechanacimientoP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.fechanacimientoP.setError("No puede estar vacio");
            return false;
        }
        this.fechanacimientoP.setError(null);
        this.fechanacimientoP.setErrorEnabled(false);
        return true;
    }

    private Boolean validatecurp() {
        String val = this.curpP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.curpP.setError("No puede estar vacio");
            return false;
        }
        this.curpP.setError(null);
        this.curpP.setErrorEnabled(false);
        return true;
    }

    private Boolean validatetiposangre() {
        String val = this.tiposangreP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.tiposangreP.setError("No puede estar vacio");
            return false;
        }
        this.tiposangreP.setError(null);
        this.tiposangreP.setErrorEnabled(false);
        return true;
    }

    private Boolean validateafiliado() {
        String val = this.afiliadoP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.afiliadoP.setError("No puede estar vacio");
            return false;
        }
        this.afiliadoP.setError(null);
        this.afiliadoP.setErrorEnabled(false);
        return true;
    }

    private Boolean validatefechaultimoexamen() {
        String val = this.examenultimoP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.examenultimoP.setError("No puede estar vacio");
            return false;
        }
        this.examenultimoP.setError(null);
        this.examenultimoP.setErrorEnabled(false);
        return true;
    }

    private Boolean validateenfermedadesdepaciente() {
        String val = this.enferpacienteP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.enferpacienteP.setError("No puede estar vacio");
            return false;
        }
        this.enferpacienteP.setError(null);
        this.enferpacienteP.setErrorEnabled(false);
        return true;
    }

    private Boolean validatemedicamentosconsumidos() {
        String val = this.medicamentosconP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.medicamentosconP.setError("No puede estar vacio");
            return false;
        }
        this.medicamentosconP.setError(null);
        this.medicamentosconP.setErrorEnabled(false);
        return true;
    }

    private Boolean validatedosisdemedicamentos() {
        String val = this.dosismedP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.dosismedP.setError("No puede estar vacio");
            return false;
        }
        this.dosismedP.setError(null);
        this.dosismedP.setErrorEnabled(false);
        return true;
    }

    private Boolean validateultimafechaconsumomedicamento() {
        String val = this.fechaconsmediP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.fechaconsmediP.setError("No puede estar vacio");
            return false;
        }
        this.fechaconsmediP.setError(null);
        this.fechaconsmediP.setErrorEnabled(false);
        return true;
    }

    private Boolean validatefechadesuultimacirujia() {
        String val = this.fechaulcirujiaP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.fechaulcirujiaP.setError("No puede estar vacio");
            return false;
        }
        this.fechaulcirujiaP.setError(null);
        this.fechaulcirujiaP.setErrorEnabled(false);
        return true;
    }

    private Boolean validatealergia() {
        String val = this.alergiasP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.alergiasP.setError("No puede estar vacio");
            return false;
        }
        this.alergiasP.setError(null);
        this.alergiasP.setErrorEnabled(false);
        return true;
    }

    private Boolean validateenfermedadescronicas() {
        String val = this.enfermedadcronicaP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.enfermedadcronicaP.setError("No puede estar vacio");
            return false;
        }
        this.enfermedadcronicaP.setError(null);
        this.enfermedadcronicaP.setErrorEnabled(false);
        return true;
    }

    private Boolean validateantecedentesfamiliares() {
        String val = this.antecfamiliaresP.getEditText().getText().toString();
        if (val.isEmpty()) {
            this.antecfamiliaresP.setError("No puede estar vacio");
            return false;
        }
        this.antecfamiliaresP.setError(null);
        this.antecfamiliaresP.setErrorEnabled(false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void RegistrarDatosPacienteNuevo() {
    }
}
