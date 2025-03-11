package com.example.fitnessapp.feature_app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnessapp.feature_app.domain.model.UserMealSchedule
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class UserMealScheduleImpl(
    @PrimaryKey(true) override val id: Int,
    @ColumnInfo(defaultValue = "") override val userID: String,
    @ColumnInfo(defaultValue = "") override val category: String,
    @ColumnInfo(defaultValue = "") override val mealID: Int,
    @ColumnInfo(defaultValue = "") override val time: String,
    @ColumnInfo(defaultValue = "") override val date: String
) : UserMealSchedule
