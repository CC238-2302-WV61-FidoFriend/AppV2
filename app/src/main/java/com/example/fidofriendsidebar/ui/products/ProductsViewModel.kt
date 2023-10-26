package com.example.fidofriendsidebar.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Payment Fragment"
    }
    val text: LiveData<String> = _text
}