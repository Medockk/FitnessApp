package com.example.fitnessapp.feature_app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.model.repository.UserDataRepo
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class UserDataRepoImpl(
    @PrimaryKey(true)
    override val id: Int = 0,
    @ColumnInfo(defaultValue = "")override val userID: String,
    @ColumnInfo(defaultValue = "")override val fio: String,
    @ColumnInfo(defaultValue = "")override val phone: String,
    @ColumnInfo(defaultValue = "")override val gender: String,
    @ColumnInfo(defaultValue = "")override val birthdayData: String,
    @ColumnInfo(defaultValue = "")override val weight: String,
    @ColumnInfo(defaultValue = "")override val height: String,
    @ColumnInfo(defaultValue = "")override val notification: Boolean,
    @ColumnInfo(defaultValue = "")override val image: String,
    @ColumnInfo(defaultValue = "")override val purpose: String
) : UserDataRepo{
    fun toUserData() : UserData{
        return UserData(
            id = id,
            userID = userID,
            fio = fio,
            phone = phone,
            gender = gender,
            birthdayData = birthdayData,
            weight = weight,
            height = height,
            notification = notification,
            image = image,
            purpose = purpose
        )
    }
}