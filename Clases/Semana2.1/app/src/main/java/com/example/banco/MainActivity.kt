package com.example.banco

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_Login.SetOnClickListener {
            startActivity(Intent(this,SegundapantallaActivity::class.java))
            var usuario = txt_Usuario
            var contrasena = txt_Contrasena

        }
    }
}