package com.example.fitnessapp.feature_app.presentation.Profile

import com.example.fitnessapp.feature_app.domain.model.Purpose
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.model.UserDataEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ProfileState(

    val userData: UserData = UserData(0,"","","","","","",""),
    val purpose: Purpose = Purpose(0,"",""),
    val image: String = "",
    val userDataDao: Flow<List<UserDataEntity>> = emptyFlow(),

    val isNotificationTurnOn: Boolean = false,

    val exception: String = "",

    val showIndicator: Boolean = false,
    val isInit: Boolean = true,
)
