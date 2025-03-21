package com.example.fitnessapp.feature_app.presentation.MealSchedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.UserMealSchedule
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetDietaryRecommendationByIDUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetUserMealScheduleByDateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetUserMealScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MealScheduleViewModel @Inject constructor(
    private val getUserMealScheduleUseCase: GetUserMealScheduleUseCase,
    private val getDietaryRecommendationByIDUseCase: GetDietaryRecommendationByIDUseCase,
    private val getUserMealScheduleByDateUseCase: GetUserMealScheduleByDateUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(MealScheduleState())
    val state: State<MealScheduleState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = state.value.copy(showIndicator = true)
            try {
                getUserMealSchedule()
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    exception = e.message.toString(),
                    showIndicator = false
                )
            }
            _state.value = state.value.copy(showIndicator = false)
        }
    }

    private suspend fun getUserMealSchedule() {

        getUserMealScheduleUseCase().collect {
            when (it){
                is NetworkResult.Error<*> -> {
                    _state.value = state.value.copy(exception = it.message ?: "unknown error", showIndicator = false)
                }
                is NetworkResult.Loading<*> -> {
                    _state.value = state.value.copy(showIndicator = true)
                }
                is NetworkResult.Success<*> -> {
                    getMealDetail(it.data ?: emptyList())

                    withContext(Dispatchers.Main) {
                        _state.value = state.value.copy(
                            mealSchedule = it.data ?: emptyList(),
                            showIndicator = false
                        )
                    }
                }
            }
        }

    }

    private suspend fun getMealDetail(userMealSchedule: List<UserMealSchedule>) {
        _state.value = MealScheduleState()
        userMealSchedule.forEach {
            val meal = getDietaryRecommendationByIDUseCase(it.mealID)

            withContext(Dispatchers.Main) {

                when (it.category) {
                    UserMealSchedule.CATEGORY_BREAKFAST -> {
                        _state.value = state.value.copy(
                            breakfastMeal = _state.value.breakfastMeal + MealScheduleItem(
                                image = meal.image,
                                name = meal.title,
                                time = it.time
                            ),
                            breakfastCalories = if (_state.value.breakfastCalories.isNotEmpty()) {
                                (_state.value.breakfastCalories.toInt() + meal.calories.toInt())
                                    .toString()
                            } else {
                                meal.calories
                            }
                        )
                    }

                    UserMealSchedule.CATEGORY_LUNCH -> {
                        _state.value = state.value.copy(
                            launchMeal = _state.value.launchMeal + MealScheduleItem(
                                image = meal.image,
                                name = meal.title,
                                time = it.time
                            ),
                            lunchCalories = if (_state.value.lunchCalories.isNotEmpty()) {
                                (
                                        _state.value.lunchCalories.toInt() + meal.calories.toInt()
                                        )
                                    .toString()
                            } else {
                                meal.calories
                            }
                        )
                    }

                    UserMealSchedule.CATEGORY_AFTERNOON_SNACK -> {
                        _state.value = state.value.copy(
                            afternoonMeal = _state.value.afternoonMeal + MealScheduleItem(
                                image = meal.image,
                                name = meal.title,
                                time = it.time
                            ),
                            afternoonCalories = if (_state.value.afternoonCalories.isNotEmpty()) {
                                (
                                        _state.value.afternoonCalories.toInt() + meal.calories.toInt()
                                        )
                                    .toString()
                            } else {
                                meal.calories
                            }
                        )
                    }

                    UserMealSchedule.CATEGORY_DINNER -> {
                        _state.value = state.value.copy(
                            dinnerMeal = _state.value.dinnerMeal + MealScheduleItem(
                                image = meal.image,
                                name = meal.title,
                                time = it.time
                            ),
                            dinnerCalories = if (_state.value.dinnerCalories.isNotEmpty()) {
                                (
                                        _state.value.dinnerCalories.toInt() + meal.calories.toInt()
                                        )
                                    .toString()
                            } else {
                                meal.calories
                            }
                        )
                    }

                }

                _state.value = state.value.copy(
                    dietaryRecommendation = _state.value.dietaryRecommendation + meal,
                    caloriesSum = _state.value.caloriesSum + meal.calories.toInt(),
                    proteinSum = _state.value.proteinSum + meal.protein.toInt(),
                    fatSum = _state.value.fatSum + meal.fat.toInt(),
                    carboSum = _state.value.carboSum + meal.carbo.toInt()
                )
            }
        }
    }

    fun onEvent(event: MealScheduleEvent) {
        when (event) {
            MealScheduleEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }

            is MealScheduleEvent.MonthClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.value =
                        state.value.copy(showIndicator = true, currentDay = event.value.dayOfMonth)
                    val date = event.value
                    try {
                        val meal = getUserMealScheduleByDateUseCase(
                            date.year,
                            date.monthValue,
                            date.dayOfMonth
                        )
                        getMealDetail(meal)
                        withContext(Dispatchers.Main) {
                            _state.value = state.value.copy(
                                mealSchedule = meal,
                                currentDay = event.value.dayOfMonth
                            )
                        }
                    } catch (e: Exception) {
                        _state.value = state.value.copy(exception = e.message.toString())
                    }
                    _state.value = state.value.copy(showIndicator = false)
                }
            }
        }
    }
}