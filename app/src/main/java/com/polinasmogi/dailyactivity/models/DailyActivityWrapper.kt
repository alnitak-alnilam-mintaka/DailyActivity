package com.polinasmogi.dailyactivity.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DailyActivityWrapper(
    val dailyGoal: String,
    val dailyGoalInPercents: Int,
    val dailyGoalInPercentsForTimeline: Int,
    val dailyActivity: Int,
    val dailyActivityInPercents: Int,
    val goalReached: Boolean,
    val dailyKcal: String,
    val dailyDistanceMeters: String,
    val dayOfMonth: String,
    val month: String,
    val dayOfWeek: String,
    val isToday: Boolean
) : Parcelable