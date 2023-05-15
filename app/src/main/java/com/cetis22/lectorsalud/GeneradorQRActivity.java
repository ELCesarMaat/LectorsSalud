package com.cetis22.lectorsalud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes5.dex */
public class GeneradorQRActivity extends AppCompatActivity {
    Button BTNgenerador;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generador_qractivity);
        Button button = (Button) findViewById(R.id.BTNgenerador);
        this.BTNgenerador = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.GeneradorQRActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GeneradorQRActivity.this.m105lambda$onCreate$0$comcetis22lectorsaludGeneradorQRActivity(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-cetis22-lectorsalud-GeneradorQRActivity  reason: not valid java name */
    public /* synthetic */ void m105lambda$onCreate$0$comcetis22lectorsaludGeneradorQRActivity(View view) {
        Intent intent = new Intent(this, QRgeneradoActivity.class);
        startActivity(intent);
    }
}
