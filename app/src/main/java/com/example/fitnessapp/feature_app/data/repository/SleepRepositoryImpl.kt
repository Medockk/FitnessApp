package com.example.fitnessapp.feature_app.data.repository

import android.util.Log
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
        val currentDate = LocalDate.now().toString()

        return client.postgrest["SleepTracker"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", currentDate)
                }
            }
        }.decodeList<SleepTracker>()
    }

    override suspend fun getSleepDataByDate(date: Int): List<SleepTracker> {
        val userID = getUserID()
        val currentData = LocalDate.now()

        return client.postgrest["SleepTracker"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", "${currentData.year}-${currentData.month.value}-$date")
                }
            }
        }.decodeList<SleepTracker>()
    }

    override suspend fun getAlarmClockData(): List<AlarmClockTracker> {

        val userID = getUserID()
        val currentData = LocalDate.now().toString()

        val t  = client.postgrest["AlarmClockTracker"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", currentData)
                }
            }
        }.decodeList<AlarmClockTracker>()
        Log.e("t", t.toString())
        return t
    }

    override suspend fun getAlarmClockDataByDate(date: Int): List<AlarmClockTracker> {
        val userID = getUserID()
        val currentDate = LocalDate.now()

        return client.postgrest["AlarmClockTracker"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", "${currentDate.year}-${currentDate.month.value}-$date")
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

        client.postgrest["SleepTracker"].insert(
            mapOf(
                "userID" to userID,
                "time" to sleepTracker.time
            )
        )

        client.postgrest["AlarmClockTracker"].insert(
            mapOf(
                "userID" to userID,
                "time" to alarmClockTracker.time
            )
        )
    }

    private suspend fun getUserID() : String{
        client.auth.awaitInitialization()
        return client.auth.currentUserOrNull()?.id ?: ""
    }
}