package com.example.fitnessapp.feature_app.domain.usecase.Workout

import com.example.fitnessapp.feature_app.domain.model.WorkoutSprint
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository

class GetWorkoutSprintUseCase(
    private val workoutRepository: WorkoutRepository
) {

    suspend operator fun invoke(sprintNumber: Int) : List<WorkoutSprint>{
        return workoutRepository.getWorkoutSprint(sprintNumber)
    }
}