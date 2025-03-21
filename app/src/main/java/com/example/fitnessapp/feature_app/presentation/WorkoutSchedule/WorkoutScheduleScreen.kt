package com.example.fitnessapp.feature_app.presentation.WorkoutSchedule

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.WorkoutSchedule.components.CustomWorkoutTextCard
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomDateCard
import com.example.fitnessapp.feature_app.presentation.common.CustomFloatingActionButton
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_B6B4C2
import kotlinx.datetime.LocalDateTime

@Composable
fun WorkoutScheduleScreen(
    navController: NavController,
    viewModel: WorkoutScheduleViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    if (state.exception.isNotEmpty()) {
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(WorkoutScheduleEvent.ResetException)
        }
    }

    BackHandler {
        navController.navigate(Route.WorkoutTrackerScreen.route) {
            popUpTo(Route.WorkoutScheduleScreen.route) {
                inclusive = true
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center
    ) {
        item {
            CustomTopAppBar(
                title = "Расписание тренировок",
                moreInformationClick = {},
                backgroundColor = _F7F8F8,
                textColor = Color.Black
            ) {
                navController.navigate(Route.WorkoutTrackerScreen.route) {
                    popUpTo(Route.WorkoutScheduleScreen.route) {
                        inclusive = true
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
            CustomDateCard(day = state.currentDay) {
                viewModel.onEvent(WorkoutScheduleEvent.MonthClick(it))
            }
            Spacer(Modifier.height(30.dp))
        }

        items(24) {
            LazyRow(
                modifier = Modifier
                    .fillParentMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Text(
                        text = if ((it + 1) < 10) {
                            "0${it + 1}:00"
                        } else {
                            "${it + 1}:00"
                        },
                        style = montserrat40012_B6B4C2
                    )
                    Spacer(Modifier.width(40.dp))
                }
                items(state.workoutSchedule) { work ->
                    AnimatedVisibility(
                        visible = (it + 1) == LocalDateTime.parse(work.time).hour,
                        enter = fadeIn(tween(1000))
                    ) {
                        CustomWorkoutTextCard(
                            text = work.title
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                }
            }
            Spacer(Modifier.height(24.dp))
            Spacer(
                Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(_F7F8F8)
            )
        }
    }

    CustomFloatingActionButton { navController.navigate(Route.AddWorkoutScheduleScreen.route) }

    CustomIndicator(state.showIndicator)
}