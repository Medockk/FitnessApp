package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.LastActivityData
import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutDetails
import com.example.fitnessapp.feature_app.domain.model.WorkoutSchedule
import com.example.fitnessapp.feature_app.domain.model.WorkoutSprint
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Класс для работы с расписанием и тренировками пользователя
 * @author Андреев Арсений, 18,02,2025; 12:07
 */
class WorkoutRepositoryImpl : WorkoutRepository {

    override suspend fun getUserWorkout(): List<UserWorkoutData> {

        val userID = getUserID()

        return client.postgrest["UserWorkoutData"].select {
            filter { eq("userID", userID) }
        }.decodeList<UserWorkoutData>()
    }

    override suspend fun changeUserWorkoutState(userWorkoutData: UserWorkoutData) {

        val userID = getUserID()

        client.postgrest["UserWorkoutData"].update({
            set("isTurnOn", userWorkoutData.isTurnOn)
        }) {
            filter {
                and {
                    eq("userID", userID)
                    eq("id", userWorkoutData.id)
                }
            }
        }
    }

    override suspend fun getAllWorkout(): List<WorkoutData> {

        return client.postgrest["WorkoutData"].select().decodeList<WorkoutData>()
    }

    override suspend fun getWorkoutSprint(sprintNumber: Int): List<WorkoutSprint> {

        return client.postgrest["WorkoutSprint"].select {
            filter { eq("sprintNumber", sprintNumber) }
        }.decodeList<WorkoutSprint>()
    }

    override suspend fun getWorkoutSchedule(): List<WorkoutSchedule> {

        val userID = getUserID()
        val date = LocalDate.now().toString()

        return client.postgrest["WorkoutSchedule"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", date)
                }
            }
        }.decodeList<WorkoutSchedule>()
    }

    override suspend fun setWorkoutSchedule(
        workoutSchedule: WorkoutSchedule,
        hour: Int,
        minute: Int
    ) {

        val userID = getUserID()
        val time = LocalDateTime.now().withHour(hour).withMinute(minute)

        client.postgrest["WorkoutSchedule"].insert(
            mapOf(
                "userID" to userID,
                "title" to workoutSchedule.title,
                "time" to time.toString()
            )
        )
    }

    override suspend fun addLastActivity(lastActivityData: LastActivityData) {
        val userID = getUserID()

        client.postgrest["LastActivity"].insert(
            mapOf(
                "userID" to userID,
                "title" to lastActivityData.title,
                "image" to lastActivityData.image
            )
        )
    }

    override suspend fun getWorkoutScheduleByDate(
        year: Int,
        month: Int,
        day: Int
    ): List<WorkoutSchedule> {
        val userID = getUserID()

        return client.postgrest["WorkoutSchedule"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", "$year-$month-$day")
                }
            }
        }.decodeList<WorkoutSchedule>()
    }

    override suspend fun getWorkoutDetails(workoutSprintID: Int): List<WorkoutDetails> {

        return client.postgrest["WorkoutDetails"].select {
            filter { eq("workoutSprintID", workoutSprintID) }
        }.decodeList<WorkoutDetails>()
    }

    private suspend fun getUserID(): String {
        client.auth.awaitInitialization()
        return client.auth.currentUserOrNull()?.id ?: ""
    }
}