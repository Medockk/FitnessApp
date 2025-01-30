package com.example.di

import com.example.fitnessapp.feature_app.data.repository.WorkoutRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository
import com.example.fitnessapp.feature_app.domain.usecase.Workout.ChangeUserWorkoutStateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetAllWorkoutUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetUserWorkoutUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetWorkoutSprintUseCase
import org.koin.dsl.module

val moduleWorkout = module {

    single<WorkoutRepository> {
        WorkoutRepositoryImpl()
    }

    factory<GetUserWorkoutUseCase> {
        GetUserWorkoutUseCase(get())
    }

    factory<ChangeUserWorkoutStateUseCase> {
        ChangeUserWorkoutStateUseCase(get())
    }

    factory<GetAllWorkoutUseCase> {
        GetAllWorkoutUseCase(get())
    }

    factory<GetWorkoutSprintUseCase> {
        GetWorkoutSprintUseCase(get())
    }
}