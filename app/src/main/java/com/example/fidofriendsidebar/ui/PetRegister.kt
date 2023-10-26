package com.example.fidofriendsidebar.ui

import Beans.Pet
import Beans.PetRegisterRequest
import Interface.FidoFriendApi
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fidofriendsidebar.MainActivity
import com.example.fidofriendsidebar.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PetRegister : AppCompatActivity() {
    private lateinit var service: FidoFriendApi
    lateinit var pet: Pet
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_register)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fidofriend-api.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(FidoFriendApi::class.java)

        val registerButton: Button = findViewById(R.id.registerPetBtn)
        val name: EditText = findViewById(R.id.petName)
        val age: EditText = findViewById(R.id.petAge)
        val description: EditText = findViewById(R.id.petDescription)
        val imgUrl: EditText = findViewById(R.id.petImgUrl)
        val sex: EditText = findViewById(R.id.petSex)

        registerButton.setOnClickListener() {
            service
                .registerPet(
                    PetRegisterRequest(name.text.toString(), age.text.toString().toInt(), description.text.toString(), imgUrl.text.toString(), sex.text.toString(), 1)
                )
                .enqueue(object: Callback<Pet> {
                    override fun onResponse(call: Call<Pet>, response: Response<Pet>) {
                        pet = response.body()!!

                        Toast.makeText(this@PetRegister, "Pet Register", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@PetRegister, MainActivity::class.java))
                    }

                    override fun onFailure(call: Call<Pet>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
        }

    }
}