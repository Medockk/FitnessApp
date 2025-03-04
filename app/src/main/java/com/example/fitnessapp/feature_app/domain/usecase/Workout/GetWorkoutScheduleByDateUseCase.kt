package com.example.fitnessapp.feature_app.domain.usecase.Workout

import com.example.fitnessapp.feature_app.domain.model.WorkoutSchedule
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository

class GetWorkoutScheduleByDateUseCase(
    private val workoutRepository: WorkoutRepository
) {

    suspend operator fun invoke(year: Int, month: Int, day: Int) : List<WorkoutSchedule>{
        return workoutRepository.getWorkoutScheduleByDate(year, month, day)
    }
}