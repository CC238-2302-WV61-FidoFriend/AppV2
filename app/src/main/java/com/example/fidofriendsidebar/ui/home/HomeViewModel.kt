package com.example.fidofriendsidebar.ui.home

import android.content.Intent
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"

    }


    val text: LiveData<String> = _text
}