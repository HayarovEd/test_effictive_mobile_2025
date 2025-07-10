package com.edurda77.test_effictive_mobile_2025

import android.app.Application
import com.edurda77.test_effictive_mobile_2025.di.baseModule
import com.edurda77.test_effictive_mobile_2025.di.repoModule
import com.edurda77.test_effictive_mobile_2025.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                baseModule, repoModule, viewModelModule
            )
        }
    }
}