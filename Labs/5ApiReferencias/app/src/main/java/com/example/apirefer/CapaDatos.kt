package com.example.apirefer

import android.app.Application
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import java.sql.Date

class CapaDatos {

    data class REFERENCIA(
        @SerializedName("TIPODOC1") var TIPODOC1: String,
        @SerializedName("NUMERO1") var NUMERO1: String,
        @SerializedName("FECHAEMISION") var FECHAEMISION: Date,
        @SerializedName("CODIGO1") var CODIGO1: String,
        @SerializedName("RAZON1") var RAZON1: String,
    )

    class SharedApp : Application() {
        companion object {
            lateinit var referencias: MutableList<REFERENCIA>
        }

        override fun onCreate() {
            super.onCreate()
            referencias  = ArrayList()
        }
    }

    interface APIService {
        @GET("INFORMACIONREFERENCIA")
        fun registrationPost(
        ) : Call<MutableList<REFERENCIA>>
    }

}