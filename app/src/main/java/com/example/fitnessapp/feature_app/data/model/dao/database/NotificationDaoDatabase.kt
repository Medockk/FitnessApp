package com.example.fitnessapp.feature_app.data.model.dao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessapp.feature_app.data.model.NotificationDataImpl
import com.example.fitnessapp.feature_app.data.model.dao.NotificationDataDao

@Database(entities = [NotificationDataImpl::class], version = 1)
abstract class NotificationDaoDatabase : RoomDatabase(){

    abstract val notificationDataDao: NotificationDataDao

    companion object{
        fun createDatabase(context: Context) : NotificationDaoDatabase{
            return Room.databaseBuilder(context, NotificationDaoDatabase::class.java, "notifications.db").build()
        }
    }
}