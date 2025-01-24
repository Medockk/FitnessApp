package com.example.fitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.feature_app.presentation.OnBoard.OnBoardScreen
import com.example.fitnessapp.feature_app.presentation.SignIn.SignInScreen
import com.example.fitnessapp.feature_app.presentation.Welcome.WelcomeScreen
import com.example.fitnessapp.ui.theme.FitnessAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            FitnessAppTheme(
                dynamicColor = false,
                darkTheme = false
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Route.WelcomeScreen.route
                ){
                    composable(Route.WelcomeScreen.route){
                        WelcomeScreen(navController)
                    }
                    composable(Route.OnBoardScreen.route){
                        OnBoardScreen(navController)
                    }
                    composable(Route.SignInScreen.route){
                        SignInScreen(navController)
                    }
                }
            }
        }
    }
}