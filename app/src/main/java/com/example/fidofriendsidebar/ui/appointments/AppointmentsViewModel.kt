package com.example.fidofriendsidebar.ui.appointments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppointmentsViewModel : ViewModel() {
    private val apiService: ApiService

    private val _meetings = MutableLiveData<List<Meeting>>()
    val meetings: LiveData<List<Meeting>> = _meetings

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fidofriend-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        loadAllMeetings() // Cargar todas las reuniones
    }

    // Función para cargar todas las reuniones desde la API
    fun loadAllMeetings() {
        viewModelScope.launch {
            try {
                // Realizar solicitudes individuales por cada ID y almacenar los resultados en una lista
                val allMeetings = mutableListOf<Meeting>()
                var id = 1 // Empezar desde el primer ID disponible

                while (true) {
                    try {
                        val response = apiService.getMeetingById(id)
                        allMeetings.add(response)
                        id++
                    } catch (e: Exception) {
                        // Si no se encuentra un ID, salir del bucle
                        break
                    }
                }

                _meetings.value = allMeetings
            } catch (e: Exception) {
                // Manejar cualquier error aquí
            }
        }
    }
}
