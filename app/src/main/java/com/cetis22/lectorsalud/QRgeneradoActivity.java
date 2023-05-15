package com.cetis22.lectorsalud;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

/* loaded from: classes5.dex */
public class QRgeneradoActivity extends AppCompatActivity {
    EditText EditQrGenerador;
    Button btnSave;
    String curp;
    byte[] imgUser;
    ImageView qrCodeGenerador;
    Map res;
    TextView txtMensaje;
    boolean permisoConcedido = false;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerado);
        this.imgUser = SessionInfo.getImg_user();
        Map map = (Map) getIntent().getSerializableExtra("data");
        this.res = map;
        this.curp = map.get("curp").toString();
        this.txtMensaje = (TextView) findViewById(R.id.txt_mensaje);
        this.EditQrGenerador = (EditText) findViewById(R.id.EditQrGenerador);
        this.qrCodeGenerador = (ImageView) findViewById(R.id.qrCodeGenerador);
        this.btnSave = (Button) findViewById(R.id.btn_save);
        this.EditQrGenerador.addTextChangedListener(new TextWatcher() { // from class: com.cetis22.lectorsalud.QRgeneradoActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(QRgeneradoActivity.this.EditQrGenerador.getText().toString(), BarcodeFormat.QR_CODE, 750, 750);
                    QRgeneradoActivity.this.qrCodeGenerador.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.EditQrGenerador.setText(this.curp);
        this.btnSave.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.QRgeneradoActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QRgeneradoActivity.this.m124lambda$onCreate$0$comcetis22lectorsaludQRgeneradoActivity(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-cetis22-lectorsalud-QRgeneradoActivity  reason: not valid java name */
    public /* synthetic */ void m124lambda$onCreate$0$comcetis22lectorsaludQRgeneradoActivity(View view) {
        this.btnSave.setEnabled(false);
        guardarUsuario();
    }

    private boolean saveImage() {
        this.qrCodeGenerador.setDrawingCacheEnabled(true);
        this.qrCodeGenerador.buildDrawingCache();
        this.qrCodeGenerador.setDrawingCacheQuality(1048576);
        Bitmap bitmap = this.qrCodeGenerador.getDrawingCache();
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        File file = new File(root);
        String fileName = this.curp + ".jpg";
        File myfile = new File(file, fileName);
        if (myfile.exists()) {
            myfile.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(myfile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            this.qrCodeGenerador.setDrawingCacheEnabled(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "No se pudo guardar la imagen" + e.toString(), 0).show();
            return false;
        }
    }

    private void guardarUsuario() {
        if (saveImage()) {
            this.curp = this.curp.toUpperCase();
            this.db.collection("pacientes").document(this.curp).set(this.res).addOnCompleteListener(new OnCompleteListener() { // from class: com.cetis22.lectorsalud.QRgeneradoActivity$$ExternalSyntheticLambda3
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    QRgeneradoActivity.this.m123xbe7f3269(task);
                }
            });
            return;
        }
        this.btnSave.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$guardarUsuario$3$com-cetis22-lectorsalud-QRgeneradoActivity  reason: not valid java name */
    public /* synthetic */ void m123xbe7f3269(Task task) {
        if (task.isSuccessful()) {
            FirebaseStorage.getInstance().getReference().child(this.curp + ".jpg").putBytes(this.imgUser).addOnSuccessListener(new OnSuccessListener() { // from class: com.cetis22.lectorsalud.QRgeneradoActivity$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    QRgeneradoActivity.this.m121x38a65fab((UploadTask.TaskSnapshot) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.cetis22.lectorsalud.QRgeneradoActivity$$ExternalSyntheticLambda2
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    QRgeneradoActivity.this.m122xfb92c90a(exc);
                }
            });
        } else {
            Toast.makeText(this, "Error :(", Toast.LENGTH_SHORT).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$guardarUsuario$1$com-cetis22-lectorsalud-QRgeneradoActivity  reason: not valid java name */
    public /* synthetic */ void m121x38a65fab(UploadTask.TaskSnapshot it) {
        Intent intent = new Intent(this, PantallaInicio.class);
        Toast.makeText(this, "REGISTRO EXITOSO", Toast.LENGTH_LONG).show();
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$guardarUsuario$2$com-cetis22-lectorsalud-QRgeneradoActivity  reason: not valid java name */
    public /* synthetic */ void m122xfb92c90a(Exception fail) {
        Toast.makeText(this, "Error al cargar la imagen", 0).show();
    }
}
