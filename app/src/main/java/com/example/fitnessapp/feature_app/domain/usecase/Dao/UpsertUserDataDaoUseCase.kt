package com.example.fitnessapp.feature_app.domain.usecase.Dao

import com.example.fitnessapp.feature_app.data.dao.UserDataDaoImpl
import com.example.fitnessapp.feature_app.domain.model.UserDataEntity

class UpsertUserDataDaoUseCase(
    private val userDataDaoImpl: UserDataDaoImpl
) {

    suspend operator fun invoke(userDataEntity: UserDataEntity){
        userDataDaoImpl.upsertUserData(userDataEntity)
    }
}