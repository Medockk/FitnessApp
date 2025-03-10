package com.example.fitnessapp.feature_app.domain.model

data class UserData(
    val id: Int = 0,
    val userID: String,
    val fio: String,
    val phone: String,
    val gender: String = "",
    val birthdayData: String = "",
    val weight: String = "",
    val height: String = "",
    val notification: Boolean = false,
    val image: String = "",
    val purpose: String = "",
){
    companion object{
        const val MALE = "Мужской"
        const val FEMALE = "Женский"
    }
}
