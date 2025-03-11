package com.example.fitnessapp.feature_app.data.data_source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessapp.feature_app.data.data_source.local.GalleryDataDao
import com.example.fitnessapp.feature_app.data.model.GalleryDataImpl

@Database(entities = [GalleryDataImpl::class], version = 1)
abstract class GalleryDaoDatabase : RoomDatabase() {

    abstract val galleryDao: GalleryDataDao

    companion object{
        fun createDatabase(context: Context) : GalleryDaoDatabase{
            return Room.databaseBuilder(context, GalleryDaoDatabase::class.java, "gallery.db").build()
        }
    }
}