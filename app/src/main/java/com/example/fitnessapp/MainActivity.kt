package com.example.fitnessapp

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.feature_app.presentation.OnBoard.OnBoardScreen
import com.example.fitnessapp.feature_app.presentation.SignIn.SignInScreen
import com.example.fitnessapp.feature_app.presentation.SignUp.SignUpScreen
import com.example.fitnessapp.feature_app.presentation.Welcome.WelcomeScreen
import com.example.fitnessapp.ui.theme.FitnessAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
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

                    composable(
                        Route.SignInScreen.route,
                        enterTransition = {
                            slideInHorizontally(tween(500, easing = FastOutLinearInEasing)) +
                                    expandHorizontally(tween(500, easing = FastOutLinearInEasing)) +
                                    fadeIn(tween(500, easing = FastOutLinearInEasing))
                        },
                        exitTransition = {
                            slideOutHorizontally(tween(500, easing = FastOutLinearInEasing),{it}) +
                                    shrinkHorizontally(tween(500, easing = FastOutLinearInEasing)) +
                                    fadeOut(tween(500, easing = FastOutLinearInEasing))
                        }
                    ){
                        SignInScreen(navController)
                    }
                    composable(Route.SuccessRegistrationScreen.route){

                    }
                    composable(
                        Route.SignUpScreen.route,
                        enterTransition = {
                            slideInHorizontally(tween(500, easing = FastOutLinearInEasing)) +
                                    expandHorizontally(tween(500, easing = FastOutLinearInEasing)) +
                                    fadeIn(tween(500, easing = FastOutLinearInEasing))
                        },
                        exitTransition = {
                            slideOutHorizontally(tween(500, easing = FastOutLinearInEasing),
                                {it}) +
                                    shrinkHorizontally(tween(500, easing = FastOutLinearInEasing)) +
                                    fadeOut(tween(500, easing = FastOutLinearInEasing))
                        }
                    ){
                        SignUpScreen(navController)
                    }
                    composable(Route.RegisterPageScreen.route){

                    }
                }
            }
        }
    }
}