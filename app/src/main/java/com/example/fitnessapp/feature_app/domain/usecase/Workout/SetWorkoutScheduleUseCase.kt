package com.example.fitnessapp.feature_app.domain.usecase.Workout

import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository

class SetWorkoutScheduleUseCase(
    private val workoutRepository: WorkoutRepository
) {

    suspend operator fun invoke(title: String, hour: Int, minute: Int){
        workoutRepository.setWorkoutSchedule(title, hour, minute)
    }
}