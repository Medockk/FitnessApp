package com.example.fitnessapp.feature_app.presentation.CompareResult

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.usecase.Compare.GetGalleryFromMonthToMonthUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Compare.GetStatisticFromMonthToMonthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CompareResultViewModel @Inject constructor(
    private val getGalleryFromMonthToMonthUseCase: GetGalleryFromMonthToMonthUseCase,
    private val getStatisticFromMonthToMonthUseCase: GetStatisticFromMonthToMonthUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CompareResultState())
    val state: State<CompareResultState> = _state

    init {
        _state.value = state.value.copy(showIndicator = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getGallery()
            } catch (e: Exception) {
                _state.value = state.value.copy(exception = e.message.toString())
            }
        }
        _state.value = state.value.copy(showIndicator = false)
    }

    private suspend fun getGallery() {
        getMonth()
        val firstMonthNumber = getMonthNumber(_state.value.firstMonth)
        val secondMonthNumber = getMonthNumber(_state.value.secondMonth)
        val gallery = getGalleryFromMonthToMonthUseCase(
            firstMonthNumber, secondMonthNumber
        )

        withContext(Dispatchers.Main) {
            _state.value = state.value.copy(
                gallery = gallery
            )
        }

        getStatisticFromMonthToMonthUseCase(firstMonthNumber, secondMonthNumber).collect{
            when (it){
                is NetworkResult.Error<*> -> {
                    _state.value = state.value.copy(
                        exception = it.message ?: "Unknown error...",
                        showIndicator = false
                    )
                }
                is NetworkResult.Loading<*> -> {
                    _state.value = state.value.copy(showIndicator = true)
                }
                is NetworkResult.Success<*> -> {
                    it.data?.sortedBy {data-> data.type }?.forEach {
                        withContext(Dispatchers.Main){
                            _state.value = state.value.copy(
                                statistic = _state.value.statistic+it,
                                showIndicator = false
                            )
                        }
                    }
                }
            }
        }

    }

    fun onEvent(event: CompareResultEvent) {
        when (event) {
            CompareResultEvent.ResetException -> {
                _state.value = state.value.copy(exception = "")
            }
        }
    }

    private fun getMonth() {
        _state.value = state.value.copy(
            firstMonth = GalleryData.firstMonth,
            secondMonth = GalleryData.secondMonth
        )
    }
    private fun getMonthNumber(month: String): String {
        return if (month.trim() == "Январь") {
            "1"
        } else if (month.trim() == "Февраль") {
            "2"
        } else if (month.trim() == "Март") {
            "3"
        } else if (month.trim() == "Апрель") {
            "4"
        } else if (month.trim() == "Май") {
            "5"
        } else if (month.trim() == "Июнь") {
            "6"
        } else if (month.trim() == "Июль") {
            "7"
        } else if (month.trim() == "Август") {
            "8"
        } else if (month.trim() == "Сентябрь") {
            "9"
        } else if (month.trim() == "Октябрь") {
            "10"
        } else if (month.trim() == "Ноябрь") {
            "11"
        } else {
            "12"
        }
    }
}