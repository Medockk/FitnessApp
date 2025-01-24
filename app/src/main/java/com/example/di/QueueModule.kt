package com.example.di

import com.example.fitnessapp.feature_app.data.manger.QueueMangerImpl
import com.example.fitnessapp.feature_app.domain.manger.QueueManger
import com.example.fitnessapp.feature_app.domain.usecase.Queue.CheckQueueUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Queue.ClearQueueUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Queue.GetQueueUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Queue.QueueUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Queue.SetQueueUseCase
import org.koin.dsl.module

val moduleQueue = module {

    single<QueueManger> {
        QueueMangerImpl(get())
    }

    factory<QueueUseCase> {
        QueueUseCase(
            GetQueueUseCase(get()),
            SetQueueUseCase(get()),
            ClearQueueUseCase(get()),
            CheckQueueUseCase(get())
        )
    }
}