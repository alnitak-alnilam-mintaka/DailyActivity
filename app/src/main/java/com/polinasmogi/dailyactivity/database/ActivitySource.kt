package com.polinasmogi.dailyactivity.database

import kotlinx.coroutines.flow.Flow

class ActivitySource(private val dao: WeeklyDataDao) {

    fun getWeeklyData(): Flow<List<ActivityEntity>> = dao.getWeeklyData()

    fun insertWeeklyData(weeklyData: List<ActivityEntity>) = dao.insertWeeklyData(weeklyData)

}