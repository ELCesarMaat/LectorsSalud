package com.cetis22.lectorsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;

public class QRgeneradoActivity extends AppCompatActivity {

    //LOGICA PARA PODER GENERAR UN QR CON UN EDIT TEXT.

    Button BtnQrGenerador;
    EditText EditQrGenerador;
    ImageView qrCodeGenerador;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerado);
        //EN ESTE EDIT TEXT, SE ESCRIBIRA EL TEXTO
        EditQrGenerador = findViewById(R.id.EditQrGenerador);
        //EN ESTE IMAGE VIEW SE VERA REFLEJADO EL QR GENERADO EN IMAGEN
        qrCodeGenerador = findViewById(R.id.qrCodeGenerador);
        //ESTE BOTON PODRA GENERAR EL CODIGO QR
        BtnQrGenerador = findViewById(R.id.BtnQrGenerador);

        BtnQrGenerador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(EditQrGenerador.getText().toString(), BarcodeFormat.QR_CODE, 750, 750);

                    qrCodeGenerador.setImageBitmap(bitmap);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        Button button = findViewById(R.id.BtnSave);
        button.setOnClickListener(v ->{
            saveImage();
            Toast.makeText(this, "GUARDADO EN  CARPETA Dolowns", Toast.LENGTH_SHORT).show();
        });

    }

    private void saveImage() {

        qrCodeGenerador.setDrawingCacheEnabled(true);
        qrCodeGenerador.buildDrawingCache();
        qrCodeGenerador.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = qrCodeGenerador.getDrawingCache();
        save(bitmap);

    }

    private void save(Bitmap bitmap) {

        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(root+"/Download");
        String fileName = "QR_GENERADO_PACIENTE.jpg";
        File myfile = new File(file, fileName);

        if (myfile.exists()){
            myfile.delete();
        }

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(myfile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(this, "Saved...", Toast.LENGTH_SHORT).show();
            qrCodeGenerador.setDrawingCacheEnabled(false);

        } catch (Exception e) {
            Toast.makeText(this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}