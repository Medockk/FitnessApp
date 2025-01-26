package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.repository.AuthRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.compose.auth.composable.NativeSignInState
import io.github.jan.supabase.postgrest.postgrest

class AuthRepositoryImpl : AuthRepository {

    override suspend fun signIn(mail: String, pass: String) {
        client.auth.signInWith(Email) {
            email = mail
            password = pass
        }
    }

    override suspend fun signInWithGoogle() {

        val userID = client.auth.currentUserOrNull()?.id ?: ""
        val email = client.auth.currentUserOrNull()?.email ?: ""

        try {
            client.postgrest["Users"].insert(
                mapOf(
                    "userID" to userID,
                    "fio" to email
                )
            )
        } catch (_: Exception) {

        }

    }

    override suspend fun signUp(mail: String, pass: String, userData: UserData) {
        client.auth.signUpWith(Email) {
            email = mail
            password = pass
        }

        val userID = client.auth.currentUserOrNull()?.id ?: ""
        client.postgrest["Users"].insert(
            mapOf(
                "userID" to userID,
                "fio" to userData.fio,
                "phone" to userData.phone
            )
        )
    }

    override suspend fun signUpWithGoogle(nativeSignInState: NativeSignInState) : Boolean {

        nativeSignInState.startFlow()

        val userID = client.auth.currentUserOrNull()?.id ?: ""
        val email = client.auth.currentUserOrNull()?.email ?: ""

        try {
            client.postgrest["Users"].insert(
                mapOf(
                    "userID" to userID,
                    "fio" to email
                )
            )
            return true
        } catch (_: Exception) {
            return false
        }
    }

    override suspend fun createProfile(
        gender: String,
        birthdayData: String,
        weight: String,
        height: String
    ) {
        val userID = client.auth.currentUserOrNull()?.id ?: ""
        client.postgrest["Users"].update(
            mapOf(
                "gender" to gender,
                "birthdayData" to birthdayData,
                "weight" to weight,
                "height" to height
            )
        ) {
            filter {
                eq("userID", userID)
            }
        }
    }

    override suspend fun selectPurpose(purpose: String) {

        val userID = client.auth.currentUserOrNull()?.id ?: ""
        client.postgrest["Purpose"].insert(
            mapOf(
                "userID" to userID,
                "purpose" to purpose
            )
        )
    }
}