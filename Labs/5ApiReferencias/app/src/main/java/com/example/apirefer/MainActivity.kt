package com.example.apirefer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {

    //lateinit var referencias: MutableList<CapaDatos.REFERENCIA>
    lateinit var mRecyclerView : RecyclerView
     val madapter : Adapter = Adapter()

    fun setUpRecyclerView() {

        mRecyclerView = findViewById(R.id.listadeobras) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        madapter.adapter(CapaDatos.SharedApp.referencias, this)
        mRecyclerView.adapter = madapter

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  referencias = ArrayList()

        btn_referencias.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(CapaDatos.APIService::class.java).registrationPost().execute()

                runOnUiThread {
                    CapaDatos.SharedApp.referencias = call.body()!!
                    Log.i("", call.body()!!.toString())
                    Log.i("", CapaDatos.SharedApp.referencias.toString())
                    setUpRecyclerView()

                }
            }
        }

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://mariomep-001-site1.itempurl.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}



