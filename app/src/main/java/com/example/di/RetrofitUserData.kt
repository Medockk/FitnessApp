package com.example.di

import com.example.fitnessapp.feature_app.data.data_source.services.RetrofitApi
import com.example.fitnessapp.feature_app.data.repository.UserDataRetrofitRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.UserDataRetrofitRepository
import com.example.fitnessapp.feature_app.domain.usecase.UserRetrofit.GetUserByIdUseCase
import com.example.fitnessapp.feature_app.domain.usecase.UserRetrofit.PostUserUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val moduleRetrofitUserData = module {

    single<RetrofitApi> {
        Retrofit.Builder()
            .baseUrl("https://petstore.swagger.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }
    single<UserDataRetrofitRepository> {
        UserDataRetrofitRepositoryImpl(get())
    }
    factory<GetUserByIdUseCase> {
        GetUserByIdUseCase(get())
    }
    factory<PostUserUseCase> {
        PostUserUseCase(get())
    }
}