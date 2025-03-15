package com.example.fitnessapp.feature_app.presentation.SleepTracker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomCanvasBarChart
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomLightGreenCard
import com.example.fitnessapp.feature_app.presentation.common.CustomSleepCard
import com.example.fitnessapp.feature_app.presentation.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.ui.theme._07856E
import com.example.fitnessapp.feature_app.presentation.ui.theme._5DDDC7
import com.example.fitnessapp.feature_app.presentation.ui.theme._81CCBF
import com.example.fitnessapp.feature_app.presentation.ui.theme._A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40010_42D742
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014White
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50016White
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60016_1D1617

@Composable
fun SleepTrackerScreen(
    navController: NavController,
    viewModel: SleepTrackerViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val brush = Brush.linearGradient(listOf(_81CCBF, _07856E))

    if (state.exception.isNotEmpty()) {
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(SleepTrackerEvent.ResetException)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 30.dp)
    ) {
        item {
            CustomTopAppBar(
                title = "Трекер сна",
                moreInformationClick = {},
                backgroundColor = _F7F8F8,
                textColor = Color.Black
            ) {
                navController.popBackStack()
            }
            Spacer(Modifier.height(35.dp))
            CustomCanvasBarChart(
                list = state.barList,
                height = 140.dp,
                textStyle = montserrat40012_B6B4C2,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(Color.Transparent),
                lineColor = _A5A3B0
            )
            Spacer(Modifier.height(15.dp))
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Увеличено на 43% ",
                        style = montserrat40010_42D742,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            Spacer(Modifier.height(15.dp))
            Card(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .background(
                        brush,
                        RoundedCornerShape(22.dp)
                    ),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(Color.Transparent, Color.Unspecified),
                onClick = { navController.navigate(Route.SleepScheduleScreen.route) }
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp)
                ) {
                    Text(
                        text = "Последний сон",
                        style = montserrat50014White
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = state.lastSleep,
                        style = montserrat50016White
                    )
                }
                AsyncImage(
                    model = "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//Sleep-Graph.png",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.height(30.dp))
            CustomLightGreenCard(
                title = "Трафик сна",
                buttonText = "Проверить",
                color = _5DDDC7,
                modifier = Modifier.alpha(0.8f)
            ) {
                navController.navigate(Route.SleepScheduleScreen.route){
                    popUpTo(Route.SleepTrackerScreen.route){
                        inclusive = true
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
            Text(
                text = "Расписание на сегодня",
                style = montserrat60016_1D1617
            )
            Spacer(Modifier.height(15.dp))
        }

        item{
            AnimatedVisibility(
                visible = state.sleepData!=null,
                enter = slideInHorizontally(tween(500, easing = LinearOutSlowInEasing))
            ) {
                CustomSleepCard(
                    sleep = state.sleepData!!,
                    icon = "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//Icon-Bed.png",
                    sleepEnd = state.sleepEnd,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .animateItem(
                            placementSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                visibilityThreshold = IntOffset.VisibilityThreshold
                            ),
                            fadeInSpec = spring(Spring.DampingRatioMediumBouncy),
                            fadeOutSpec = spring(Spring.DampingRatioMediumBouncy),
                        )
                ) {
                    viewModel.onEvent(SleepTrackerEvent.ChangeSleepWorkout(state.sleepData))
                }
            }
            Spacer(Modifier.height(15.dp))
        }

        item {
            AnimatedVisibility(
                visible = state.alarmClockTracker != null,
                enter = slideInHorizontally(tween(500, easing = LinearOutSlowInEasing))
            ) {
                CustomSleepCard(
                    alarmClockTracker = state.alarmClockTracker!!,
                    icon = "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//Icon-Alaarm.png",
                    alarmEnd = state.alarmEnd,
                    modifier = Modifier.fillParentMaxWidth(),
                ) {
                    viewModel.onEvent(SleepTrackerEvent.ChangeAlarmState(state.alarmClockTracker))
                }
            }
            Spacer(Modifier.height((LocalConfiguration.current.screenHeightDp / 20).dp))
        }
    }

    CustomIndicator(state.showIndicator)
}