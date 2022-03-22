package com.example.lab1calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var num1: Int = 0
    private var num2: Int = 0
    private var resultado: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0.setOnClickListener {numero("0")}
        btn_1.setOnClickListener {numero("1")}
        btn_2.setOnClickListener {numero("2")}
        btn_3.setOnClickListener {numero("3")}
        btn_4.setOnClickListener {numero("4")}
        btn_5.setOnClickListener {numero("5")}
        btn_6.setOnClickListener {numero("6")}
        btn_7.setOnClickListener {numero("7")}
        btn_8.setOnClickListener {numero("8")}
        btn_9.setOnClickListener {numero("9")}

        btn_Menos.setOnClickListener {operacion(resta)}
        btn_Multi.setOnClickListener {operacion(multi)}
        btn_Mas.setOnClickListener {operacion(suma)}
        btn_Divi.setOnClickListener {operacion(divi)}

        btn_Igual.setOnClickListener {
            var total = when(resultado){
                suma -> num1 + num2
                resta -> num1 - num2
                multi -> num1 * num2
                divi -> num1 / num2
                else -> 0
            }
            txt_Dato.text = total.toString()
        }

        btn_Limpiar.setOnClickListener {
            num1 = 0
            num2 = 0
            txt_Dato.text = ""
            resultado = noOperacion
        }
    }

    private fun operacion (operacion : Int){
        this.resultado = operacion
        txt_Dato.text = ""
    }

    companion object{
        const val suma = 1
        const val  resta = 2
        const val multi = 3
        const val divi  = 4
        const val noOperacion = 0
    }

    private fun numero (numero : String){

        txt_Dato.text = "${txt_Dato.text}$numero"

        if(resultado == noOperacion) {
            num1 = txt_Dato.text.toString().toInt()
        }
        else{
            num2 = txt_Dato.text.toString().toInt()
        }
    }
}