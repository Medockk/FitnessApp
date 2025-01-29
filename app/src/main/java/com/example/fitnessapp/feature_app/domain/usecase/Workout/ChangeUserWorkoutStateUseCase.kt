package com.example.fitnessapp.feature_app.domain.usecase.Workout

import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository

class ChangeUserWorkoutStateUseCase(
    private val workoutRepository: WorkoutRepository
) {

    suspend operator fun invoke(userWorkoutData: UserWorkoutData){
        workoutRepository.changeUserWorkoutState(userWorkoutData)
    }
}