package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.SleepTracker
import com.example.fitnessapp.feature_app.domain.repository.SleepRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest

class SleepRepositoryImpl : SleepRepository {

    override suspend fun getSleepData(): List<SleepTracker> {

        val userID = getUserID()

        return client.postgrest["SleepTracker"].select {
            filter { eq("userID", userID) }
        }.decodeList<SleepTracker>()
    }

    override suspend fun changeEnabled(sleepTracker: SleepTracker) {

        val userID = getUserID()

        client.postgrest["SleepTracker"].update({
            set("enabled", sleepTracker.enabled)
        }){
            filter {
                and {
                    eq("userID", userID)
                    eq("id", sleepTracker.id)
                }
            }
        }
    }

    override suspend fun addAlarm(sleepTracker: SleepTracker) {

        val userID = getUserID()
        client.postgrest["SleepTracker"].upsert(
            mapOf(
                "title" to "Сон",
            )
        ){
            onConflict = "userID"
            filter { eq("userID", userID) }
        }
        TODO("IDK how do that!")
    }

    private fun getUserID() : String = client.auth.currentUserOrNull()?.id?:""
}