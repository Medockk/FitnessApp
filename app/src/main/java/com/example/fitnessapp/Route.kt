package com.example.fitnessapp

sealed class Route(val route: String) {

    data object WelcomeScreen : Route("WelcomeScreen")
    data object OnBoardScreen : Route("OnBoardScreen")
    data object SignInScreen : Route("SignInScreen")
}