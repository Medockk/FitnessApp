package com.example.fitnessapp.feature_app.domain.usecase.Queue

data class QueueUseCase(
    val getQueueUseCase: GetQueueUseCase,
    val setQueueUseCase: SetQueueUseCase,
    val clearQueueUseCase: ClearQueueUseCase,
    val checkQueueUseCase: CheckQueueUseCase
)
