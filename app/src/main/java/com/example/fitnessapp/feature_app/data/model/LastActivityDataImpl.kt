package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.LastActivityData
import kotlinx.serialization.Serializable

@Serializable
data class LastActivityDataImpl(
    override val id: Int,
    override val userID: String,
    override val title: String,
    override val date: String,
    override val image: String
) : LastActivityData
