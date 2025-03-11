package com.example.fitnessapp.feature_app.data.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessapp.feature_app.data.model.GalleryDataImpl

@Dao
interface GalleryDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertGalleryData(galleryDataRepoImpl: GalleryDataImpl)

    @Query("SELECT * FROM GalleryDataImpl WHERE id = :id")
    fun getGalleryByID(id: String) : GalleryDataImpl

    @Delete
    fun deleteGallery(galleryDataRepoImpl: GalleryDataImpl)
}