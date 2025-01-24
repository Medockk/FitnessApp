package com.example.fitnessapp

import com.example.fitnessapp.feature_app.domain.manger.QueueManger

class QueueMangerImpl : QueueManger {
    private var queue: Int = 0
    override fun getQueue(): Int {
        return queue
    }

    override fun setQueue(position: Int) {
        this.queue = position
    }
}