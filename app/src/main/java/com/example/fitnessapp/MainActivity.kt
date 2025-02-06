package com.example.fitnessapp

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.feature_app.presentation.ActivityTracker.ActivityTrackerScreen
import com.example.fitnessapp.feature_app.presentation.AddAlarm.AddAlarmScreen
import com.example.fitnessapp.feature_app.presentation.AddWorkoutSchedule.AddWorkoutScheduleScreen
import com.example.fitnessapp.feature_app.presentation.CategoryBreakfast.CategoryBreakfastScreen
import com.example.fitnessapp.feature_app.presentation.Comparison.ComparisonScreen
import com.example.fitnessapp.feature_app.presentation.Congratulations.CongratulationsScreen
import com.example.fitnessapp.feature_app.presentation.CreateProfile.CreateProfileScreen
import com.example.fitnessapp.feature_app.presentation.Home.HomeScreen
import com.example.fitnessapp.feature_app.presentation.MealDetail.MealDetailScreen
import com.example.fitnessapp.feature_app.presentation.MealSchedule.MealScheduleScreen
import com.example.fitnessapp.feature_app.presentation.Notification.NotificationScreen
import com.example.fitnessapp.feature_app.presentation.OnBoard.OnBoardScreen
import com.example.fitnessapp.feature_app.presentation.Profile.ProfileScreen
import com.example.fitnessapp.feature_app.presentation.ProgressPhoto.ProgressPhotoScreen
import com.example.fitnessapp.feature_app.presentation.RegisterPage.RegisterPageScreen
import com.example.fitnessapp.feature_app.presentation.SignIn.SignInScreen
import com.example.fitnessapp.feature_app.presentation.SignUp.SignUpScreen
import com.example.fitnessapp.feature_app.presentation.SleepSchedule.SleepScheduleScreen
import com.example.fitnessapp.feature_app.presentation.SleepTracker.SleepTrackerScreen
import com.example.fitnessapp.feature_app.presentation.StartWorkout.StartWorkoutScreen
import com.example.fitnessapp.feature_app.presentation.SuccessRegistration.SuccessRegistrationScreen
import com.example.fitnessapp.feature_app.presentation.Welcome.WelcomeScreen
import com.example.fitnessapp.feature_app.presentation.WorkoutDetail.WorkoutDetailScreen
import com.example.fitnessapp.feature_app.presentation.WorkoutSchedule.WorkoutScheduleScreen
import com.example.fitnessapp.feature_app.presentation.WorkoutTracker.WorkoutTrackerScreen
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import com.mahmoud.composecharts.barchart.BarChart
import com.mahmoud.composecharts.barchart.BarChartEntity

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
                    startDestination = Route.WelcomeScreen.route,
                    enterTransition = {
                        slideInHorizontally(tween(700, easing = LinearOutSlowInEasing)) +
                                expandHorizontally(expandFrom = Alignment.Start) +
                                fadeIn()
                    },
                    exitTransition = {
                        fadeOut() + slideOutHorizontally() +
                                shrinkHorizontally()
                    }
                ) {
                    composable("Test"){
                        Test()
                    }
                    composable(Route.WelcomeScreen.route) {
                        WelcomeScreen(navController)
                    }
                    composable(Route.OnBoardScreen.route) {
                        OnBoardScreen(navController)
                    }
                    composable(Route.SignInScreen.route) {
                        SignInScreen(navController)
                    }
                    composable(Route.SuccessRegistrationScreen.route) {
                        SuccessRegistrationScreen(navController)
                    }
                    composable(Route.SignUpScreen.route) {
                        SignUpScreen(navController)
                    }
                    composable(Route.RegisterPageScreen.route) {
                        RegisterPageScreen(navController)
                    }
                    composable(Route.CreateProfileScreen.route) {
                        CreateProfileScreen(navController)
                    }

                    composable(Route.HomeScreen.route) {
                        HomeScreen(navController)
                    }
                    composable(Route.NotificationScreen.route) {
                        NotificationScreen(navController)
                    }
                    composable(Route.ProfileScreen.route) {
                        ProfileScreen(navController)
                    }
                    composable(Route.CongratulationsScreen.route) {
                        CongratulationsScreen(navController)
                    }
                    composable(Route.ActivityTrackerScreen.route) {
                        ActivityTrackerScreen(navController)
                    }
                    composable(Route.WorkoutTrackerScreen.route) {
                        WorkoutTrackerScreen(navController)
                    }

                    composable(
                        route = Route.WorkoutDetailScreen.route,
                        enterTransition = {
                            expandVertically(expandFrom = Alignment.Bottom) +
                            slideInVertically(tween(500, easing = LinearOutSlowInEasing),
                                initialOffsetY = { it / 2})
                        },
                        exitTransition = {
                            shrinkVertically(shrinkTowards = Alignment.Top) +
                            slideOutVertically(tween(500, easing = LinearOutSlowInEasing))
                        }
                    ) {
                        WorkoutDetailScreen(
                            navController,
                            workoutData = Route.WorkoutDetailScreen.workoutData
                        )
                    }
                    composable(Route.CategoryBreakfastScreen.route){
                        CategoryBreakfastScreen(navController)
                    }
                    composable(
                        route = Route.MealDetailScreen.route,
                        enterTransition = {
                            expandVertically(expandFrom = Alignment.Bottom) +
                                    slideInVertically(tween(500, easing = LinearOutSlowInEasing),
                                        initialOffsetY = { it / 2})
                        },
                        exitTransition = {
                            shrinkVertically(shrinkTowards = Alignment.Top) +
                                    slideOutVertically(tween(500, easing = LinearOutSlowInEasing))
                        }
                    ) {
                        MealDetailScreen(
                            navController,
                            meal = Route.MealDetailScreen.meal
                        )
                    }
                    composable(Route.MealScheduleScreen.route){
                        MealScheduleScreen(navController)
                    }
                    composable(Route.WorkoutScheduleScreen.route){
                        WorkoutScheduleScreen(navController)
                    }
                    composable(Route.AddWorkoutScheduleScreen.route){
                        AddWorkoutScheduleScreen(navController)
                    }
                    composable(Route.StartWorkoutScreen.route){
                        StartWorkoutScreen(navController)
                    }
                    composable(Route.SleepTrackerScreen.route){
                        SleepTrackerScreen(navController)
                    }
                    composable(Route.SleepScheduleScreen.route){
                        SleepScheduleScreen(navController)
                    }
                    composable(Route.AddAlarmScreen.route){
                        AddAlarmScreen(navController)
                    }

                    composable(Route.ProgressPhotoScreen.route){
                        ProgressPhotoScreen(navController)
                    }
                    composable(Route.ComparisonScreen.route){
                        ComparisonScreen(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Test() {

    val barChartData = listOf(
        BarChartEntity(10f, Color.Blue),
        BarChartEntity(58f, Color.Red),
        BarChartEntity(75f, Color.Green),
        BarChartEntity(23f, Color.DarkGray),
        BarChartEntity(75f, Color.Black),
        BarChartEntity(13f, Color.Yellow),
        BarChartEntity(85f, Color.Magenta),
        BarChartEntity(10f, Color.Blue),
        BarChartEntity(58f, Color.Red),
        BarChartEntity(75f, Color.Green),
        BarChartEntity(23f, Color.DarkGray),
        BarChartEntity(75f, Color.Black),
        BarChartEntity(13f, Color.Yellow),
        BarChartEntity(85f, Color.Magenta),
    )

    BarChart(
        barChartData = barChartData,
        verticalAxisValues = listOf(0f, 20f, 40f, 60f, 80f, 100f),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(
                vertical = 50.dp, horizontal = 30.dp
            )
    )
}