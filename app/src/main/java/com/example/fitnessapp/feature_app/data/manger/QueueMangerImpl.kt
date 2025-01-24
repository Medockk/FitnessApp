package com.example.fitnessapp.feature_app.data.manger

import android.content.Context
import com.example.fitnessapp.feature_app.domain.manger.QueueManger

class QueueMangerImpl(
    private val context: Context
) : QueueManger {

    private val queueKey = "queueKey"
    private val sp = context.getSharedPreferences(queueKey, Context.MODE_PRIVATE)

    override fun getQueue(): Int {
        return sp.getInt(queueKey, 0)
    }

    override fun setQueue(position: Int) {
        sp.edit().clear().putInt(queueKey, position).apply()
    }
}