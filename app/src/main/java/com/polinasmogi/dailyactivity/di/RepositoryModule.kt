package com.polinasmogi.dailyactivity.di

import com.polinasmogi.dailyactivity.database.ActivitySource
import com.polinasmogi.dailyactivity.repository.DailyItemsRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule : Module = module {
    single { DailyItemsRepository(get(), get(), get()) }
    single { ActivitySource(get()) }
}