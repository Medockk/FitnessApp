package com.example.fitnessapp.feature_app.presentation.Welcome

import android.os.CountDownTimer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.feature_app.domain.usecase.Queue.QueueUseCase

class WelcomeViewModel(
    private val queueUseCase: QueueUseCase
) : ViewModel() {

    private val _state = mutableStateOf(WelcomeState())
    val state: State<WelcomeState> = _state

    init {
        if (queueUseCase.getQueueUseCase() == -1){
            _state.value = state.value.copy(isQueueComplete = true)
        }else{
            startTimer()
        }
    }

    private fun startTimer() {
        val timer = object : CountDownTimer(2500, 1000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                _state.value = state.value.copy(
                    isComplete = true
                )
            }

        }
        timer.start()
    }
}