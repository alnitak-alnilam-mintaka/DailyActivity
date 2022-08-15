package com.polinasmogi.dailyactivity.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [ ActivityEntity::class ], version = 2, exportSchema = true)
abstract class ActivityDatabase : RoomDatabase() {

    abstract fun weeklyDataDao(): WeeklyDataDao

    internal class PopulateDbAsyn(database: ActivityDatabase?) : AsyncTask<Void?, Void?, Void?>() {
        private val weeklyDataDao = database?.weeklyDataDao()
        override fun doInBackground(vararg p0: Void?): Void? {
            weeklyDataDao?.deleteAll()
            return null
        }
    }

    companion object {
        const val DATABASE_NAME = "weekly_activity_database"

        @Volatile
        var INSTANCE: ActivityDatabase? = null
        fun getInstance(context: Context): ActivityDatabase? {
            if (INSTANCE == null) {
                synchronized(ActivityDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            ActivityDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        var callback: Callback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                ActivityDatabase.PopulateDbAsyn(INSTANCE)
            }
        }
    }

}