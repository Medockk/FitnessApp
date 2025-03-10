package com.example.fitnessapp.feature_app.presentation.MealDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Meal.AddMealToUserMealScheduleUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetMealDetailsUseCase
import com.example.fitnessapp.feature_app.presentation.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealDetailsViewModel(
    private val getMealDetailsUseCase: GetMealDetailsUseCase,
    private val addMealToUserMealScheduleUseCase: AddMealToUserMealScheduleUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MealDetailState())
    val state: State<MealDetailState> = _state

    init {
        getNutrition()
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = state.value.copy(showIndicator = true)
            try {
                getDetails()
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    exception = e.message.toString(),
                    showIndicator = false
                )
            }
            _state.value = state.value.copy(showIndicator = false)
        }
    }

    private suspend fun getDetails() {

        val details = getMealDetailsUseCase(Route.MealDetailScreen.dietary!!.id)
        var receiptStep = ""
        var ingredient = ""

        withContext(Dispatchers.Main){
            _state.value = state.value.copy(
                details = details
            )
        }

        details.ingredientsAndTheyCount.forEach {
            if (it.toString() != ","){
                ingredient += it.toString()
            }else{
                _state.value = state.value.copy(
                    ingredients = _state.value.ingredients.plus(ingredient),
                    ingredientCount = _state.value.ingredientCount + 1
                )
                ingredient = ""
            }
        }

        details.stepDescription.forEach {
           if (it.toString() != ";"){
               receiptStep += it.toString()
           }else{
               _state.value = state.value.copy(
                   receipt = _state.value.receipt.plus(receiptStep)
               )
               receiptStep = ""
           }
        }
    }

    private fun getNutrition() {

        _state.value = state.value.copy(
            nutrition = _state.value.nutrition.plus(
                Route.MealDetailScreen.dietary!!.calories
            ).plus(
                Route.MealDetailScreen.dietary!!.fat
            ).plus(
                Route.MealDetailScreen.dietary!!.protein
            ).plus(
                Route.MealDetailScreen.dietary!!.carbo
            )
        )
    }

    fun onEvent(event: MealDetailsEvent){
        when (event){
            MealDetailsEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }

            is MealDetailsEvent.AddToBreakfast -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.value = state.value.copy(showIndicator = true)
                    try {
                        addMealToUserMealScheduleUseCase(event.category, event.mealID)
                    } catch (e: Exception) {
                        _state.value = state.value.copy(exception = e.message.toString())
                    }
                    _state.value = state.value.copy(showIndicator = false)
                }
            }
        }
    }
}