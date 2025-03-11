package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.model.AlarmClockTrackerImpl
import com.example.fitnessapp.feature_app.data.model.SleepTrackerImpl
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
        }.decodeList<SleepTrackerImpl>()
    }

    override suspend fun getSleepDataByDate(year: Int, month: Int, day: Int): List<SleepTracker> {
        val userID = getUserID()

        return client.postgrest["SleepTracker"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", "${year}-${month}-$day")
                }
            }
        }.decodeList<SleepTrackerImpl>()
    }

    override suspend fun getAlarmClockData(): List<AlarmClockTracker> {

        val userID = getUserID()
        val currentData = LocalDate.now().toString()

        return client.postgrest["AlarmClockTracker"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", currentData)
                }
            }
        }.decodeList<AlarmClockTrackerImpl>()
    }

    override suspend fun getAlarmClockDataByDate(
        year: Int,
        month: Int,
        day: Int
    ): List<AlarmClockTracker> {
        val userID = getUserID()

        return client.postgrest["AlarmClockTracker"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", "${year}-${month}-$day")
                }
            }
        }.decodeList<AlarmClockTrackerImpl>()
    }

    override suspend fun changeSleepEnabled(sleepTrackerId: Int, sleepTrackerEnabled: Boolean) {

        val userID = getUserID()

        client.postgrest["SleepTracker"].update({
            set("enabled", sleepTrackerEnabled)
        }) {
            filter {
                and {
                    eq("userID", userID)
                    eq("id", sleepTrackerId)
                }
            }
        }
    }

    override suspend fun changeAlarmEnabled(
        alarmClockTrackerEnabled: Boolean,
        alarmClockTrackerId: Int
    ) {

        val userID = getUserID()

        client.postgrest["AlarmClockTracker"].update({
            set("enabled", alarmClockTrackerEnabled)
        }) {
            filter {
                and {
                    eq("userID", userID)
                    eq("id", alarmClockTrackerId)
                }
            }
        }
    }

    override suspend fun addAlarm(sleepTrackerTime: String, alarmClockTrackerTime: String) {

        val userID = getUserID()

        client.postgrest["SleepTracker"].insert(
            mapOf(
                "userID" to userID,
                "time" to sleepTrackerTime
            )
        )

        client.postgrest["AlarmClockTracker"].insert(
            mapOf(
                "userID" to userID,
                "time" to alarmClockTrackerTime
            )
        )
    }

    private suspend fun getUserID(): String {
        client.auth.awaitInitialization()
        return client.auth.currentUserOrNull()?.id ?: ""
    }
}