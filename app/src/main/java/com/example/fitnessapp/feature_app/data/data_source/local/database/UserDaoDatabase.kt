package com.example.fitnessapp.feature_app.data.data_source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessapp.feature_app.data.data_source.local.UserDataDao
import com.example.fitnessapp.feature_app.data.model.UserDataImpl

@Database(entities = [UserDataImpl::class], version = 1)
abstract class UserDaoDatabase: RoomDatabase() {

    abstract val userDataDao: UserDataDao

    companion object{
        fun createDatabase(context: Context) : UserDaoDatabase{
            return Room.databaseBuilder(context, UserDaoDatabase::class.java, "user.db").build()
        }
    }
}