package com.cetis22.lectorsalud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes5.dex */
public class NPaciente2Activity extends AppCompatActivity {
    Button BTN2;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npaciente2);
        Button button = (Button) findViewById(R.id.BTN2);
        this.BTN2 = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.NPaciente2Activity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(NPaciente2Activity.this, NPaciente3Activity.class);
                NPaciente2Activity.this.startActivity(intent);
            }
        });
    }
}
