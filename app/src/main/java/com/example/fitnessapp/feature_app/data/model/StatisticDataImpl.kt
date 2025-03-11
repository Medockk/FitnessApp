package com.example.fitnessapp.feature_app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnessapp.feature_app.domain.model.StatisticData
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class StatisticDataImpl(
    @ColumnInfo(defaultValue = "") override val date: String,
    @ColumnInfo(defaultValue = "") override val userID: String,
    @ColumnInfo(defaultValue = "") override val type: String,
    @ColumnInfo(defaultValue = "") override val value: String,
    @PrimaryKey(true) override val id: Int? = null
) : StatisticData
