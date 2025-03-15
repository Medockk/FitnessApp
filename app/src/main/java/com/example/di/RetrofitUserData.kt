package com.example.di

import com.example.fitnessapp.feature_app.data.data_source.services.RetrofitApi
import com.example.fitnessapp.feature_app.data.repository.UserDataRetrofitRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.UserDataRetrofitRepository
import com.example.fitnessapp.feature_app.domain.usecase.UserRetrofit.GetUserByIdUseCase
import com.example.fitnessapp.feature_app.domain.usecase.UserRetrofit.PostUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitUserData {

    @Provides
    @Singleton
    fun getRetrofitApi() : RetrofitApi{
        return Retrofit.Builder()
            .baseUrl("https://petstore.swagger.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun getUserDataRetrofitRepository(retrofitApi: RetrofitApi) : UserDataRetrofitRepository{
        return UserDataRetrofitRepositoryImpl(retrofitApi)
    }

    //factories
    @Provides @Singleton
    fun getUserByIdUseCase(userDataRetrofitRepository: UserDataRetrofitRepository) : GetUserByIdUseCase{
        return GetUserByIdUseCase(userDataRetrofitRepository)
    }
    @Provides @Singleton
    fun postUserUseCase(userDataRetrofitRepository: UserDataRetrofitRepository) : PostUserUseCase{
        return PostUserUseCase(userDataRetrofitRepository)
    }
}