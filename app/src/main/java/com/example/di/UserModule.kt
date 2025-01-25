package com.example.di

import com.example.fitnessapp.feature_app.data.repository.UserDataRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository
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
}