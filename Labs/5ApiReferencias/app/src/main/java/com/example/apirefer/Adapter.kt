package com.example.apirefer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>()  {
    //var estudiantes: MutableList<MainActivity.Estudiante> = ArrayList()
    var referencias:MutableList<CapaDatos.REFERENCIA> = ArrayList()

    lateinit var context: Context

    fun adapter(referencias: MutableList<CapaDatos.REFERENCIA>, context: Context){
        this.referencias = referencias
        this.context = context

    }
    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val item = referencias.get(position)
        holder.bind(item, context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Adapter.ViewHolder(layoutInflater.inflate(R.layout.item_referencia, parent, false))
    }

    override fun getItemCount(): Int {
        return referencias.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val fecha = view.findViewById(R.id.fecha) as TextView
        val numero = view.findViewById(R.id.numero) as TextView
        val razon = view.findViewById(R.id.razon) as TextView
        val botonselecciona = view.findViewById(R.id.btnselecciona) as Button


        fun bind(referencias:CapaDatos.REFERENCIA, context: Context){
            fecha.text = referencias.CODIGO1
            numero.text = referencias.NUMERO1
            razon.text= referencias.RAZON1


        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }




}