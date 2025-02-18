package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.model.SleepTracker
import com.example.fitnessapp.feature_app.domain.repository.SleepRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import java.time.LocalDate

/**
 * Класс для работы со сном и режимами сна
 * @author Андреев Арсений, 18,02,2025; 12:07
 */
class SleepRepositoryImpl : SleepRepository {

    override suspend fun getSleepData(): List<SleepTracker> {

        val userID = getUserID()
        val currentDate = LocalDate.now().toString().replace("-",":")

        return client.postgrest["SleepTracker"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", currentDate)
                }
            }
        }.decodeList<SleepTracker>()
    }

    override suspend fun getAlarmClockData(): List<AlarmClockTracker> {

        val userID = getUserID()
        val currentData = LocalDate.now().toString().replace("-",":")

        return client.postgrest["AlarmClockTracker"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", currentData)
                }
            }
        }.decodeList<AlarmClockTracker>()
    }

    override suspend fun changeSleepEnabled(sleepTracker: SleepTracker) {

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

    override suspend fun changeAlarmEnabled(alarmClockTracker: AlarmClockTracker) {

        val userID = getUserID()

        client.postgrest["AlarmClockTracker"].update({
            set("enabled", alarmClockTracker.enabled)
        }){
            filter {
                and {
                    eq("userID", userID)
                    eq("id", alarmClockTracker.id)
                }
            }
        }
    }

    override suspend fun addAlarm(sleepTracker: SleepTracker, alarmClockTracker: AlarmClockTracker) {

        val userID = getUserID()
        val date = LocalDate.now().toString().replace("-",":")

        client.postgrest["SleepTracker"].insert(
            mapOf(
                "userID" to userID,
                "date" to date,
                "time" to sleepTracker.time
            )
        )

        client.postgrest["AlarmClockTracker"].insert(
            mapOf(
                "userID" to userID,
                "date" to date,
                "time" to alarmClockTracker.time
            )
        )
    }

    private suspend fun getUserID() : String{
        client.auth.awaitInitialization()
        return client.auth.currentUserOrNull()?.id ?: ""
    }
}