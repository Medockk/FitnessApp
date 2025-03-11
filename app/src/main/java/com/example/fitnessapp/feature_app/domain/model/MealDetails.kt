package com.example.fitnessapp.feature_app.domain.model

interface MealDetails {
    val id: Int
    val stepSize: String
    val stepDescription: String
    val ingredientsAndTheyCount: String
}