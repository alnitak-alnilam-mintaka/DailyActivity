package com.polinasmogi.dailyactivity.di

import androidx.room.Room
import com.polinasmogi.dailyactivity.database.ActivityDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), ActivityDatabase::class.java, ActivityDatabase.DATABASE_NAME).build()
    }
    single { get<ActivityDatabase>().weeklyDataDao() }
}