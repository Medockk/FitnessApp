package com.example.fitnessapp.feature_app.domain.usecase.Queue

import com.example.fitnessapp.feature_app.domain.manger.QueueManger

class CheckQueueUseCase(
    private val queueManger: QueueManger
) {

    operator fun invoke() : Boolean{
        val queue = queueManger.getQueue()
        return queue!=-1
    }
}