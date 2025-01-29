package com.example.fitnessapp

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
}