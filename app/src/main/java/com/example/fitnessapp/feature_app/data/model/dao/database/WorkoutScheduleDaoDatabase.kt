package com.example.fitnessapp.feature_app.data.model.dao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessapp.feature_app.data.model.WorkoutScheduleImpl
import com.example.fitnessapp.feature_app.data.model.dao.WorkoutScheduleDao

@Database(entities = [WorkoutScheduleImpl::class], version = 1)
abstract class WorkoutScheduleDaoDatabase : RoomDatabase(){

    abstract val workoutScheduleDao: WorkoutScheduleDao

    companion object{
        fun createDatabase(context: Context) : WorkoutScheduleDaoDatabase {
            return Room.databaseBuilder(context, WorkoutScheduleDaoDatabase::class.java, "workoutSchedule.db").build()
        }
    }
}