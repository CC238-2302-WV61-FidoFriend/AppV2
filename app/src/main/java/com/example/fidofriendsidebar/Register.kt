package com.example.fidofriendsidebar

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
import com.example.fidofriendsidebar.ui.profile.ProfileFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Register : AppCompatActivity() {
    private lateinit var service: FidoFriendApi
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_account)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fidofriend-api.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(FidoFriendApi::class.java)

        val registerButton: Button = findViewById(R.id.btnCreate_Account1)
        val firstName: EditText = findViewById(R.id.txtFirstName)
        val lastName: EditText = findViewById(R.id.txtLastName)
        val email: EditText = findViewById(R.id.txtUsername_Email1)
        val password: EditText = findViewById(R.id.txtPassword1)
        val isVet: CheckBox = findViewById(R.id.isVet)

        registerButton.setOnClickListener() {
            service
                .registerUser(
                    RegisterBody(
                        firstName.text.toString(),
                        lastName.text.toString(),
                        email.text.toString(),
                        password.text.toString(),
                        "1999-09-08T02:37:58.061Z",
                        isVet.isChecked)
                )
                .enqueue(object: Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        user = response.body()!!
                        val toastMessage = "Your register, ${user.firstName}!"
                        Toast.makeText(this@Register, toastMessage, Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this@Register, MainActivity::class.java))
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        val toastMessage = "Error with api"
                        Toast.makeText(this@Register, toastMessage, Toast.LENGTH_SHORT).show()
                    }

                })
        }

    }
}