package com.example.fidofriendsidebar.ui.home

import Beans.Pet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fidofriendsidebar.R

class PetAdapter(val petList: List<Pet>): RecyclerView.Adapter<PetListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PetListViewHolder(layoutInflater.inflate(R.layout.pet_card, parent, false))
    }

    override fun getItemCount(): Int = petList.size

    override fun onBindViewHolder(holder: PetListViewHolder, position: Int) {
        val item = petList[position]
        holder.render(item)
    }

}