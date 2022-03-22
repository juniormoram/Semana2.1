package com.example.serializacionfotubi

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var Estudiantes:MutableList<CapaDatos.Estudiante> = ArrayList()

    lateinit var context: Context
    fun RecyclerAdapter(estudiantes: MutableList<CapaDatos.Estudiante>, context: Context){
        this. Estudiantes = estudiantes
        this.context = context

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Estudiantes.get(position)
        holder.bind(item, context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_estudiante, parent, false))
    }
    override fun getItemCount(): Int {
        return Estudiantes.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nombredelestudiante = view.findViewById(R.id.nombre) as TextView
        val carnetdelestudiante = view.findViewById(R.id.carnet) as TextView
        val fotodelestudiante = view.findViewById(R.id.foto) as ImageView
        val localizacion = view.findViewById(R.Id.longi)
        val botonelimina = view.findViewById(R.id.btnelimina) as Button
        fun bind(estudiante: CapaDatos.Estudiante, context: Context){
            nombredelestudiante.text = estudiante.nombre
            carnetdelestudiante.text = "Carnet: " + estudiante.carnet
            fotodelestudiante.loadUrl(estudiante.foto)


            botonelimina.setOnClickListener(){
                var dialogClickListener =
                    DialogInterface.OnClickListener { dialog, which ->
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                CapaDatos.SharedApp.estudiantestodos.remove(estudiante)
                                Toast.makeText(context,  estudiante.nombre + " eliminado", Toast.LENGTH_SHORT).show()
                                nombredelestudiante.setText(nombredelestudiante.text.toString() + " pendiente de eliminar...")
                                val gson = Gson()
                                val listaser = gson.toJson(CapaDatos.SharedApp.estudiantestodos)
                                // sharedPreferences.edit().putString(USER_PROFILE, serializedUser).apply()
                                CapaDatos.SharedApp.prefs.name = listaser
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {

                            }
                        }
                    }

                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder.setMessage("Desea eliminar este estudiante "+ estudiante.nombre +"?").setPositiveButton("Si", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show()
            }

        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }
}