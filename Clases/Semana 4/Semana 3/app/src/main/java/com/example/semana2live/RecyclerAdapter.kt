package com.example.semana2live

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var estudiantes: MutableList<MainActivity.Estudiante>  = ArrayList()
    lateinit var context:Context

    fun RecyclerAdapter(superheros : MutableList<MainActivity.Estudiante>, context: Context){
        this.estudiantes = superheros
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = estudiantes.get(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_estudiante_list, parent, false))
    }

    override fun getItemCount(): Int {
        return estudiantes.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreest = view.findViewById(R.id.nombre) as TextView
        val edadest = view.findViewById(R.id.edad) as TextView
        val carnetest = view.findViewById(R.id.carnet) as TextView
        val fotoest = view.findViewById(R.id.foto) as ImageView

        fun bind(estudiante: MainActivity.Estudiante, context: Context){
            nombreest.text = estudiante.nombre
            edadest.text = estudiante.edad
            carnetest.text = estudiante.carnet
            fotoest.loadUrl(estudiante.foto)
            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(context, "Selecciono el estudiante: " + estudiante.nombre, Toast.LENGTH_SHORT).show() })
        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }
}