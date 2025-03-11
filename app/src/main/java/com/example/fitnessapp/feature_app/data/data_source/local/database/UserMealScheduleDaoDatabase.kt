package com.example.fitnessapp.feature_app.data.data_source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessapp.feature_app.data.data_source.local.UserMealScheduleDao
import com.example.fitnessapp.feature_app.data.model.UserMealScheduleImpl

@Database(entities = [UserMealScheduleImpl::class], version = 1)
abstract class UserMealScheduleDaoDatabase : RoomDatabase(){

    abstract val userMealScheduleDao: UserMealScheduleDao

    companion object{
        fun createDatabase(context: Context) : UserMealScheduleDaoDatabase{
            return Room.databaseBuilder(context, UserMealScheduleDaoDatabase::class.java, "userMealSchedule.db").build()
        }
    }
}