package com.example.fitnessapp.feature_app.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessapp.feature_app.domain.model.UserDataEntity
import com.example.fitnessapp.feature_app.domain.repository.UserDao

@Database([UserDataEntity::class], version = 1)
abstract class UserDataDao : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        fun createDB(context: Context) : UserDataDao{
            return Room.databaseBuilder(context, UserDataDao::class.java, "userData.db").build()
        }
    }
}