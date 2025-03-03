package com.example.di

import com.example.fitnessapp.feature_app.data.repository.SleepRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.SleepRepository
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.AddAlarmUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.ChangeAlarmEnabledUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.ChangeSleepEnabledUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetAlarmClockDataByDateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetAlarmClockDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetSleepDataByDateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetSleepDataUseCase
import org.koin.dsl.module

val moduleSleep = module {

    single<SleepRepository> {
        SleepRepositoryImpl()
    }

    factory<GetSleepDataUseCase> {
        GetSleepDataUseCase(get())
    }

    factory<ChangeSleepEnabledUseCase> {
        ChangeSleepEnabledUseCase(get())
    }

    factory<AddAlarmUseCase> {
        AddAlarmUseCase(get())
    }

    factory<GetAlarmClockDataUseCase> {
        GetAlarmClockDataUseCase(get())
    }

    factory<ChangeAlarmEnabledUseCase> {
        ChangeAlarmEnabledUseCase(get())
    }
    factory<GetSleepDataByDateUseCase> {
        GetSleepDataByDateUseCase(get())
    }
    factory<GetAlarmClockDataByDateUseCase> {
        GetAlarmClockDataByDateUseCase(get())
    }
}