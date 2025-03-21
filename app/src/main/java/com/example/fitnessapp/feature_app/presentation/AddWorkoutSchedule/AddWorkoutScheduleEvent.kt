package com.example.fitnessapp.feature_app.presentation.AddWorkoutSchedule

sealed class AddWorkoutScheduleEvent {

    data object ResetException : AddWorkoutScheduleEvent()

    data object AddWorkout : AddWorkoutScheduleEvent()
    //Устанавливает название тренировки
    data class SetWorkoutTitle(val value: String) : AddWorkoutScheduleEvent()
    data class HourChange(val value: Int) : AddWorkoutScheduleEvent()
    data class MinuteChange(val value: Int) : AddWorkoutScheduleEvent()
}