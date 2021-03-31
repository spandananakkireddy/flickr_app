package com.example.nike_assessment.network

import com.example.nike_assessment.model.Items
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("photos_public.gne?")
    fun getImages(@Query("nojsoncallback") jsonCallback: String, @Query("tagmode")
    tagmode: String, @Query("format") format: String, @Query("tags") tags: String)
            : Call<Items>
}