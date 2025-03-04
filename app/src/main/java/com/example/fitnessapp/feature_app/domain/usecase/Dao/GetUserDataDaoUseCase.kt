package com.example.fitnessapp.feature_app.domain.usecase.Dao

import com.example.fitnessapp.feature_app.data.dao.UserDataDaoImpl
import com.example.fitnessapp.feature_app.domain.model.UserDataEntity
import kotlinx.coroutines.flow.Flow

class GetUserDataDaoUseCase(
    private val userDataDaoImpl: UserDataDaoImpl
) {

    operator fun invoke(email: String) : Flow<List<UserDataEntity>>{
        return userDataDaoImpl.getUserData(email)
    }
}