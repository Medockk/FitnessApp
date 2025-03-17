package com.example.fitnessapp.feature_app.domain.usecase.Workout

import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.WorkoutSchedule
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class GetWorkoutScheduleUseCase(
    private val workoutRepository: WorkoutRepository
) {

    suspend operator fun invoke() : Flow<NetworkResult<List<WorkoutSchedule>>> {
        return workoutRepository.getWorkoutSchedule()
    }
}