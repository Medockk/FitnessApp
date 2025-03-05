package com.example.fitnessapp.feature_app.presentation.OnBoard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.feature_app.domain.usecase.Queue.QueueUseCase

class OnBoardViewModel(
    private val queueUseCase: QueueUseCase
) : ViewModel() {

    private val _state = mutableStateOf(OnBoardState())
    val state: State<OnBoardState> = _state

    init {
        getQueue()
    }

    private fun getQueue() {

        val queue = queueUseCase.checkQueueUseCase()

        if (queue){
            _state.value = state.value.copy(
                currentPage = queueUseCase.getQueueUseCase()
            )
        }else{
            _state.value = state.value.copy(
                isComplete = true
            )
        }
    }

    fun onEvent(event: OnBoardEvent){
        when (event){
            is OnBoardEvent.NextPage -> {
                if (event.value < _state.value.onBoardItem.size){
                    _state.value = state.value.copy(
                        currentPage = event.value,
                    )
                    queueUseCase.setQueueUseCase(event.value)
                }else{
                    _state.value = state.value.copy(
                        isComplete = true
                    )
                    queueUseCase.setQueueUseCase(-1)
                }
            }
        }
    }
}