package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.model.UserDataEntity
import com.example.fitnessapp.feature_app.domain.repository.UserDao

class DeleteUserDataDaoUseCase(
    private val userDao: UserDao
) {

    operator fun invoke(userDataEntity: UserDataEntity){
        userDao.deleteUserData(userDataEntity)
    }
}