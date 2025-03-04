package com.example.fitnessapp.feature_app.domain.usecase.Dao

import com.example.fitnessapp.feature_app.data.dao.UserDataDaoImpl
import com.example.fitnessapp.feature_app.domain.model.UserDataEntity

class DeleteUserDataDaoUseCase(
    private val userDataDaoImpl: UserDataDaoImpl
) {

    operator fun invoke(userDataEntity: UserDataEntity){
        userDataDaoImpl.deleteUserData(userDataEntity)
    }
}