package com.example.fitnessapp.feature_app.domain.usecase.Workout

import com.example.fitnessapp.feature_app.domain.model.WorkoutSchedule
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository

class SetWorkoutScheduleUseCase(
    private val workoutRepository: WorkoutRepository
) {

    suspend operator fun invoke(workoutSchedule: WorkoutSchedule){
        workoutRepository.setWorkoutSchedule(workoutSchedule)
    }
}