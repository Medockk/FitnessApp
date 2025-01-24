package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.repository.AuthRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest

class AuthRepositoryImpl : AuthRepository {

    override suspend fun signIn(mail: String, pass: String) {
        client.auth.signInWith(Email){
            email = mail
            password = pass
        }
    }

    override suspend fun signInWithGoogle() {
        client.auth.signInWith(Google)
    }

    override suspend fun signUp(mail: String, pass: String, userData: UserData) {
        client.auth.signUpWith(Email){
            email = mail
            password = pass
        }

        val userID = client.auth.currentUserOrNull()?.id?:""
        client.postgrest["Users"].insert(
            mapOf(
                "userID" to userID,
                "name" to userData.name,
                "surname" to userData.surname,
                "patronymic" to userData.patronymic,
                "phone" to userData.phone
            )
        )
    }

    override suspend fun signUpWithGoogle() {
        client.auth.signUpWith(Google)
    }
}