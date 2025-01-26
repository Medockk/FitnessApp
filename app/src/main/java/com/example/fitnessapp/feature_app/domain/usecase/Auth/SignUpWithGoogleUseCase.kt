package com.example.fitnessapp.feature_app.domain.usecase.Auth

import com.example.fitnessapp.feature_app.domain.repository.AuthRepository
import io.github.jan.supabase.compose.auth.composable.NativeSignInState

class SignUpWithGoogleUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(nativeSignInState: NativeSignInState) : Boolean{
        return authRepository.signUpWithGoogle(nativeSignInState)
    }
}