package com.example.materialdesign.model

import com.example.materialdesign.END_POINT_NASA
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET(END_POINT_NASA)
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<PictureOfTheDayServerResponseData>

    @GET(END_POINT_NASA)
    fun getPictureOfTheDay(@Query("api_key") apiKey: String, @Query("date") date: String): Call<PictureOfTheDayServerResponseData>
}