package com.polinasmogi.dailyactivity.di

import com.polinasmogi.dailyactivity.interactor.ActivityInteractor
import org.koin.dsl.module

val interactorsModule = module {
    single { ActivityInteractor(get()) }
}