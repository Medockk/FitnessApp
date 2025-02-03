package com.example.fitnessapp

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.repository.AuthRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email

class AuthTestRepoImpl : AuthRepository {
    override suspend fun signIn(mail: String, pass: String) {

    }

    override suspend fun signInWithGoogle(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(mail: String, pass: String, userData: UserData) {
        client.auth.signUpWith(Email){
            email = mail
            password = pass
        }
    }

    override suspend fun signUpWithGoogle(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun createProfile(
        gender: String,
        birthdayData: String,
        weight: String,
        height: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun selectPurpose(purpose: String) {
        TODO("Not yet implemented")
    }
}