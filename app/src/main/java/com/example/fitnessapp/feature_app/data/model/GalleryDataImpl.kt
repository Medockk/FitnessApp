package com.example.fitnessapp.feature_app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnessapp.feature_app.domain.model.GalleryData
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class GalleryDataImpl(
    @PrimaryKey(true)
    override val id: Int,
    @ColumnInfo(defaultValue = "") override val userID: String,
    @ColumnInfo(defaultValue = "") override val date: String,
    @ColumnInfo(defaultValue = "") override val photo: String,
    @ColumnInfo(defaultValue = "") override val category: String
) : GalleryData