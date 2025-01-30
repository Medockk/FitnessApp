package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutSprint
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest

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

    private fun getUserID() : String{

        val userID = client.auth.currentUserOrNull()?.id?:""
        return userID
    }
}