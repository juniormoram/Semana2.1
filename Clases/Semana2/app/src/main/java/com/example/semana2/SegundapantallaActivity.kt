package com.example.semana2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_segundapantalla.*

class SegundapantallaActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segundapantalla)
        btn_ircalendario.setOnClickListener {
            startActivity(Intent(this,CalendarioActivity::class.java))


        }
    }

}