package com.example.fitnessapp.feature_app.domain.model

interface UserRetrofitData {
    val id: Int
    val name: String?
    val status: String?
    val photoUrls: List<String>
    val tags: List<Tags>
    val category: Category?
}

interface Tags{
    val id: Int
    val name: String
}

interface Category{
    val id: Int
    val name: String
}