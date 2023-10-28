package com.example.fidofriendsidebar.ui

import Beans.Pet
import Beans.PetRegisterRequest
import Beans.Profile
import Beans.RegisterBody
import Beans.User
import Interface.FidoFriendApi
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.fidofriendsidebar.MainActivity
import com.example.fidofriendsidebar.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditProfile : AppCompatActivity() {
    private lateinit var service: FidoFriendApi
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fidofriend-api.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(FidoFriendApi::class.java)

        val registerButton: Button = findViewById(R.id.EditButton)
        val firstName: EditText = findViewById(R.id.userFirstname)
        val lastName: EditText = findViewById(R.id.userLastname)
        val email: EditText = findViewById(R.id.userEmail)

        registerButton.setOnClickListener() {
            service
                .editUser(
                    Profile(
                        firstName.text.toString(),
                        lastName.text.toString(),
                        email.text.toString())
                )
                .enqueue(object: Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        user = response.body()!!
                        val toastMessage = "Your register, ${user.firstName}!"
                        Toast.makeText(this@EditProfile, toastMessage, Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@EditProfile, MainActivity::class.java))
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        val toastMessage = "Error with api"
                        Toast.makeText(this@EditProfile, toastMessage, Toast.LENGTH_SHORT).show()
                    }

                })
        }

    }
}

