package com.example.fitnessapp.feature_app.data.model.dao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessapp.feature_app.data.model.StatisticDataImpl
import com.example.fitnessapp.feature_app.data.model.dao.StatisticDataDao

@Database(entities = [StatisticDataImpl::class], version = 1)
abstract class StatisticDataDaoDatabase : RoomDatabase() {

    abstract val statisticDataDao: StatisticDataDao

    companion object{
        fun createDatabase(context: Context) : StatisticDataDaoDatabase{
            return Room.databaseBuilder(context, StatisticDataDaoDatabase::class.java, "statisticData.db").build()
        }
    }
}