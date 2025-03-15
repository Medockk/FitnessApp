package com.example.di

import android.content.Context
import com.example.fitnessapp.feature_app.data.data_source.local.WorkoutScheduleDao
import com.example.fitnessapp.feature_app.data.data_source.local.database.WorkoutScheduleDaoDatabase
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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkoutModule {

    @Provides
    @Singleton
    fun createWorkoutScheduleDatabase(@ApplicationContext context: Context): WorkoutScheduleDaoDatabase {
        return WorkoutScheduleDaoDatabase.createDatabase(context)
    }

    @Provides
    @Singleton
    fun createWorkoutScheduleDao(workoutScheduleDaoDatabase: WorkoutScheduleDaoDatabase): WorkoutScheduleDao {
        return workoutScheduleDaoDatabase.workoutScheduleDao
    }

    @Provides
    @Singleton
    fun getWorkoutRepository(workoutScheduleDao: WorkoutScheduleDao): WorkoutRepository {
        return WorkoutRepositoryImpl(workoutScheduleDao)
    }

    //FACTORIES
    @Provides
    @Singleton
    fun addLastActivityUseCase(workoutRepository: WorkoutRepository): AddLastActivityUseCase {
        return AddLastActivityUseCase(workoutRepository)
    }

    @Provides
    @Singleton
    fun changeUserWorkoutStateUseCase(workoutRepository: WorkoutRepository): ChangeUserWorkoutStateUseCase {
        return ChangeUserWorkoutStateUseCase(workoutRepository)
    }

    @Provides
    @Singleton
    fun getAllWorkoutUseCase(workoutRepository: WorkoutRepository): GetAllWorkoutUseCase {
        return GetAllWorkoutUseCase(workoutRepository)
    }

    @Provides
    @Singleton
    fun getUserWorkoutUseCase(workoutRepository: WorkoutRepository): GetUserWorkoutUseCase {
        return GetUserWorkoutUseCase(workoutRepository)
    }

    @Provides
    @Singleton
    fun getWorkoutDetailsUseCase(workoutRepository: WorkoutRepository): GetWorkoutDetailsUseCase {
        return GetWorkoutDetailsUseCase(workoutRepository)
    }

    @Provides
    @Singleton
    fun getWorkoutScheduleUseCase(workoutRepository: WorkoutRepository): GetWorkoutScheduleUseCase {
        return GetWorkoutScheduleUseCase(workoutRepository)
    }

    @Provides
    @Singleton
    fun getWorkoutScheduleByDateUseCase(workoutRepository: WorkoutRepository): GetWorkoutScheduleByDateUseCase {
        return GetWorkoutScheduleByDateUseCase(workoutRepository)
    }

    @Provides
    @Singleton
    fun getWorkoutSprintUseCase(workoutRepository: WorkoutRepository): GetWorkoutSprintUseCase {
        return GetWorkoutSprintUseCase(workoutRepository)
    }

    @Provides
    @Singleton
    fun setWorkoutScheduleUseCase(workoutRepository: WorkoutRepository): SetWorkoutScheduleUseCase {
        return SetWorkoutScheduleUseCase(workoutRepository)
    }

}