package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.UserRetrofitData

data class UserRetrofitDataImpl(
    override val petId: Int,
    override val name: String,
    override val status: String
) : UserRetrofitData
