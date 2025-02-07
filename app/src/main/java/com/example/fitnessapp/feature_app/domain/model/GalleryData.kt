package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GalleryData(
    val id: Int,
    val userID: String,
    val date: String,
    val photo: String,
    val category: String
){
    companion object{
        const val frontSide = "Передняя сторона"
        const val reverseSide = "Обратной стороной"
        const val leftSide = "Левая сторона"
        const val rightSide = "Правая сторона"

        var firstMonth = ""
        var secondMonth = ""
    }
}
