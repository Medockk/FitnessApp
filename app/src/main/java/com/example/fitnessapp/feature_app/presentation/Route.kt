package com.example.fitnessapp.feature_app.presentation

import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.domain.model.WorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutDetails

sealed class Route(val route: String) {

    data object WelcomeScreen : Route("WelcomeScreen")
    data object OnBoardScreen : Route("OnBoardScreen")

    data object SignInScreen : Route("SignInScreen")
    data object SuccessRegistrationScreen : Route("SuccessRegistrationScreen")
    data object SignUpScreen : Route("SignUpScreen")
    data object RegisterPageScreen : Route("RegisterPageScreen")
    data object CreateProfileScreen : Route("CreateProfileScreen")

    data object HomeScreen : Route("HomeScreen")
    data object NotificationScreen : Route("NotificationScreen")
    data object ProgressPhotoScreen : Route("ProgressPhotoScreen")
    data object ProfileScreen : Route("ProfileScreen")
    data object CongratulationsScreen : Route("CongratulationsScreen")
    data object ActivityTrackerScreen : Route("ActivityTrackerScreen")
    data object WorkoutTrackerScreen : Route("WorkoutTrackerScreen")

    data object WorkoutDetailScreen : Route("WorkoutDetailScreen"){
        var workoutData = WorkoutData(0,"","","","")
    }
    data object CategoryBreakfastScreen : Route("CategoryBreakfastScreen")

    data object MealDetailScreen : Route("MealDetailScreen"){
        var meal = DietaryRecommendation(0,"","","","","","", "","","","")
    }
    data object MealScheduleScreen : Route("MealScheduleScreen")
    data object WorkoutScheduleScreen : Route("WorkoutScheduleScreen")
    data object AddWorkoutScheduleScreen : Route("AddWorkoutScheduleScreen")
    data object StartWorkoutScreen : Route("StartWorkoutScreen"){
        var workout = WorkoutDetails(0,0,"","")
        var title = ""
    }

    data object SleepTrackerScreen : Route("SleepTrackerScreen")
    data object SleepScheduleScreen : Route("SleepScheduleScreen")
    data object AddAlarmScreen : Route("AddAlarmScreen")

    data object ComparisonScreen : Route("ComparisonScreen")
    data object CompareResultScreen : Route("CompareResultScreen")
    data object TakePhotoScreen : Route("TakePhotoScreen")
}