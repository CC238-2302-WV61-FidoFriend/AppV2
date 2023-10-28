package com.example.fidofriendsidebar

import Beans.LoginBody
import Beans.User
import Interface.FidoFriendApi
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {
    private lateinit var service: FidoFriendApi
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fidofriend-api.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(FidoFriendApi::class.java)

        val createAccountBtn: Button = findViewById(R.id.btnCreate_Account)
        val loginButton: Button = findViewById(R.id.btnLog_in)
        val emailText: EditText = findViewById(R.id.txtUsername_Email)
        val passwordText: EditText = findViewById(R.id.txtPassword)

        createAccountBtn.setOnClickListener() {
            startActivity(Intent(this, Register::class.java))
        }

        loginButton.setOnClickListener() {
            service
                .loginUser(LoginBody(emailText.text.toString(), passwordText.text.toString()))
                .enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        user = response.body()!!
                        val toastMessage = "Welcome, ${user.firstName}!"
                        Toast.makeText(this@Login, toastMessage, Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@Login, Service::class.java))
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        TODO("Not yet implemented")
                    }


                })
        }
    }
}