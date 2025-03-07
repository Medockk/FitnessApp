package com.example.fitnessapp.feature_app.domain.model

interface UserDataInter {

    val id: Int?
    val fio: String
    val userID: String
    val phone: String
    val gender: String
    val birthdayData: String
    val weight: String
    val height: String
    val notification: Boolean
    val image: String
    val purpose: String
}