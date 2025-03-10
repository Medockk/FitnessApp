package com.example.fitnessapp.feature_app.domain.usecase.Workout

import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository

class AddLastActivityUseCase(
    private val workoutRepository: WorkoutRepository
) {

    suspend operator fun invoke(title: String, image: String){
        workoutRepository.addLastActivity(title, image)
    }
}