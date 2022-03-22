package com.example.vet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //El siguiente botón ingresa al cronograma de citas, Actitivity CronogramaActivity
        btn_iracrono.setOnClickListener {

            val intent = Intent(this, CronoActivity::class.java)

        //INICIAMOS EL NUEVO ACTIVITY
            startActivity(intent)
        }
    }
}