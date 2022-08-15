package com.polinasmogi.dailyactivity

import android.app.Application
import com.polinasmogi.dailyactivity.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                viewModelModule,
                repositoryModule,
                databaseModule,
                networkModule,
                interactorsModule
            )
        }
    }

}