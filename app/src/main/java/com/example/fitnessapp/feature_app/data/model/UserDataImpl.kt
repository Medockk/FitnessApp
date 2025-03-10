package com.example.fitnessapp.feature_app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnessapp.feature_app.domain.model.UserData
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class UserDataImpl(
    @PrimaryKey(true)
    override val id: Int = 0,
    @ColumnInfo(defaultValue = "") override val userID: String,
    @ColumnInfo(defaultValue = "") override val fio: String,
    @ColumnInfo(defaultValue = "") override val phone: String,
    @ColumnInfo(defaultValue = "") override val gender: String,
    @ColumnInfo(defaultValue = "") override val birthdayData: String,
    @ColumnInfo(defaultValue = "") override val weight: String,
    @ColumnInfo(defaultValue = "") override val height: String,
    @ColumnInfo(defaultValue = "") override val notification: Boolean,
    @ColumnInfo(defaultValue = "") override val image: String,
    @ColumnInfo(defaultValue = "") override val purpose: String
) : UserData