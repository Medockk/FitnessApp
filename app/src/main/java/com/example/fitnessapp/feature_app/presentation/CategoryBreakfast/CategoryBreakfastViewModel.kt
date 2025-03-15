package com.example.fitnessapp.feature_app.presentation.CategoryBreakfast

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetCategoriesUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetDietaryRecommendationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryBreakfastViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getDietaryRecommendationUseCase: GetDietaryRecommendationUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CategoryBreakfastState())
    val state: State<CategoryBreakfastState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = state.value.copy(showIndicator = true)
            try {
                getBreakfastData()
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    exception = e.message.toString(),
                    showIndicator = false
                )
            }
            _state.value = state.value.copy(showIndicator = false)
        }
    }

    private suspend fun getBreakfastData() {

        val categories = getCategoriesUseCase()

        withContext(Dispatchers.Main){
            _state.value = state.value.copy(
                categories = categories
            )
        }
        val mael = getDietaryRecommendationUseCase()
        withContext(Dispatchers.Main){
            _state.value = state.value.copy(
                dietaryRecommendations =
                    _state.value.dietaryRecommendations.plus(mael[0]).plus(mael[1]),
                popularMeal = mael
            )
        }
    }

    fun onEvent(event: CategoryBreakfastEvent){
        when (event){
            is CategoryBreakfastEvent.EnterSearchText -> {
                _state.value = state.value.copy(
                    searchText = event.value
                )
            }
            CategoryBreakfastEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }
        }
    }
}