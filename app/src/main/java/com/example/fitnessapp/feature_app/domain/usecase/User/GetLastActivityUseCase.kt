package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.model.LastActivityData
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository

class GetLastActivityUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke() : List<LastActivityData>{
        return userDataRepository.getLastActivity()
    }
}