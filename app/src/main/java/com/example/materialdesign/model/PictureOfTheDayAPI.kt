package com.example.materialdesign.model

import com.example.materialdesign.BuildConfig
import com.example.materialdesign.END_POINT_NASA
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET(END_POINT_NASA)
    fun getPictureOfTheDay(@Query(BuildConfig.NASA_API_KEY) apiKey: String): Call<PictureOfTheDayServerResponseData>
}