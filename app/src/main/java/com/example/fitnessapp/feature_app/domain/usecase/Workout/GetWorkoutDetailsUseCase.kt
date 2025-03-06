package com.example.fitnessapp.feature_app.domain.usecase.Workout

import com.example.fitnessapp.feature_app.domain.model.WorkoutDetails
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository

class GetWorkoutDetailsUseCase(
    private val workoutRepository: WorkoutRepository
) {

    suspend operator fun invoke(workoutSprintID: Int) : List<WorkoutDetails>{
        return workoutRepository.getWorkoutDetails(workoutSprintID)
    }
}