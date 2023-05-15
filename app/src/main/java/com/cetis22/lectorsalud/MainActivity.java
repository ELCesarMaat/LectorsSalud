package com.cetis22.lectorsalud;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes5.dex */
public class MainActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(1024, 1024);
        new Handler().postDelayed(new Runnable() { // from class: com.cetis22.lectorsalud.MainActivity.1
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent(MainActivity.this, SelectorActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        }, (long) ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
    }
}
