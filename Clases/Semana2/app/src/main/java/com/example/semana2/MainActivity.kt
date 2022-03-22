package com.example.semana2

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var contador = 0
        btn_accion.setOnClickListener {
            // btn_uno.setBackgroundColor(Color.RED);
            // var color = Color.TRANSPARENT
            //val background: Drawable = btn_uno.background
            //if (background is ColorDrawable) color = background.color
            var color = Color.TRANSPARENT
            val background : Drawable = btn_accion.background
            if (background is ColorDrawable)
            {
                color = background.color
            }
            if (color == Color.BLUE)
            {
                btn_accion.setBackgroundColor(Color.RED)
                btn_accion.setText("cambie")
            }
            else
            {
                btn_accion.setBackgroundColor(Color.BLUE)
                btn_accion.setText("cambie")
            }

            contador += 1
            txt_multi_clicks.setText(txt_multi_clicks.text.toString() + " " + contador.toString())
        }

        btn_continuar.setOnClickListener {
            startActivity(Intent(this,SegundapantallaActivity::class.java))

        }
    }
}