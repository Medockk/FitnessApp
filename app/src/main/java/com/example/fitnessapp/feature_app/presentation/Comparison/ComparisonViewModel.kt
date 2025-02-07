package com.example.fitnessapp.feature_app.presentation.Comparison

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.feature_app.domain.model.GalleryData

class ComparisonViewModel : ViewModel() {

    private val _state = mutableStateOf(ComparisonState())
    val state: State<ComparisonState> = _state

    init {

    }

    fun onEvent(event: ComparisonEvent){
        when (event){
            ComparisonEvent.ComparisonClick -> {
                GalleryData.firstMonth = _state.value.firstMount
                GalleryData.secondMonth = _state.value.secondMount
            }
            ComparisonEvent.ResetException -> {
                _state.value = state.value.copy(exception = "")
            }

            ComparisonEvent.ChangeFirstMonthShowDropdownMenuState -> {
                _state.value = state.value.copy(showFirstMonthDropDownMenu = !_state.value.showFirstMonthDropDownMenu)
            }
            ComparisonEvent.ChangeSecondMonthShowDropdownMenuState -> {
                _state.value = state.value.copy(showSecondMonthDropDownMenu = !_state.value.showSecondMonthDropDownMenu)
            }

            is ComparisonEvent.EnterFirstMonth -> {
                _state.value = state.value.copy(firstMount = event.value)
            }
            is ComparisonEvent.EnterSecondMonth -> {
                _state.value = state.value.copy(secondMount = event.value)
            }

        }
    }
}