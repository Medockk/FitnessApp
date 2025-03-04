package com.example.di

import com.example.fitnessapp.feature_app.data.dao.UserDataDao
import com.example.fitnessapp.feature_app.data.dao.UserDataDaoImpl
import com.example.fitnessapp.feature_app.domain.usecase.Dao.DeleteUserDataDaoUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Dao.GetUserDataDaoUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Dao.UpsertUserDataDaoUseCase
import org.koin.dsl.module

val moduleUserDao = module {

    single {
        UserDataDao.createDataBase(get())
    }
    single {
        get<UserDataDao>().userDao
    }
    single {
        UserDataDaoImpl(get())
    }
    factory<DeleteUserDataDaoUseCase> {
        DeleteUserDataDaoUseCase(get())
    }
    factory<GetUserDataDaoUseCase> {
        GetUserDataDaoUseCase(get())
    }
    factory<UpsertUserDataDaoUseCase> {
        UpsertUserDataDaoUseCase(get())
    }
}