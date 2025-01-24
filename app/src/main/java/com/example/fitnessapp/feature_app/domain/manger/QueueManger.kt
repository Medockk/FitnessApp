package com.example.fitnessapp.feature_app.domain.manger

interface QueueManger {

    fun getQueue() : Int

    fun setQueue(position: Int)
}