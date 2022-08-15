package com.polinasmogi.dailyactivity.api

import com.polinasmogi.dailyactivity.models.WeeklyDataModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface DailyItemsApi {

    @GET("daily_items")
    fun getDailyItems(): Call<WeeklyDataModel>

}