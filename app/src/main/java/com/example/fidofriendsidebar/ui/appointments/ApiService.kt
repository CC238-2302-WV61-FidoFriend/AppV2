package com.example.fidofriendsidebar.ui.appointments

import retrofit2.http.GET

import retrofit2.http.Path

interface ApiService {
    @GET("api/Meeting/{id}")
    suspend fun getMeetingById(@Path("id") id: Int): Meeting
}
