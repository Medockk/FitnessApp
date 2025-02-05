package com.example.fitnessapp.feature_app.presentation.SleepSchedule

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.common.CustomAlertDialog
import com.example.common.CustomDateCard
import com.example.common.CustomFloatingActionButton
import com.example.common.CustomGreenButton
import com.example.common.CustomIndicator
import com.example.common.CustomSleepCard
import com.example.common.CustomTopAppBar
import com.example.fitnessapp.Route
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme._9CEEDF
import com.example.fitnessapp.ui.theme._F7F8F8
import com.example.fitnessapp.ui.theme.montserrat40012_1D1617
import com.example.fitnessapp.ui.theme.montserrat50010White
import com.example.fitnessapp.ui.theme.montserrat50014_228F7D
import com.example.fitnessapp.ui.theme.montserrat60016_1D1617
import org.koin.androidx.compose.koinViewModel

@Composable
fun SleepScheduleScreen(
    navController: NavController,
    viewModel: SleepScheduleViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    if (state.exception.isNotEmpty()){
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(SleepScheduleEvent.ResetException)
        }
    }

    CustomIndicator(state.showIndicator)

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
                backgroundColor = _F7F8F8
            ) {
                navController.popBackStack()
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
                {},
                modifier = Modifier
                    .fillParentMaxWidth()
            ) { }
            Spacer(Modifier.height(30.dp))
        }

        items(state.sleepData){sleep ->
            CustomSleepCard(
                sleep = sleep,
                sleepEnd = "",
                modifier = Modifier
                    .fillParentMaxWidth()
            ) {
                viewModel.onEvent(SleepScheduleEvent.ChangeEnabled(sleep))
            }
            Spacer(Modifier.height(15.dp))
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
        }
    }

    CustomFloatingActionButton { navController.navigate(Route.AddAlarmScreen.route) }


}