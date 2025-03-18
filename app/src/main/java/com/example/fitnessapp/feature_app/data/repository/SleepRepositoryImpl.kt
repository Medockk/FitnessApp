package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.data_source.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.data.model.AlarmClockTrackerModelDto
import com.example.fitnessapp.feature_app.data.model.SleepTrackerModelDto
import com.example.fitnessapp.feature_app.domain.model.AlarmClockTrackerModel
import com.example.fitnessapp.feature_app.domain.model.SleepTrackerModel
import com.example.fitnessapp.feature_app.domain.repository.SleepRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import java.time.LocalDate

/**
 * Класс для работы со сном и режимами сна
 * @author Андреев Арсений, 18,02,2025; 12:07
 */
class SleepRepositoryImpl : SleepRepository {

    override suspend fun getSleepData(): List<SleepTrackerModel> {

        val userID = getUserID()
        val currentDate = LocalDate.now().toString()

        val data = ArrayList<SleepTrackerModel>()
        client.postgrest["SleepTracker"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", currentDate)
                }
            }
        }.decodeList<SleepTrackerModelDto>().forEach {
            data.add(it.toSleepTracker())
        }
        return data
    }

    override suspend fun getSleepDataByDate(year: Int, month: Int, day: Int): List<SleepTrackerModel> {
        val userID = getUserID()

        val data = ArrayList<SleepTrackerModel>()
        client.postgrest["SleepTracker"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", "${year}-${month}-$day")
                }
            }
        }.decodeList<SleepTrackerModelDto>().forEach {
            data.add(it.toSleepTracker())
        }
        return data
    }

    override suspend fun getAlarmClockData(): List<AlarmClockTrackerModel> {

        val userID = getUserID()
        val currentData = LocalDate.now().toString()

        val data = ArrayList<AlarmClockTrackerModel>()
        client.postgrest["AlarmClockTracker"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", currentData)
                }
            }
        }.decodeList<AlarmClockTrackerModelDto>().forEach {
            data.add(it.toAlarmClockTracker())
        }
        return data
    }

    override suspend fun getAlarmClockDataByDate(
        year: Int,
        month: Int,
        day: Int
    ): List<AlarmClockTrackerModel> {
        val userID = getUserID()

        val data = ArrayList<AlarmClockTrackerModel>()
        client.postgrest["AlarmClockTracker"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", "${year}-${month}-$day")
                }
            }
        }.decodeList<AlarmClockTrackerModelDto>().forEach {
            data.add(it.toAlarmClockTracker())
        }

        return data
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
        alarmClockTrackerId: Int,
        alarmClockTrackerEnabled: Boolean
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