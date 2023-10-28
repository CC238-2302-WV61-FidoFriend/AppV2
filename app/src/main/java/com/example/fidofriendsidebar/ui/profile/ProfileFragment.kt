package com.example.fidofriendsidebar.ui.profile

import Beans.User
import Interface.FidoFriendApi
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fidofriendsidebar.R
import com.example.fidofriendsidebar.databinding.FragmentHomeBinding
import com.example.fidofriendsidebar.databinding.FragmentProfileBinding
import com.example.fidofriendsidebar.ui.EditProfile
import com.example.fidofriendsidebar.ui.PetRegister
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileFragment : Fragment() {

    private lateinit var service: FidoFriendApi
    private lateinit var user: User

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fidofriend-api.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(FidoFriendApi::class.java)

        service.getUserById(1).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                user = response.body()!!

                val firstName: TextView = view.findViewById(R.id.textViewFirstName)
                val lastName: TextView = view.findViewById(R.id.textViewLastName)
                val email: TextView = view.findViewById(R.id.textViewEmail)
                val userImg: ImageView = view.findViewById(R.id.imageViewAvatar)

                firstName.text = "Nombre: " + user.firstName
                lastName.text = "Apellido: " + user.lastName
                email.text = "Email: " + user.email
                Picasso.get().load(user.imgUrl).placeholder(R.drawable.baseline_face_24)
                    .error(R.drawable.baseline_face_24)
                    .into(userImg)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
            }
        })

        val buttonEditProfile: Button = view.findViewById(R.id.buttonEditProfile)
        buttonEditProfile.setOnClickListener {
            val intent = Intent(activity, EditProfile::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }
}