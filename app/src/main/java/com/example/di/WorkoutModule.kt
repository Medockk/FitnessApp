package com.example.di

import com.example.fitnessapp.feature_app.data.repository.WorkoutRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository
import com.example.fitnessapp.feature_app.domain.usecase.Workout.AddLastActivityUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.ChangeUserWorkoutStateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetAllWorkoutUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetUserWorkoutUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetWorkoutDetailsUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetWorkoutScheduleByDateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetWorkoutScheduleUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetWorkoutSprintUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.SetWorkoutScheduleUseCase
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

    factory<GetWorkoutScheduleUseCase> {
        GetWorkoutScheduleUseCase(get())
    }

    factory<SetWorkoutScheduleUseCase> {
        SetWorkoutScheduleUseCase(get())
    }
    factory<AddLastActivityUseCase> {
        AddLastActivityUseCase(get())
    }
    factory<GetWorkoutScheduleByDateUseCase> {
        GetWorkoutScheduleByDateUseCase(get())
    }
    factory<GetWorkoutDetailsUseCase> {
        GetWorkoutDetailsUseCase(get())
    }
}