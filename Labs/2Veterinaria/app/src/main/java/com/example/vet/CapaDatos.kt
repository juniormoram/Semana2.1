package com.example.vet

import android.app.Application

class CapaDatos {

    data class Cita(
        var nombrecliente:String,
        var nombremascota:String,
        var raza:String,
        var edadmascota:String,
        var horacita:String,
        var descripcita:String,
        var foto:String,
    )
    //CLASE INSTANCIADA QUE PUEDE SER ACCESADA POR TODOS LOS ACTIVITY DEL PROYECTO
    class SharedApp : Application() {
        companion object {
            //VARIABLES, ATRIBUTOS DE LA CLASE PARA GUARDAR INFORMACION QUE PUEDE SER CONSIDERADA GLOBAL
            lateinit var Citas: MutableList<Cita>
        }

        override fun onCreate() {
            super.onCreate()
            //INSTANCIAMOS, TODAS LAS VARIABLES QUE SE DECLARARON EN EL PUNTO ANTERIOR
            Citas = ArrayList()

        }
}}