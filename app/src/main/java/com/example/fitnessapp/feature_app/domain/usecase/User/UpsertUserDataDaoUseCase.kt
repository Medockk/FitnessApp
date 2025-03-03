package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.model.UserDataEntity
import com.example.fitnessapp.feature_app.domain.repository.UserDao

class UpsertUserDataDaoUseCase(
    private val userDao: UserDao
) {

    suspend operator fun invoke(userDataEntity: UserDataEntity){
        userDao.upsertUserData(userDataEntity)
    }
}