package com.example.fitnessapp.feature_app.domain.usecase.Queue

import com.example.fitnessapp.feature_app.domain.manger.QueueManger

class GetQueueUseCase(
    private val queueManger: QueueManger
) {

    operator fun invoke() : Int{
        return queueManger.getQueue()
    }
}