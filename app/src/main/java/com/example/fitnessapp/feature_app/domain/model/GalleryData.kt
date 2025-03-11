package com.example.fitnessapp.feature_app.domain.model

interface GalleryData {

    val id: Int
    val userID: String
    val date: String
    val photo: String
    val category: String

    companion object{
        const val FRONT_SIDE = "Передняя сторона"
        const val REVERS_SIDE = "Обратной стороной"
        const val LEFT_SIDE = "Левая сторона"
        const val RIGHT_SIDE = "Правая сторона"

        var firstMonth = ""
        var secondMonth = ""
    }
}