package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData
import kotlinx.serialization.Serializable

@Serializable
data class UserWorkoutDataDto(
    val id: Int,
    val userID: String,
    val image: String,
    val title: String,
    val time: String,
    var isTurnOn: Boolean
){
    fun toUserWorkoutData() : UserWorkoutData{
        return UserWorkoutData(id, userID, image, title, time, isTurnOn)
    }
}
