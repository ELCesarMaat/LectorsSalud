package com.cetis22.lectorsalud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

private const val CAMERA_REQUEST_CODE = 101

class LectorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lector)
    }
}