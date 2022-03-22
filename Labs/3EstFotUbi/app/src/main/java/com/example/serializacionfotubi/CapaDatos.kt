package com.example.serializacionfotubi

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class CapaDatos {

    //ESTRUCTURA QUE VAMOS A GUARDAR EN MEMORIA
    data class Estudiante(
        var nombre:String,
        var carnet:String,
        var foto:String,
        var lati:String,
        var longi:String
    )

    //CLASE PARA GUARDAR PREFERENCE, EN ARCHIVO XML
    class Prefs (context: Context) {
        val PREFS_NAME = "com.cursokotlin.sharedpreferences"
        val SHARED_NAME = "shared_name"
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

        var name: String?
            get() = prefs.getString(SHARED_NAME, "")
            set(value) = prefs.edit().putString(SHARED_NAME, value).apply()
    }

    //CLASE COMPARTIDA PARA QUE TODAS LAS PANTALLAS TENGAS ACCESO A LO GUARDADO EN PREFERENCE
    class SharedApp : Application() {
        companion object {
            lateinit var estudiantestodos: MutableList<Estudiante>
            lateinit var prefs: Prefs

        }

        override fun onCreate() {
            super.onCreate()
            estudiantestodos = ArrayList()
            prefs = Prefs(applicationContext)

        }

    }

}
