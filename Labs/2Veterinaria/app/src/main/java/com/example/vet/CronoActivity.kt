package com.example.vet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_cronograma.*

class CronoActivity : AppCompatActivity() {

    //DECLARAMOS LA VARIABLE PARA ALAMCENAR EL RECYCLER VIEW
    lateinit var mRecyclerView : RecyclerView
    //DECLARAMOS LA VARIABLE PARA ALMACENAR EL ADAPTADOR QUE ENLAZA EL RECYCLERVIEW CON NUESTROS DATOS
    val mAdapter : RecyclerAdapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cronograma)
        setUpRecyclerView()//RELLENAR EL RECYCLER VIEW
        btn_agregar.setOnClickListener {
//AL PRESIONAR SOBRE EL BOTON DE AGREGAR ESTUDIANTE
            actualizacitas(CapaDatos.Cita(txt_nombrecliente.text.toString(), txt_nombremascota.text.toString(),txt_raza.text.toString(), txt_edad.text.toString(),txt_horacita.text.toString(),txt_notacita.text.toString(),"https://png.pngtree.com/png-clipart/20190515/original/pngtree-pets-vector-logo-template-this-cat-and-dog-logo-could-be-png-image_3641318.jpg"))
//SE LLAMA A LA FUNCION DE ACTUALIZAR ESTUDIANTE Y SE LE PASAN LOS CAMPOS RESPECTIVOS DEL NUEVO ESTUDIANTE
            //QUE EL USUAIRO RELLENO EN EL ALYOUT

        }
    }

    //ESTA FUNCION RECIBE UNA CITA Y LO GUARDA EN LA LISTA DE LA CLASE SHAREDAPP QUE YA VIMOS ES COMPARTIDA PARA TODA LA
    //APLICACION
    fun actualizacitas(nuevacita: CapaDatos.Cita): MutableList<CapaDatos.Cita>{

        CapaDatos.SharedApp.Citas.add(nuevacita)
        setUpRecyclerView()//RELLENAMOS NUEVAMENTE EL RECYCLER VIEW
        Toast.makeText(this, "Nueva cita agregada", Toast.LENGTH_SHORT).show()//LE MOSTRAMOS UN MSG AL USUARIO
        return CapaDatos.SharedApp.Citas//DEVOLVEMOS LA LISTA DE ESTUDIANTE GLOBAL
    }
    //FUNCION PARA RELLENAR EL RECYCLERVIEW CON EL ADAPTADOR RECYCLERADAPTER
    fun setUpRecyclerView(){


        mRecyclerView = findViewById(R.id.listadecitas) as RecyclerView//ASIGNAMOS EL RECYCLERVIEW DEL LAYOUT
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)//CONFGURACION GENERICA DEL RECYCLERVIEW
        mAdapter.RecyclerAdapter(CapaDatos.SharedApp.Citas, this)//INSTANCIAMOS EL ADAPTADOR CON LA LISTA DE
        //ESTUDIANTES ACTUAL EN LA CLASE COMPARTIDA
        mRecyclerView.adapter = mAdapter//LE ASIGNAMOS EL ADAPTER AL RECYCLERVIEW

    }

}