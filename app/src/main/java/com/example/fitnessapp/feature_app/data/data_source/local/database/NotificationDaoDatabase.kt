package com.example.fitnessapp.feature_app.data.data_source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessapp.feature_app.data.data_source.local.NotificationDataDao
import com.example.fitnessapp.feature_app.data.model.NotificationDataImpl

@Database(entities = [NotificationDataImpl::class], version = 1)
abstract class NotificationDaoDatabase : RoomDatabase(){

    abstract val notificationDataDao: NotificationDataDao

    companion object{
        fun createDatabase(context: Context) : NotificationDaoDatabase{
            return Room.databaseBuilder(context, NotificationDaoDatabase::class.java, "notifications.db").build()
        }
    }
}