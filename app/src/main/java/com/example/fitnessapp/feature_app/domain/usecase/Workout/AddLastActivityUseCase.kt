package com.example.fitnessapp.feature_app.domain.usecase.Workout

import com.example.fitnessapp.feature_app.domain.model.LastActivityData
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository

class AddLastActivityUseCase(
    private val workoutRepository: WorkoutRepository
) {

    suspend operator fun invoke(lastActivityData: LastActivityData){
        workoutRepository.addLastActivity(lastActivityData)
    }
}