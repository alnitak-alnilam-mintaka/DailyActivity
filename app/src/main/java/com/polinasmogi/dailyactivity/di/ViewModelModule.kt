package com.polinasmogi.dailyactivity.di

import com.polinasmogi.dailyactivity.ui.dailyactivity.DailyActivityViewModel
import com.polinasmogi.dailyactivity.ui.timeline.TimeLineViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule : Module = module {
    viewModel { DailyActivityViewModel(get()) }
    viewModel { TimeLineViewModel(get()) }
}