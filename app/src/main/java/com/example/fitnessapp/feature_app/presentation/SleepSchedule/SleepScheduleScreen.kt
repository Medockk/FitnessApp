package com.example.fitnessapp.feature_app.presentation.SleepSchedule

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomDateCard
import com.example.fitnessapp.feature_app.presentation.common.CustomFloatingActionButton
import com.example.fitnessapp.feature_app.presentation.common.CustomGreenButton
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomSleepCard
import com.example.fitnessapp.feature_app.presentation.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._9CEEDF
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50010White
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014_228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60016_1D1617
import org.koin.androidx.compose.koinViewModel

@Composable
fun SleepScheduleScreen(
    navController: NavController,
    viewModel: SleepScheduleViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    if (state.exception.isNotEmpty()) {
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(SleepScheduleEvent.ResetException)
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
                title = "График сна",
                moreInformationClick = {},
                backgroundColor = _F7F8F8,
                textColor = Color.Black
            ) {
                navController.navigate(Route.SleepTrackerScreen.route) {
                    popUpTo(Route.SleepScheduleScreen.route) {
                        inclusive = true
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
            Card(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .background(
                        Brush.linearGradient(listOf(_228F7D, _9CEEDF)),
                        RoundedCornerShape(22.dp),
                        0.8f
                    ),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Column {
                        Text(
                            text = "Идеальные часы для сна",
                            style = montserrat40012_1D1617
                        )
                        Spacer(Modifier.height(5.dp))
                        Text(
                            text = "8часов 30минут",
                            style = montserrat50014_228F7D
                        )
                        Spacer(Modifier.height(10.dp))
                        CustomGreenButton(
                            text = "Больше",
                        ) { }
                    }
                    Spacer(Modifier.weight(1f))
                    AsyncImage(
                        model = "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//Moon.png",
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Spacer(Modifier.height(30.dp))
            Text(
                text = "Ваше расписание",
                style = montserrat60016_1D1617
            )
            Spacer(Modifier.height(15.dp))
            CustomDateCard(
                modifier = Modifier
                    .fillParentMaxWidth(),
                day = state.currentDay
            ) { viewModel.onEvent(SleepScheduleEvent.MonthClick(it)) }
            Spacer(Modifier.height(30.dp))
        }

        items(state.sleepData) { sleep ->
            if (state.sleepData.isNotEmpty() && state.sleepData.indexOf(sleep) == 0) {
                CustomSleepCard(
                    sleep = sleep,
                    icon = "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//Icon-Bed.png",
                    sleepEnd = "",
                    modifier = Modifier
                        .fillParentMaxWidth()
                ) {
                    viewModel.onEvent(SleepScheduleEvent.ChangeSleepEnabled(sleep))
                }
                Spacer(Modifier.height(15.dp))
            }
        }

        items(state.alarmClockTracker) { alarm ->
            if (state.alarmClockTracker.isNotEmpty() && state.alarmClockTracker.indexOf(alarm) == 0){
                CustomSleepCard(
                    alarmClockTracker = alarm,
                    icon = "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//Icon-Alaarm.png",
                    alarmEnd = "",
                    modifier = Modifier
                        .fillParentMaxWidth()
                ) {
                    viewModel.onEvent(SleepScheduleEvent.ChangeAlarmEnabled(alarm))
                }
                Spacer(Modifier.height(15.dp))
            }
        }

        item {
            Card(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .background(
                        Brush.linearGradient(listOf(_228F7D, _9CEEDF)),
                        RoundedCornerShape(20.dp),
                        0.8f
                    ),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Сегодня вечером у тебя будет",
                        style = montserrat40012_1D1617
                    )
                    Text(
                        text = state.sleepTime,
                        style = montserrat40012_1D1617
                    )
                    Spacer(Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(99.dp))
                            .background(_F7F8F8, RoundedCornerShape(99.dp))
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth(state.sleepPercent)
                        ) {
                            Text(
                                text = state.sleepPercent.toString().drop(2) + "%",
                                style = montserrat50010White
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height((LocalConfiguration.current.screenHeightDp / 20).dp))
        }
    }

    CustomFloatingActionButton {
        navController.navigate(Route.AddAlarmScreen.route) {
            popUpTo(Route.SleepScheduleScreen.route) {
                inclusive = true
            }
        }
    }
    CustomIndicator(state.showIndicator)
}