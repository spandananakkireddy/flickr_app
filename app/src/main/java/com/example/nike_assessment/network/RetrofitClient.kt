package com.example.nike_assessment.network

import com.example.nike_assessment.shared.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private val retrofitClient: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
    }

    val retrofitService: RetrofitService by lazy {
        retrofitClient
                .build()
                .create(RetrofitService::class.java)
    }
}