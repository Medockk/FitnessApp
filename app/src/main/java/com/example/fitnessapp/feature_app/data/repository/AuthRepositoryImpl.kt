package com.example.fitnessapp.feature_app.data.repository

import android.util.Log
import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.repository.AuthRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlin.random.Random

class AuthRepositoryImpl : AuthRepository {

    override suspend fun signIn(mail: String, pass: String) {
        client.auth.signInWith(Email) {
            email = mail
            password = pass
        }
    }

    override suspend fun signInWithGoogle(): Boolean {

        Log.e("sign in google", "use case")
        val userID = client.auth.currentUserOrNull()?.id ?: ""

        client.postgrest["Users"].select {
            filter { eq("userID", userID) }
        }.decodeSingle<UserData>()

        return true
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

        val heartRate = getRandomHeartRate()
        client.postgrest["HeartRate"].insert(
            mapOf(
                "userID" to userID,
                "heartRateList" to heartRate
            )
        )
    }

    override suspend fun signUpWithGoogle(): Boolean {

        try {
            val userID = client.auth.currentUserOrNull()?.id ?: ""
            client.postgrest["Users"].select {
                filter { eq("userID", userID) }
            }
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

    private fun getRandomHeartRate() : String{

        var heartRate = ""

        for (i in 0..11){

            heartRate += Random.nextInt(1, 6).toString() + " "
        }

        return heartRate
    }
}