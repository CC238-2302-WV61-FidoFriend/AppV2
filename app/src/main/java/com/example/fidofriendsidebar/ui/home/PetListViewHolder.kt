package com.example.fidofriendsidebar.ui.home

import Beans.Pet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fidofriendsidebar.R
import com.squareup.picasso.Picasso

class PetListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val name = view.findViewById<TextView>(R.id.namePetCard)
    val age = view.findViewById<TextView>(R.id.agePetCard)
    val description = view.findViewById<TextView>(R.id.descriptionPetCard)
    val imgUrl = view.findViewById<ImageView>(R.id.imgPetCard)
    val sex = view.findViewById<TextView>(R.id.sexPetCard)

    fun render(petModel: Pet) {
        name.text = petModel.name.toString()
        age.text = petModel.age.toString()
        description.text = petModel.description.toString()
        sex.text = petModel.sex.toString()
        Picasso.get().load(petModel.imgUrl).into(imgUrl)
    }

}