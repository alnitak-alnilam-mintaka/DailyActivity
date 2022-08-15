package com.polinasmogi.dailyactivity.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHolder {

    private val retrofit by lazy<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : DailyItemsApi by lazy {
        retrofit.create(DailyItemsApi::class.java)
    }

    private const val BASE_URL = "https://dev-api.com/android_home/"

}