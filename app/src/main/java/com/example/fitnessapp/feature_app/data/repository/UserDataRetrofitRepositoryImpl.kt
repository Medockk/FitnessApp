package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.data_source.services.RetrofitApi
import com.example.fitnessapp.feature_app.domain.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.UserRetrofitData
import com.example.fitnessapp.feature_app.domain.repository.UserDataRetrofitRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class UserDataRetrofitRepositoryImpl(
    private val retrofitApi: RetrofitApi
) : UserDataRetrofitRepository {
    override suspend fun getUserById(id: Int) = flow<NetworkResult<UserRetrofitData>> {

        emit(NetworkResult.Loading())

        with(retrofitApi.getUserById(id)){
            if (isSuccessful){
                emit(NetworkResult.Success(this.body()))
            }else{
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }
}