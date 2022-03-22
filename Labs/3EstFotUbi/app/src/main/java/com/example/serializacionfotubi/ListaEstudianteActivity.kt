package com.example.serializacionfotubi

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_lista.*
import kotlinx.android.synthetic.main.item_estudiante.*


class ListaEstudianteActivity : AppCompatActivity()  {
    private var locationManager : LocationManager? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var mRecyclerView : RecyclerView
    val mAdapter : RecyclerAdapter = RecyclerAdapter()

    private val PERMISSION_CODE = 1000;
    private val IMAGE_CAPTURE_CODE = 1001
    var image_uri: Uri? = null
    var ejex:String=""
    var ejey:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        //SE RECUPERA DE LA PERSISTENCIA PREFERENCE, LA LISTA SERIALIZADA DE ESTUDIANTES
        val listaser = CapaDatos.SharedApp.prefs.name
        if(listaser != "")//SI LA LISTA NO ESTA VACIA
        {
            //CREAMOS UN OBJETO COMPLEJO, LISTA DE ESTUDIANTES, A PARTIR DEL TEXTO PLANO RECUPERADO DEL XML
            val gson = GsonBuilder().create()
            val Model= gson.fromJson(listaser,Array<CapaDatos.Estudiante>::class.java).toMutableList()
            CapaDatos.SharedApp.estudiantestodos = Model //ASIGNAMOS LA LISTA DESERIALIZADA A LA LISTA COMPARTIDA

        }
        setUpRecyclerView()

        btn_agregar.setOnClickListener {
            //AGREGAMOS UN NUEVO ESTUDIANTE A LA LISTA COMPARTIDA
            actualizaestudiantes(CapaDatos.Estudiante(txt_nombre.text.toString(), txt_carnet.text.toString(),image_uri.toString(),ejex, ejey))
            val gson = Gson()
            //SERIALIZAMOS LA LISTA COMPARTIDA, PARA CONVERTIRLA EN TEXTO PLANO
            val listaser = gson.toJson(CapaDatos.SharedApp.estudiantestodos)

            CapaDatos.SharedApp.prefs.name = listaser//GUARDAMOS EL TEXTO PLANO, EN EL PREFERENCE, ARCHIVO XML

        }

        btn_captura.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED){
                    val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                }
                else{
                    openCamera()
                }
            }
            else{
                openCamera()
            }
        }
        requestPermissions()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (!checkPermissions()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions()
            }
        }
        else {

        }

    }

    private fun showSnackbar(
        mainTextStringId: String, actionStringId: String,
        listener: View.OnClickListener
    ) {
        Toast.makeText(this@ListaEstudianteActivity, mainTextStringId, Toast.LENGTH_LONG).show()
    }
    private fun requestPermissions() {
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (shouldProvideRationale) {
            Log.i("", "Mostrar el permiso.")
            showSnackbar("Se necesitan permisos de ubicación", "Bien",
                View.OnClickListener {
                    startLocationPermissionRequest()
                })
        }
        else {
            Log.i("", "Solicitando permisos")
            startLocationPermissionRequest()
        }
    }
    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(
            this@ListaEstudianteActivity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 34)
    }
    private fun checkPermissions(): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        return permissionState == PackageManager.PERMISSION_GRANTED
    }
    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New pic")
        values.put(MediaStore.Images.Media.DESCRIPTION, "from the camera")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){

            foto.setImageURI(image_uri)
            getLastLocation()
        }
    }

    fun actualizaestudiantes(nuevoestudiante: CapaDatos.Estudiante): MutableList<CapaDatos.Estudiante>{
        // estudiantes.clear()
        CapaDatos.SharedApp.estudiantestodos.add(nuevoestudiante)
        setUpRecyclerView()
        Toast.makeText(this, "Nuevo estudiante agregado", Toast.LENGTH_SHORT).show()
        return CapaDatos.SharedApp.estudiantestodos
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient?.lastLocation!!.addOnCompleteListener(this) { task ->
            if (task.isSuccessful && task.result != null) {
                var   lastLocation = task.result
                Log.d("lista en memoria", CapaDatos.SharedApp.prefs.name.toString())

                ejex= (lastLocation)!!.latitude.toString()
                ejey= (lastLocation)!!.longitude.toString()


            }
            else {
                Log.w("", "getLastLocation:exception", task.exception)
                Toast.makeText(this,"Sin permisos",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun setUpRecyclerView(){

        mRecyclerView = findViewById(R.id.listadeestudiantes) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapter(CapaDatos.SharedApp.estudiantestodos, this)
        mRecyclerView.adapter = mAdapter

    }
}