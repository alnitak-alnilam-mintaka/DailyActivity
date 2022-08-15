package com.polinasmogi.dailyactivity.models

import com.google.gson.annotations.SerializedName

data class WeeklyDataModel(
    @SerializedName("weekly_data")
    val weeklyData: List<WeeklyDataItem>,
    @SerializedName("err")
    val err: Int
)

data class WeeklyDataItem(
    @SerializedName("daily_item")
    val dailyItem: DailyItem,
    @SerializedName("daily_data")
    val dailyData: DailyData
)

data class DailyItem(
    @SerializedName("daily_goal")
    val dailyGoal: Int,
    @SerializedName("daily_activity")
    val dailyActivity: Int
)

data class DailyData(
    @SerializedName("daily_distance_meters")
    val dailyDistanceMeters: Int,
    @SerializedName("daily_kcal")
    val dailyKcal: Int
)