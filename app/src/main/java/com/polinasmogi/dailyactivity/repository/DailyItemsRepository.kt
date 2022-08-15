package com.polinasmogi.dailyactivity.repository

import android.content.Context

import com.polinasmogi.dailyactivity.api.DailyItemsApi
import com.polinasmogi.dailyactivity.database.ActivitySource
import com.polinasmogi.dailyactivity.utils.toActivityEntity
import com.polinasmogi.dailyactivity.models.WeeklyDataModel
import retrofit2.Call
import java.util.*


class DailyItemsRepository(context: Context, private val dataSource: ActivitySource, private val api: DailyItemsApi) {

    private val sharedPreference = context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)
    private val editor = sharedPreference.edit()

    fun getLastRequestTime() = sharedPreference.getLong(LAST_REQUEST_TIME, 0)

    fun getWeeklyDataFromApi(): Call<WeeklyDataModel> = api.getDailyItems()

    fun safeWeeklyDataInDatabase(weeklyData: WeeklyDataModel) {
        dataSource.insertWeeklyData(weeklyData.weeklyData.toActivityEntity())
        editor.putLong(LAST_REQUEST_TIME, Calendar.getInstance().timeInMillis)
        editor.apply()
    }

    fun getWeeklyDataFromDatabase() = dataSource.getWeeklyData()

    companion object {
        private const val LAST_REQUEST_TIME = "lastRequestTime"
    }

}