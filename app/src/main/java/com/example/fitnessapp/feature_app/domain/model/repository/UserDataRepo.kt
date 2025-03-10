package com.example.fitnessapp.feature_app.domain.model.repository

interface UserDataRepo {

    val id: Int
    val userID: String
    val fio: String
    val phone: String
    val gender: String
    val birthdayData: String
    val weight: String
    val height: String
    val notification: Boolean
    val image: String
    val purpose: String
}