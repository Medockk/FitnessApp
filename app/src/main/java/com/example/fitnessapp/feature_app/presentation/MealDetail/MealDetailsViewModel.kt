package com.example.fitnessapp.feature_app.presentation.MealDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.Route

class MealDetailsViewModel : ViewModel() {

    private val _state = mutableStateOf(MealDetailState())
    val state: State<MealDetailState> = _state

    init {
        getIngredientsList()
    }

    private fun getIngredientsList() {

        _state.value = state.value.copy(
            nutrition = _state.value.nutrition.plus(
                Route.MealDetailScreen.meal.calories
            ).plus(
                Route.MealDetailScreen.meal.fat
            ).plus(
                Route.MealDetailScreen.meal.protein
            ).plus(
                Route.MealDetailScreen.meal.carbo
            )
        )

        var word = ""
        val ingredients = Route.MealDetailScreen.meal.ingredients

        ingredients.forEach {
            if (it.toString() != " "){
                word += it.toString()
            }else{
                _state.value = state.value.copy(
                    ingredients = _state.value.ingredients.plus(word)
                )
            }
        }

    }
}