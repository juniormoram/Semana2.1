package com.example.semana2live

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    data class Estudiante(
        var nombre:String,
        var carnet:String,
        var edad:String,
        var foto:String
    )
    lateinit var mRecyclerView : RecyclerView
    val mAdapter : RecyclerAdapter = RecyclerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var contador = 0

        setUpRecyclerView()


    }
    fun setUpRecyclerView(){
        mRecyclerView = findViewById<RecyclerView>(R.id.vistareciclada)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapter(getEstudiantes(), this)
        mRecyclerView.adapter = mAdapter
    }

    fun getEstudiantes(): MutableList<Estudiante>{
        var estudiantes:MutableList<Estudiante> = ArrayList()
        estudiantes.add(Estudiante("Pedro Perez", "23", "30", "https://cdn.pixabay.com/photo/2020/05/07/16/07/owl-5142084_1280.jpg"))
        estudiantes.add(Estudiante("Andrea Abarca", "43", "40", "https://cdn.pixabay.com/photo/2020/05/29/06/07/cow-5233887_1280.jpg"))
        estudiantes.add(Estudiante("Diego Calvo", "56", "34", "https://cdn.pixabay.com/photo/2020/05/28/00/27/great-crested-grebe-chick-5229364_1280.jpg"))
        estudiantes.add(Estudiante("Esteban R", "12", "56", "https://cdn.pixabay.com/photo/2020/05/28/13/05/dog-5231137_1280.jpg"))
        estudiantes.add(Estudiante("Dayana Mora", "67", "89", "https://cdn.pixabay.com/photo/2020/05/27/16/56/sandy-bee-5228047_1280.jpg"))
        estudiantes.add(Estudiante("Ricardo perez", "89", "23", "https://cdn.pixabay.com/photo/2020/05/28/15/25/meerkat-5231515_1280.jpg"))
        estudiantes.add(Estudiante("Odilie Watson", "32", "22", "https://cdn.pixabay.com/photo/2020/05/30/11/56/horse-5238629_1280.jpg"))
        estudiantes.add(Estudiante("Oscar Moreno", "33", "21", "https://cdn.pixabay.com/photo/2020/05/27/06/29/dog-5225880_1280.jpg"))


        estudiantes.add(Estudiante("Godzilla", "2000", "2000", "https://cdn.pixabay.com/photo/2020/05/30/11/56/horse-5238629_1280.jpg"))
        estudiantes.add(Estudiante("Kong", "200", "200", "https://cdn.pixabay.com/photo/2020/05/27/06/29/dog-5225880_1280.jpg"))

        return estudiantes
    }

}
