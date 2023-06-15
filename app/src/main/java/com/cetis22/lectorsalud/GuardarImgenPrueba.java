package com.cetis22.lectorsalud;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileOutputStream;

/* loaded from: classes5.dex */
public class GuardarImgenPrueba extends AppCompatActivity {
    RelativeLayout relativeLayout;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_imgen_prueba);
        this.relativeLayout = (RelativeLayout) findViewById(R.id.myLayout);
        Button button = (Button) findViewById(R.id.saveLyout);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.GuardarImgenPrueba$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GuardarImgenPrueba.this.m106lambda$onCreate$0$comcetis22lectorsaludGuardarImgenPrueba(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-cetis22-lectorsalud-GuardarImgenPrueba  reason: not valid java name */
    public /* synthetic */ void m106lambda$onCreate$0$comcetis22lectorsaludGuardarImgenPrueba(View v) {
        saveImage();
    }

    private void saveImage() {
        this.relativeLayout.setDrawingCacheEnabled(true);
        this.relativeLayout.buildDrawingCache();
        this.relativeLayout.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = this.relativeLayout.getDrawingCache();
        save(bitmap);
    }

    private void save(Bitmap bitmap) {
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(root + "/Download");
        File myfile = new File(file, "my_simple_image.jpg");
        if (myfile.exists()) {
            myfile.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(myfile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(this, "Saved...", 0).show();
            this.relativeLayout.setDrawingCacheEnabled(false);
        } catch (Exception e) {
            Toast.makeText(this, "Error : " + e.toString(), 0).show();
        }
    }
}
