package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutSchedule
import com.example.fitnessapp.feature_app.domain.model.WorkoutSprint
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import java.time.LocalDate

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

    override suspend fun setWorkoutSchedule(workoutSchedule: WorkoutSchedule) {

        val userID = getUserID()

        client.postgrest["WorkoutSchedule"].insert(mapOf(
            "userID" to userID,
            "title" to workoutSchedule.title
        ))
    }

    private suspend fun getUserID() : String{
        client.auth.awaitInitialization()
        return client.auth.currentUserOrNull()?.id ?: ""
    }
}