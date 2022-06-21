package com.example.materialdesign.model

import com.example.materialdesign.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PictureOfTheDayRetrofitImpl {
    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(PictureOfTheDayAPI::class.java)
    }
    fun getRetrofitImpl(): PictureOfTheDayAPI{
        val podRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return podRetrofit.create(PictureOfTheDayAPI::class.java)
    }

    fun getPictureOfTheDay(apiKey: String, date: String, podCallback: Callback<PictureOfTheDayServerResponseData>) {
        api.getPictureOfTheDay(apiKey, date).enqueue(podCallback)
    }
}