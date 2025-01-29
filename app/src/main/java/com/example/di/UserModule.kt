package com.example.di

import com.example.fitnessapp.feature_app.data.repository.UserDataRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository
import com.example.fitnessapp.feature_app.domain.usecase.Statistic.GetUserBodyMassIndexUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Statistic.GetUserStatisticsUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetHeartRateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetLastActivityUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetNotificationsUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetPurposeUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.UpdateUserDataUseCase
import org.koin.dsl.module

val moduleUser = module {

    single<UserDataRepository> {
        UserDataRepositoryImpl()
    }

    factory<GetUserDataUseCase> {
        GetUserDataUseCase(get())
    }

    factory<UpdateUserDataUseCase> {
        UpdateUserDataUseCase(get())
    }

    factory<GetUserStatisticsUseCase> {
        GetUserStatisticsUseCase(get())
    }

    factory<GetUserBodyMassIndexUseCase> {
        GetUserBodyMassIndexUseCase(get())
    }

    factory<GetNotificationsUseCase> {
        GetNotificationsUseCase(get())
    }

    factory<GetPurposeUseCase> {
        GetPurposeUseCase(get())
    }

    factory<GetLastActivityUseCase> {
        GetLastActivityUseCase(get())
    }

    factory<GetHeartRateUseCase> {
        GetHeartRateUseCase(get())
    }
}