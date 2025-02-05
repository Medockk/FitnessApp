package com.example.di

import com.example.fitnessapp.feature_app.data.repository.SleepRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.SleepRepository
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.AddAlarmUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.ChangeEnabledUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetSleepDataUseCase
import org.koin.dsl.module

val moduleSleep = module {

    single<SleepRepository> {
        SleepRepositoryImpl()
    }

    factory<GetSleepDataUseCase> {
        GetSleepDataUseCase(get())
    }

    factory<ChangeEnabledUseCase> {
        ChangeEnabledUseCase(get())
    }

    factory<AddAlarmUseCase> {
        AddAlarmUseCase(get())
    }
}