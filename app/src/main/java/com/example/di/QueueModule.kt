package com.example.di

import android.content.Context
import com.example.fitnessapp.feature_app.data.manger.QueueMangerImpl
import com.example.fitnessapp.feature_app.domain.manger.QueueManger
import com.example.fitnessapp.feature_app.domain.usecase.Queue.CheckQueueUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Queue.ClearQueueUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Queue.GetQueueUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Queue.QueueUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Queue.SetQueueUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QueueModule {

    @Provides
    @Singleton
    fun getQueueManger(@ApplicationContext context: Context): QueueManger {
        return QueueMangerImpl(context)
    }

    //FACTORIES
    @Provides
    @Singleton
    fun checkQueueUseCase(queueManger: QueueManger): CheckQueueUseCase {
        return CheckQueueUseCase(queueManger)
    }

    @Provides
    @Singleton
    fun clearQueueUseCase(queueManger: QueueManger): ClearQueueUseCase {
        return ClearQueueUseCase(queueManger)
    }

    @Provides
    @Singleton
    fun getQueueUseCase(queueManger: QueueManger): GetQueueUseCase {
        return GetQueueUseCase(queueManger)
    }

    @Provides
    @Singleton
    fun setQueueUseCase(queueManger: QueueManger): SetQueueUseCase {
        return SetQueueUseCase(queueManger)
    }

    @Provides
    @Singleton
    fun queueUseCase(
        getQueueUseCase: GetQueueUseCase,
        setQueueUseCase: SetQueueUseCase,
        clearQueueUseCase: ClearQueueUseCase,
        checkQueueUseCase: CheckQueueUseCase
    ): QueueUseCase {
        return QueueUseCase(getQueueUseCase, setQueueUseCase, clearQueueUseCase, checkQueueUseCase)
    }
}