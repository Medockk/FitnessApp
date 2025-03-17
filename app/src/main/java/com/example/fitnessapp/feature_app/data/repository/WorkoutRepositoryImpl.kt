package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.model.UserWorkoutDataImpl
import com.example.fitnessapp.feature_app.data.model.WorkoutDataImpl
import com.example.fitnessapp.feature_app.data.model.WorkoutDetailsImpl
import com.example.fitnessapp.feature_app.data.model.WorkoutScheduleImpl
import com.example.fitnessapp.feature_app.data.model.WorkoutSprintImpl
import com.example.fitnessapp.feature_app.data.data_source.local.WorkoutScheduleDao
import com.example.fitnessapp.feature_app.data.data_source.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutDetails
import com.example.fitnessapp.feature_app.domain.model.WorkoutSchedule
import com.example.fitnessapp.feature_app.domain.model.WorkoutSprint
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Класс для работы с расписанием и тренировками пользователя
 * @author Андреев Арсений, 18,02,2025; 12:07
 */
class WorkoutRepositoryImpl(
    private val workoutScheduleDao: WorkoutScheduleDao
) : WorkoutRepository {

    override suspend fun getUserWorkout(): List<UserWorkoutData> {

        val userID = getUserID()

        return client.postgrest["UserWorkoutData"].select {
            filter { eq("userID", userID) }
        }.decodeList<UserWorkoutDataImpl>()
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

        return client.postgrest["WorkoutData"].select().decodeList<WorkoutDataImpl>()
    }

    override suspend fun getWorkoutSprint(sprintNumber: Int): List<WorkoutSprint> {

        return client.postgrest["WorkoutSprint"].select {
            filter { eq("sprintNumber", sprintNumber) }
        }.decodeList<WorkoutSprintImpl>()
    }

    override suspend fun getWorkoutSchedule() = flow<NetworkResult<List<WorkoutSchedule>>> {

        emit(NetworkResult.Loading())
        val userID = getUserID()
        val date = LocalDate.now().toString()
        emit(NetworkResult.Success(workoutScheduleDao.getWorkoutSchedule(userID)))

        val data = client.postgrest["WorkoutSchedule"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", date)
                }
            }
        }.decodeList<WorkoutScheduleImpl>()
        emit(NetworkResult.Success(data))
        workoutScheduleDao.clearWorkoutSchedule()

        data.forEach {
            workoutScheduleDao.upsertWorkoutSchedule(it)
        }

    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }

    override suspend fun setWorkoutSchedule(
        title: String,
        hour: Int,
        minute: Int
    ) {

        val userID = getUserID()
        val time = LocalDateTime.now().withHour(hour).withMinute(minute)

        client.postgrest["WorkoutSchedule"].insert(
            mapOf(
                "userID" to userID,
                "title" to title,
                "time" to time.toString()
            )
        )
    }

    override suspend fun addLastActivity(title: String, image: String) {
        val userID = getUserID()

        client.postgrest["LastActivity"].insert(
            mapOf(
                "userID" to userID,
                "title" to title,
                "image" to image
            )
        )
    }

    override suspend fun getWorkoutScheduleByDate(
        year: Int,
        month: Int,
        day: Int
    ): List<WorkoutSchedule> {
        val userID = getUserID()

        val data = client.postgrest["WorkoutSchedule"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", "$year-$month-$day")
                }
            }
        }.decodeList<WorkoutScheduleImpl>()
        workoutScheduleDao.clearWorkoutSchedule()

        data.forEach {
            workoutScheduleDao.upsertWorkoutSchedule(it)
        }
        return workoutScheduleDao.getWorkoutSchedule(userID)
    }

    override suspend fun getWorkoutDetails(workoutSprintID: Int): List<WorkoutDetails> {

        return client.postgrest["WorkoutDetails"].select {
            filter { eq("workoutSprintID", workoutSprintID) }
        }.decodeList<WorkoutDetailsImpl>()
    }

    private suspend fun getUserID(): String {
        client.auth.awaitInitialization()
        return client.auth.currentUserOrNull()?.id ?: ""
    }
}