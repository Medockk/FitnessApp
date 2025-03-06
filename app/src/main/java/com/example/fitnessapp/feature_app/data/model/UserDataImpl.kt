package com.example.fitnessapp.feature_app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnessapp.feature_app.domain.model.UserDataInter
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class UserDataImpl(
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    @ColumnInfo(defaultValue = "")
    override val fio: String,
    @ColumnInfo(defaultValue = "")
    override val userID: String,
    @ColumnInfo(defaultValue = "")
    override val phone: String,
    @ColumnInfo(defaultValue = "")
    override val gender: String,
    @ColumnInfo(defaultValue = "")
    override val birthdayData: String,
    @ColumnInfo(defaultValue = "")
    override val weight: String,
    @ColumnInfo(defaultValue = "")
    override val height: String,
    @ColumnInfo(defaultValue = "")
    override val notification: Boolean,
    @ColumnInfo(defaultValue = "")
    override val image: String,
) : UserDataInter{

    companion object{
        const val male = "Мужской"
        const val female = "Женский"
    }
}
