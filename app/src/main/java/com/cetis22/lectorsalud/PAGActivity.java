package com.cetis22.lectorsalud;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.webkit.URLUtil;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import java.io.IOException;

/* loaded from: classes5.dex */
public class PAGActivity extends AppCompatActivity {
    private CameraSource cameraSource;
    private SurfaceView cameraView;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private String token = "";
    private String tokenanterior = "";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag);
        this.cameraView = (SurfaceView) findViewById(R.id.camera_view);
        initQR();
    }

    public void initQR() {
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(0).build();
        this.cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(1600, 1024).setAutoFocusEnabled(true).build();
        this.cameraView.getHolder().addCallback(new SurfaceHolder.Callback() { // from class: com.cetis22.lectorsalud.PAGActivity.1
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(PAGActivity.this, "android.permission.CAMERA") == 0) {
                    try {
                        PAGActivity.this.cameraSource.start(PAGActivity.this.cameraView.getHolder());
                        return;
                    } catch (IOException ie) {
                        Log.e("CAMERA SOURCE", ie.getMessage());
                        return;
                    }
                }
                PAGActivity.this.shouldShowRequestPermissionRationale("android.permission.CAMERA");
                PAGActivity.this.requestPermissions(new String[]{"android.permission.CAMERA"}, 1);
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder holder) {
                PAGActivity.this.cameraSource.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() { // from class: com.cetis22.lectorsalud.PAGActivity.2
            @Override // com.google.android.gms.vision.Detector.Processor
            public void release() {
            }

            @Override // com.google.android.gms.vision.Detector.Processor
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() > 0) {
                    PAGActivity.this.token = barcodes.valueAt(0).displayValue.toString();
                    if (!PAGActivity.this.token.equals(PAGActivity.this.tokenanterior)) {
                        PAGActivity pAGActivity = PAGActivity.this;
                        pAGActivity.tokenanterior = pAGActivity.token;
                        Log.i("token", PAGActivity.this.token);
                        if (URLUtil.isValidUrl(PAGActivity.this.token)) {
                            Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(PAGActivity.this.token));
                            PAGActivity.this.startActivity(browserIntent);
                        } else {
                            Intent shareIntent = new Intent();
                            shareIntent.setAction("android.intent.action.SEND");
                            shareIntent.putExtra("android.intent.extra.TEXT", PAGActivity.this.token);
                            shareIntent.setType("text/plain");
                            PAGActivity.this.startActivity(shareIntent);
                        }
                        new Thread(new Runnable() { // from class: com.cetis22.lectorsalud.PAGActivity.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    synchronized (this) {
                                        wait(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
                                        PAGActivity.this.tokenanterior = "";
                                    }
                                } catch (InterruptedException e) {
                                    Log.e("Error", "Waiting didnt work!!");
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }
            }
        });
    }
}
