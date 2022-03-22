package com.example.vet

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()  {

    //VARIABLE DEL TIPO LISTA DE CITAS
    var Citas: MutableList<CapaDatos.Cita> = ArrayList()
    //VARIABLE CONTEXTO QUE GUARDARA EL ACTIVITY QUE ESTA INSTANCIANDO EL ADAPTER
    lateinit var context: Context

    //CONSTRUCTOR DE LA CLASE, RECIBE LA LISTA DE CITAS Y EL CONTEXTO Y LOS ALMACENA EN LAS VARIABLES LOCALES
    fun RecyclerAdapter(Citas: MutableList<CapaDatos.Cita>, context: Context){
        this.Citas = Citas
        this.context = context

    }

    //REESCRIBIMOS EL RELLENAR EL VIEW HOLDER PARA UTILIZAR LA LISTA DE CITAS
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Citas.get(position)
        holder.bind(item, context)
    }
    //REESCRIBIMOS EL CREATE DEL VIEW HOLDER PARA INDICARLE EL DISENNO LAYOUT RESPECTIVO CON EL QUE QUEREMOS RELLENAR
    //EL RECYCLER
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //AQUI SELECCIONAMOS EL NOMBRE DEL LAYOUT CORRESPONDIENTE
        return ViewHolder(layoutInflater.inflate(R.layout.item_cita, parent, false))
    }
    //PARA SABER CUANTOS ITEMS VA A TENER EL RECYCLER, ES EL MISMO NUMERO DE CITAS
    override fun getItemCount(): Int {
        return Citas.size
    }
    //CLASE VIEWHOLDER, QUE CORRESPONDE A CADA ITEM DENTRO DEL RECYCLER
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //POR CADA ETIQUETA EN EL LAYOUT DEL ITEM DEL RECYCLERVIEW
        //CREO UNA CONSTANTE RESPECTIVA QUE RECIBE LA ETIQUETA
        val nombrecliente = view.findViewById(R.id.nombrecliente) as TextView
        val nombremascota = view.findViewById(R.id.nombremascota) as TextView
        val raza = view.findViewById(R.id.raza) as TextView
        val edaddemascota = view.findViewById(R.id.edad) as TextView
        val horacit = view.findViewById(R.id.horacita) as EditText
        val notacit = view.findViewById(R.id.notacita) as TextView
        val fotomascota = view.findViewById(R.id.foto) as ImageView
        val botonelimina = view.findViewById(R.id.btn_eliminar) as Button

        //ESTA FUNCION RELLENA CADA ITEM DEL RECYCLERVIEW CON LA INFORMACION RESPECTIVA DE CADA CLASE CITA
        //QUE ESTA EN LA LISTA
        fun bind(Cita: CapaDatos.Cita, context: Context){
            nombrecliente.text = Cita.nombrecliente
            nombremascota.text = Cita.nombremascota
            raza.text = Cita.raza
            edaddemascota.text = Cita.edadmascota
            notacit.text = Cita.descripcita
            fotomascota.loadUrl(Cita.foto)

        //PARA CAMBIAR LA HORA DE CITA AL PRESIONAR UNA TECLA EN EL EDITEXT
            horacit.setText(Cita.horacita.toString())
            horacit.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {//CORROBORA QUE LA TECLA PRESIOANDA
                    //SEA ENTER
                    Cita.horacita = horacit.text.toString().toString()//REESCRIBO EL ATRIBUTO NOTA DE LA CLASE ACTUAL CON LO QUE
                    //ESCRIBIO EL USUARIO EN EL CAMPO
                    Toast.makeText(context,  "La cita de la mascota: " + Cita.nombremascota + " ha sido actualizada", Toast.LENGTH_SHORT).show()
//LE MUESTRO UN MSG AL USUAIRO SOBRE LA MODIFICACION
                    return@OnKeyListener true
                }
                false
            })
            //AL PRESIONAR SOBRE EL BOTON ELIMINAR
            botonelimina.setOnClickListener(){
//CREO UN TEXTO DE DIALOGO PARA EL USUARIO, PROGRAMO QUE PASA CUANDO SE PRESIONA POSITICO O NEGATIVO
                var dialogClickListener =
                    DialogInterface.OnClickListener { dialog, which ->
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                //SI SE PRESIONA POSITIVO
                                CapaDatos.SharedApp.Citas.remove(Cita)//ELIMINO DE LA LISTA DE LA CLASE
                                //COMPARTIDA, LA CITA ACTUAL
                                Toast.makeText(context,  Cita.nombremascota  + " eliminado", Toast.LENGTH_SHORT).show()
                                // LE MUESTRO UN MSG AL USUARIO

                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
//SI PRESIONA NEGATIVO, NO HAGO NADA
                            }
                        }
                    }
//INSTANCIO Y PONGO EL TEXTO DE LA CONSULTA AL USUAIRO, SOBRE SI DESEA ELIMINAR LA CITA
                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder.setMessage("Desea eliminar esta cita "+ Cita.nombremascota +"?").setPositiveButton("Si", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show()//LE ASIGNO AL SI Y AL NO, LA VARIABLE CREADA ANTERIORMENTE
            }

        }
        //FUNCION GENERICA PARA CARGAR URL A UN IMAGEVIEW USANDO LA LIBRERIA PICASSO
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }
}