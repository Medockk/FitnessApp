package com.example.fitnessapp.feature_app.data.data_source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessapp.feature_app.data.data_source.local.LastActivityDataDao
import com.example.fitnessapp.feature_app.data.model.LastActivityDataImpl

@Database(entities = [LastActivityDataImpl::class], version = 1)
abstract class LastActivityDaoDatabase : RoomDatabase() {

    abstract val lastActivityDataDao: LastActivityDataDao

    companion object {
        fun createDatabase(context: Context): LastActivityDaoDatabase {
            return Room.databaseBuilder(
                context,
                LastActivityDaoDatabase::class.java,
                "lastActivity.db"
            ).build()
        }
    }
}