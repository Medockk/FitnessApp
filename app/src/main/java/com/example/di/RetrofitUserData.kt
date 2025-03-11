package com.example.di

import com.example.fitnessapp.feature_app.data.data_source.services.RetrofitApi
import com.example.fitnessapp.feature_app.data.repository.UserDataRetrofitRepositoryImpl
import com.example.fitnessapp.feature_app.domain.usecase.UserRetrofit.GetUserById
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val moduleRetrofitUserData = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://petstore.swagger.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }
    single {
        UserDataRetrofitRepositoryImpl(get())
    }
    factory {
        GetUserById(get())
    }
}