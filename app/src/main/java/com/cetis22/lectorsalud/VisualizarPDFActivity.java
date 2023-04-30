package com.cetis22.lectorsalud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class VisualizarPDFActivity extends AppCompatActivity {

    private ListView listView;

    private ArrayList <String> libros;

    private StorageReference mStroge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_pdfactivity);

        listView = findViewById(R.id.listview);
        libros = new ArrayList<>();

        mStroge = FirebaseStorage.getInstance().getReference();

        StorageReference ref = mStroge.child("JORGEpaciente");

        ref.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for (StorageReference item : listResult.getItems()){
                    libros.add(item.getName()+"");
                }

                ArrayAdapter <String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, libros);

                listView.setAdapter(adapter);

            }
        }).addOnFailureListener((e -> {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(VisualizarPDFActivity.this);
            builder1.setMessage("Ha ocurrido un error al cargar el documento. Revisa Tu conexion a Internet");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }));

    }
}