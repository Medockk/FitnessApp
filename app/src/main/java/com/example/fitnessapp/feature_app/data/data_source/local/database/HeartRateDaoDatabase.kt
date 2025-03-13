package com.example.fitnessapp.feature_app.data.data_source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessapp.feature_app.data.data_source.local.HeartRateDataDao
import com.example.fitnessapp.feature_app.data.model.HeartRateImpl

@Database(entities = [HeartRateImpl::class], version = 1)
abstract class HeartRateDaoDatabase : RoomDatabase() {

    abstract val heartRateDataDao: HeartRateDataDao

    companion object{
        fun createDatabase(context: Context) : HeartRateDaoDatabase{
            return Room.databaseBuilder(context, HeartRateDaoDatabase::class.java, "heartRate.db").build()
        }
    }
}