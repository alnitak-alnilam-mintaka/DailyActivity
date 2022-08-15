package com.polinasmogi.dailyactivity.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "weekly_activity")
data class ActivityEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "daily_goal")
    val dailyGoal: Int,

    @ColumnInfo(name = "daily_activity")
    val dailyActivity: Int,

    @ColumnInfo(name = "daily_distance_meters")
    val dailyDistanceMeters: Int,

    @ColumnInfo(name = "daily_kcal")
    val dailyKcal: Int

)