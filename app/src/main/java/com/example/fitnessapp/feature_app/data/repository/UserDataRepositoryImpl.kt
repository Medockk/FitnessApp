package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest

class UserDataRepositoryImpl : UserDataRepository {

    override suspend fun getUserData(): UserData {

        val userID = client.auth.currentUserOrNull()?.id?:""
        return client.postgrest["Users"].select {
            filter {
                eq("userID", userID)
            }
        }.decodeSingle<UserData>()
    }

    override suspend fun updateUserData(userData: UserData) {

        val userID = client.auth.currentUserOrNull()?.id?:""

        client.postgrest["Users"].update({
            set("fio", userData.fio)
            set("phone", userData.phone)
            set("gender", userData.gender)
            set("birthdayData", userData.birthdayData)
            set("weight", userData.weight)
            set("height", userData.height)
        }) {
            filter {
                eq("userID", userID)
            }
        }
    }
}