package com.example.fitnessapp

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.repository.AuthRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email

class AuthTestRepoImpl : AuthRepository {
    override suspend fun signIn(mail: String, pass: String) {

    }

    override suspend fun signInWithGoogle(): Boolean {
        return false
    }

    override suspend fun signUp(mail: String, pass: String, fio: String, phone: String) {
        client.auth.signUpWith(Email){
            email = mail
            password = pass
        }
    }

    override suspend fun signUpWithGoogle(): Boolean {
        return false
    }

    override suspend fun createProfile(
        gender: String,
        birthdayData: String,
        weight: String,
        height: String
    ) {

    }

    override suspend fun selectPurpose(purpose: String) {

    }
}