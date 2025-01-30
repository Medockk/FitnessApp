package com.example.fitnessapp.feature_app.presentation.WorkoutTracker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.common.CustomAlertDialog
import com.example.common.CustomLightGreenCard
import com.example.common.CustomTopAppBar
import com.example.fitnessapp.Route
import com.example.fitnessapp.feature_app.presentation.WorkoutTracker.components.AllWorkoutCard
import com.example.fitnessapp.feature_app.presentation.WorkoutTracker.components.UserWorkoutCard
import com.example.fitnessapp.feature_app.presentation.WorkoutTracker.components.WorkoutBar
import com.example.fitnessapp.ui.theme._1D1617
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme._3FF9BE
import com.example.fitnessapp.ui.theme._9CEEDF
import com.example.fitnessapp.ui.theme._B0F8E1
import com.example.fitnessapp.ui.theme._F7F8F8
import com.example.fitnessapp.ui.theme.montserrat50012_A5A3B0
import com.example.fitnessapp.ui.theme.montserrat60016_1D1617
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    WorkoutTrackerScreen(rememberNavController())
}

@Composable
fun WorkoutTrackerScreen(
    navController: NavController,
    viewModel: WorkoutViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val backgroundBrush = Brush.linearGradient(
        listOf(
            _228F7D,
            _9CEEDF
        )
    )

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
            .background(backgroundBrush),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            CustomTopAppBar(
                title = "Трекер тренировок",
                moreInformationClick = {},
                backgroundColor = _F7F8F8,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                navController.popBackStack()
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
                WorkoutBar(
                    list = state.workoutBar,
                    lineColor = listOf(_B0F8E1, _3FF9BE),
                    modifier = Modifier
                        .background(Color.Transparent)
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
                    .background(Color.White, RoundedCornerShape(40.dp))
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
                        navController.navigate(Route.ActivityTrackerScreen.route)
                    }
                    Spacer(Modifier.height(30.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillParentMaxWidth()
                    ) {
                        Text(
                            text = "Тренировка",
                            style = montserrat60016_1D1617
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "Больше",
                            style = montserrat50012_A5A3B0
                        )
                    }
                    Spacer(Modifier.height(15.dp))
                }

                items(state.userWorkoutList) { userWorkoutData ->
                    UserWorkoutCard(
                        userWorkoutData = userWorkoutData,
                        modifier = Modifier
                            .fillParentMaxWidth(),
                    ) {
                        viewModel.onEvent(WorkoutEvent.ChangeUserWorkoutState(userWorkoutData))
                    }
                    Spacer(Modifier.height(15.dp))
                }

                item {
                    Spacer(Modifier.height(15.dp))
                    Text(
                        text = "Что вы хотите тренировать",
                        style = montserrat60016_1D1617
                    )
                    Spacer(Modifier.height(15.dp))
                }

                items(state.workoutList){workoutData ->
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
}