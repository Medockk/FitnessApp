package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.CategoryData
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDataImpl(
    override val id: Int = 0,
    override val title: String = "",
    override val image: String = "",
) : CategoryData