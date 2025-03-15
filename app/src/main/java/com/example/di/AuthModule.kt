package com.example.di

import com.example.fitnessapp.feature_app.data.repository.AuthRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.AuthRepository
import com.example.fitnessapp.feature_app.domain.usecase.Auth.CreateProfileUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SelectPurposeUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignInUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignInWithGoogleUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignUpUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignUpWithGoogleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun getAuthRepository() : AuthRepository{
        return AuthRepositoryImpl()
    }

    //FACTORIES
    @Singleton @Provides
    fun createProfileUseCase(authRepository: AuthRepository) : CreateProfileUseCase{
        return CreateProfileUseCase(authRepository)
    }
    @Singleton @Provides
    fun selectPurposeUseCase(authRepository: AuthRepository) : SelectPurposeUseCase{
        return SelectPurposeUseCase(authRepository)
    }
    @Singleton @Provides
    fun signInUseCase(authRepository: AuthRepository) : SignInUseCase{
        return SignInUseCase(authRepository)
    }
    @Singleton @Provides
    fun signUpUseCase(authRepository: AuthRepository) : SignUpUseCase {
        return SignUpUseCase(authRepository)
    }
    @Singleton @Provides
    fun signInWithGoogleUseCase(authRepository: AuthRepository) : SignInWithGoogleUseCase{
        return SignInWithGoogleUseCase(authRepository)
    }
    @Singleton @Provides
    fun signUpWithGoogleUseCase(authRepository: AuthRepository) : SignUpWithGoogleUseCase {
        return SignUpWithGoogleUseCase(authRepository)
    }
}