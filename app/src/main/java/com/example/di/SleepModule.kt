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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SleepModule {

    @Provides
    @Singleton
    fun getSleepRepository() : SleepRepository{
        return SleepRepositoryImpl()
    }

    //factories
    @Provides @Singleton
    fun addAlarmUseCase(sleepRepository: SleepRepository) : AddAlarmUseCase{
        return AddAlarmUseCase(sleepRepository)
    }
    @Provides @Singleton
    fun changeAlarmEnabledUseCase(sleepRepository: SleepRepository) : ChangeAlarmEnabledUseCase{
        return ChangeAlarmEnabledUseCase(sleepRepository)
    }
    @Provides @Singleton
    fun changeSleepEnabledUseCase(sleepRepository: SleepRepository) : ChangeSleepEnabledUseCase{
        return ChangeSleepEnabledUseCase(sleepRepository)
    }
    @Provides @Singleton
    fun getAlarmClockDataByDateUseCase(sleepRepository: SleepRepository) : GetAlarmClockDataByDateUseCase{
        return GetAlarmClockDataByDateUseCase(sleepRepository)
    }
    @Provides @Singleton
    fun getAlarmClockDataUseCase(sleepRepository: SleepRepository) : GetAlarmClockDataUseCase{
        return GetAlarmClockDataUseCase(sleepRepository)
    }
    @Provides @Singleton
    fun getSleepDataByDateUseCase(sleepRepository: SleepRepository) : GetSleepDataByDateUseCase{
        return GetSleepDataByDateUseCase(sleepRepository)
    }
    @Provides @Singleton
    fun getSleepDataUseCase(sleepRepository: SleepRepository) : GetSleepDataUseCase{
        return GetSleepDataUseCase(sleepRepository)
    }
}