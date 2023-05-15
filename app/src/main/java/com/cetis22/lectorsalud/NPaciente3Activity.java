package com.cetis22.lectorsalud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes5.dex */
public class NPaciente3Activity extends AppCompatActivity {
    Button BTN3;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npaciente3);
        Button button = (Button) findViewById(R.id.BTN3);
        this.BTN3 = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.NPaciente3Activity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(NPaciente3Activity.this, NPaciente5Activity.class);
                NPaciente3Activity.this.startActivity(intent);
            }
        });
    }
}
