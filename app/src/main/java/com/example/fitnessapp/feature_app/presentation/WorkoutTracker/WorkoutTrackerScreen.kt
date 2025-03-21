package com.example.fitnessapp.feature_app.presentation.WorkoutTracker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.WorkoutTracker.components.AllWorkoutCard
import com.example.fitnessapp.feature_app.presentation.WorkoutTracker.components.UserWorkoutCard
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomCanvasBarChart
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomLightGreenCard
import com.example.fitnessapp.feature_app.presentation.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.ui.theme._03AE8C
import com.example.fitnessapp.feature_app.presentation.ui.theme._1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40010White
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50012_A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60016Bold_1D1617

@Composable
fun WorkoutTrackerScreen(
    navController: NavController,
    viewModel: WorkoutViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    if (state.exception.isNotEmpty()) {
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(WorkoutEvent.ResetException)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(_03AE8C),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            CustomTopAppBar(
                title = "Трекер тренировок",
                moreInformationClick = {
                    navController.navigate(Route.WorkoutScheduleScreen.route)
                },
                backgroundColor = _F7F8F8,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(horizontal = 30.dp),
                textColor = Color.White
            ) {
                navController.navigate(Route.HomeScreen.route){
                    popUpTo(Route.WorkoutTrackerScreen.route){
                        inclusive = true
                    }
                }
            }
        }

        item {
            AnimatedVisibility(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(horizontal = 30.dp),
                visible = state.workoutBar.isNotEmpty(),
                enter = slideInHorizontally(tween(500, easing = LinearOutSlowInEasing))
            ) {
                CustomCanvasBarChart(
                    list = state.workoutBar,
                    height = 200.dp,
                    textStyle = montserrat40010White,
                    indexToDarkBarColor = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Transparent)
                )
            }
            Spacer(Modifier.height(20.dp))
        }

        item {
            LazyColumn(
                modifier = Modifier
                    .fillParentMaxSize()
                    .background(Color.White, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(Modifier.height(10.dp))
                    Box(
                        Modifier
                            .size(50.dp, 5.dp)
                            .background(_1D1617)
                            .alpha(0.1f)
                    )
                    Spacer(Modifier.height(15.dp))
                    CustomLightGreenCard(
                        title = "Рассписание трен.",
                        buttonText = "Проверить",
                        modifier = Modifier
                            .fillParentMaxWidth()
                    ) {
                        navController.navigate(Route.WorkoutScheduleScreen.route)
                    }
                    Spacer(Modifier.height(30.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillParentMaxWidth()
                    ) {
                        Text(
                            text = "Тренировка",
                            style = montserrat60016Bold_1D1617
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "Больше",
                            style = montserrat50012_A5A3B0
                        )
                    }
                    Spacer(Modifier.height(15.dp))
                }

                items(state.userWorkoutList.sortedBy {item -> item.isTurnOn }, key = {it.id}) { userWorkoutData ->
                    UserWorkoutCard(
                        userWorkoutData = userWorkoutData,
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .animateItem(
                                fadeInSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessVeryLow
                                )
                            )
                    ) {
                        viewModel.onEvent(WorkoutEvent.ChangeUserWorkoutState(userWorkoutData))
                    }
                    Spacer(Modifier.height(15.dp))
                }

                item {
                    Spacer(Modifier.height(15.dp))
                    Text(
                        text = "Что вы хотите тренировать",
                        style = montserrat60016Bold_1D1617,
                        modifier = Modifier
                            .fillParentMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                    Spacer(Modifier.height(15.dp))
                }

                items(state.workoutList) { workoutData ->
                    AllWorkoutCard(
                        workoutData = workoutData,
                        modifier = Modifier
                            .fillParentMaxWidth()
                    ) {
                        Route.WorkoutDetailScreen.workoutData = workoutData
                        navController.navigate(Route.WorkoutDetailScreen.route)
                    }
                    Spacer(Modifier.height(15.dp))
                }
            }
        }
    }

    CustomIndicator(state.showIndicator)
}