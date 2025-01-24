package com.example.fitnessapp.feature_app.domain.usecase.Queue

import com.example.fitnessapp.feature_app.domain.manger.QueueManger

class SetQueueUseCase(
    private val queueManger: QueueManger
) {

    operator fun invoke(position: Int){
        queueManger.setQueue(position)
    }
}