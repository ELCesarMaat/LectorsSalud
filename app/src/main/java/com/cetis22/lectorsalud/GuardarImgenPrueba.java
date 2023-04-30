package com.cetis22.lectorsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class GuardarImgenPrueba extends AppCompatActivity {

    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_imgen_prueba);

        relativeLayout = findViewById(R.id.myLayout);

        Button button = findViewById(R.id.saveLyout);
        button.setOnClickListener(v ->{
            saveImage();
        });
    }

    private void saveImage(){

        relativeLayout.setDrawingCacheEnabled(true);
        relativeLayout.buildDrawingCache();
        relativeLayout.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = relativeLayout.getDrawingCache();
        save(bitmap);

    }

    private void save(Bitmap bitmap) {

        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(root+"/Download");
        String fileName = "my_simple_image.jpg";
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
            relativeLayout.setDrawingCacheEnabled(false);

        } catch (Exception e) {
            Toast.makeText(this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

}