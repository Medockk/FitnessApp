package com.example.di

import com.example.fitnessapp.feature_app.data.repository.AuthRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.AuthRepository
import com.example.fitnessapp.feature_app.domain.usecase.Auth.CreateProfileUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SelectPurposeUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignInUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignInWithGoogleUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignUpUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignUpWithGoogleUseCase
import org.koin.dsl.module

val moduleAuth = module {

    single<AuthRepository> {
        AuthRepositoryImpl()
    }

    factory<SignInUseCase> {
        SignInUseCase(get())
    }

    factory<SignInWithGoogleUseCase> {
        SignInWithGoogleUseCase(get())
    }

    factory<SignUpUseCase> {
        SignUpUseCase(get())
    }

    factory<SignUpWithGoogleUseCase> {
        SignUpWithGoogleUseCase(get())
    }

    factory<SelectPurposeUseCase> {
        SelectPurposeUseCase(get())
    }

    factory<CreateProfileUseCase> {
        CreateProfileUseCase(get())
    }
}