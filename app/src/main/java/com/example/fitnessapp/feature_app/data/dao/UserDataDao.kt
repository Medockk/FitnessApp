package com.example.fitnessapp.feature_app.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessapp.feature_app.data.model.UserDataImpl

@Database([UserDataImpl ::class], version = 1, exportSchema = false)
abstract class UserDataDao : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        fun createDataBase(context: Context): UserDataDao {
            return Room.databaseBuilder(context, UserDataDao::class.java, "userData.db").build()
        }
    }
}