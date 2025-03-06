package com.example.fitnessapp.feature_app.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(defaultValue = "")
    val email: String = "",
    @ColumnInfo(defaultValue = "")
    val fio: String = "",
    @ColumnInfo(defaultValue = "")
    val phone: String = "",
    @ColumnInfo(defaultValue = "")
    val gender: String = "",
    @ColumnInfo(defaultValue = "")
    val birthdayData: String = "",
    @ColumnInfo(defaultValue = "")
    val weight: String = "",
    @ColumnInfo(defaultValue = "")
    val height: String = "",
    @ColumnInfo(defaultValue = "")
    val purpose: String = "",
    @ColumnInfo(defaultValue = "")
    val image: String = "",
){
    companion object{
        var email: String = ""
    }
}