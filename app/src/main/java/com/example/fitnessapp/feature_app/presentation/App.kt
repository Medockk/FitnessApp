package com.example.fitnessapp.feature_app.presentation

import android.app.Application
import com.example.di.moduleAuth
import com.example.di.moduleCompare
import com.example.di.moduleMeal
import com.example.di.moduleQueue
import com.example.di.moduleRetrofitUserData
import com.example.di.moduleSleep
import com.example.di.moduleUser
import com.example.di.moduleViewModel
import com.example.di.moduleWorkout
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    moduleQueue, moduleViewModel, moduleAuth,
                    moduleUser, moduleWorkout, moduleMeal,
                    moduleSleep, moduleCompare, moduleRetrofitUserData
                )
            )
        }
    }
}