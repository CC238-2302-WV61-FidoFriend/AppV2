package com.example.fidofriendsidebar.ui.services

import Beans.Service
import Interface.FidoFriendApi
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fidofriendsidebar.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ServicesFragment : Fragment() {

    private lateinit var servicea: FidoFriendApi
    private lateinit var services: List<Service>

    private lateinit var viewModel: ServicesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_services, container, false)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fidofriend-api.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        servicea = retrofit.create(FidoFriendApi::class.java)

        servicea.getServices().enqueue(object : Callback<List<Service>> {
            override fun onResponse(call: Call<List<Service>>, response: Response<List<Service>>) {
                val name: TextView = view.findViewById(R.id.textViewName)
                val description: TextView = view.findViewById(R.id.textViewDescription)
                val imgUrl: TextView = view.findViewById(R.id.textViewimgUrl)


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
                    Log.e(
                        "ServiceActivity",
                        "Error en la respuesta de la API: ${response.code()} - ${response.message()}"
                    )
                }

            }
            override fun onFailure(call: Call<List<Service>>, t: Throwable) {
                Log.e("ServiceActivity", "Error en la solicitud a la API: ${t.message}")
            }
        })
        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ServicesViewModel::class.java)
        // TODO: Use the ViewModel
    }


}