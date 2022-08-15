package com.polinasmogi.dailyactivity.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeeklyDataDao {

    @Query("SELECT * FROM weekly_activity")
    fun getWeeklyData(): Flow<List<ActivityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeeklyData(weeklyData: List<ActivityEntity>)

    @Query("DELETE FROM weekly_activity")
    fun deleteAll()

}