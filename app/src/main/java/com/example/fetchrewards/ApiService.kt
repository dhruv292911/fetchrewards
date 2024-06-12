package com.example.fetchrewards

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    fun getData(): Call<List<Rewards>>
}