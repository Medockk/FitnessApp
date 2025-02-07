package com.example.fitnessapp.feature_app.presentation.CompareResult

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.usecase.Gallery.GetGalleryFromMonthToMonthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompareResultViewModel(
    private val getGalleryFromMonthToMonthUseCase: GetGalleryFromMonthToMonthUseCase
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

        Log.e("gallery", gallery.toString())
        withContext(Dispatchers.Main) {
            _state.value.copy(
                gallery = gallery
            )
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
            "01"
        } else if (month.trim() == "Февраль") {
            "02"
        } else if (month.trim() == "Март") {
            "03"
        } else if (month.trim() == "Апрель") {
            "04"
        } else if (month.trim() == "Май") {
            "05"
        } else if (month.trim() == "Июнь") {
            "06"
        } else if (month.trim() == "Июль") {
            "07"
        } else if (month.trim() == "Август") {
            "08"
        } else if (month.trim() == "Сентябрь") {
            "09"
        } else if (month.trim() == "Октябрь") {
            "10"
        } else if (month.trim() == "Ноябрь") {
            "11"
        } else {
            "12"
        }
    }
}