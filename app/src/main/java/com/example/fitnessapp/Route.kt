package com.example.fitnessapp

sealed class Route(val route: String) {

    data object WelcomeScreen : Route("WelcomeScreen")
    data object OnBoardScreen : Route("OnBoardScreen")

    data object SignInScreen : Route("SignInScreen")
    data object SuccessRegistrationScreen : Route("SuccessRegistrationScreen")
    data object SignUpScreen : Route("SignUpScreen")
    data object RegisterPageScreen : Route("RegisterPageScreen")
}