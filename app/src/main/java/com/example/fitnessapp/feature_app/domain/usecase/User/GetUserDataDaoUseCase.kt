package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.model.UserDataEntity
import com.example.fitnessapp.feature_app.domain.repository.UserDao
import kotlinx.coroutines.flow.Flow

class GetUserDataDaoUseCase(
    private val userDao: UserDao
) {

    operator fun invoke(id: Int) : Flow<UserDataEntity>{
        return userDao.getUserData(id)
    }
}