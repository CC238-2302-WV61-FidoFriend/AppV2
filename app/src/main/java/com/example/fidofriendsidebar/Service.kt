package com.example.fidofriendsidebar

import Beans.Service
import Interface.FidoFriendApi
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service : AppCompatActivity() {
    private lateinit var servicea: FidoFriendApi
    private lateinit var services: List<Service>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fidofriend-api.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        servicea = retrofit.create(FidoFriendApi::class.java)

        servicea.getServices().enqueue(object : Callback<List<Service>> {
            override fun onResponse(call: Call<List<Service>>, response: Response<List<Service>>) {
                val name: TextView = findViewById(R.id.textViewName)
                val description: TextView = findViewById(R.id.textViewDescription)
                val imgUrl: TextView = findViewById(R.id.textViewimgUrl)


                if (response.isSuccessful) {
                    services = response.body() ?: emptyList()

                    if (services.isNotEmpty()) {
                        val firstService = services[0]
                        name.text = "Nombre: " + firstService.name
                        description.text = "Descripcion: " + firstService.description
                        imgUrl.text = "ImgUrl: " + firstService.imgUrl
                    } else {
                        Log.e("ServiceActivity", "La lista de servicios está vacía.")
                    }
                } else {
                    Log.e("ServiceActivity", "Error en la respuesta de la API: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Service>>, t: Throwable) {
                Log.e("ServiceActivity", "Error en la solicitud a la API: ${t.message}")
            }

        })
    }
}