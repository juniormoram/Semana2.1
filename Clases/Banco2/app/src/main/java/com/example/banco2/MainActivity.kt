package com.example.banco2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_Registro.setOnClickListener {
            startActivity(Intent(this,RegistroActivity::class.java))

        }

        btn_Login.setOnClickListener {
            startActivity(Intent(this,DetalleActivity::class.java))

        }

    }
}