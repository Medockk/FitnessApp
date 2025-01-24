package com.example.fitnessapp.feature_app.domain.usecase.Queue

import com.example.fitnessapp.feature_app.domain.manger.QueueManger

class ClearQueueUseCase(
    private val queueManger: QueueManger
) {

    operator fun invoke(){
        queueManger.setQueue(0)
    }
}