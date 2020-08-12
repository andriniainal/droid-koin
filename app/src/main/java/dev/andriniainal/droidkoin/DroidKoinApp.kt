package dev.andriniainal.droidkoin

import android.app.Application
import dev.andriniainal.droidkoin.di.networkModule
import dev.andriniainal.droidkoin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DroidKoinApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(networkModule, viewModelModule)
        }
    }
}