package com.example.fitnessapp.feature_app.presentation.Home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import coil.compose.AsyncImage
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.presentation.Home.components.CustomCard
import com.example.fitnessapp.feature_app.presentation.Home.components.ImtDiagram
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.common.BarChart
import com.example.fitnessapp.feature_app.presentation.common.BottomBar
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomGreenButton
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomLightGreenCard
import com.example.fitnessapp.feature_app.presentation.ui.theme._07856E
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._81CCBF
import com.example.fitnessapp.feature_app.presentation.ui.theme._9CEEDF
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40010_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012White
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat4008_A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50012_1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60013White
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60014_07856E
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60014_228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60016_1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat70020Bold_1D1617

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value

    val imtBrush = Brush.linearGradient(
        listOf(
            _81CCBF,
            _07856E
        )
    )

    if (state.exception.isNotEmpty()) {
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(HomeEvent.ResetException)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        bottomBar = {
            BottomBar(
                currentPage = Route.HomeScreen,
                navController = navController
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(
                    start = 30.dp,
                    end = 30.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Добро пожаловать,",
                            style = montserrat40012_A5A3B0
                        )
                        Spacer(Modifier.height(5.dp))
                        AnimatedVisibility(
                            visible = state.userData != null && state.userData.fio.isNotEmpty(),
                            label = "animated user fio",
                            enter = slideInVertically(tween(500, easing = LinearOutSlowInEasing))
                        ) {
                            Text(
                                text = state.userData!!.fio,
                                style = montserrat70020Bold_1D1617
                            )
                        }
                    }
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            navController.navigate(Route.NotificationScreen.route)
                        },
                        colors = IconButtonDefaults.iconButtonColors(containerColor = _F7F8F8),
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(_F7F8F8, RoundedCornerShape(8.dp))
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.notification_icon),
                            contentDescription = "notification",
                            tint = Color.Unspecified
                        )
                    }
                }
                Spacer(Modifier.height(30.dp))
            }

            item {
                Card(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .background(imtBrush, RoundedCornerShape(22.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(22.dp),
                    onClick = { navController.navigate(Route.CategoryBreakfastScreen.route) }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Column {
                            Text(
                                text = "ИМТ (индекс массы тела)",
                                style = montserrat60013White
                            )
                            Spacer(Modifier.height(5.dp))
                            Text(
                                text = state.bodyMassComments,
                                style = montserrat40012White
                            )
                            Spacer(Modifier.height(20.dp))
                            CustomGreenButton(
                                text = "Больше"
                            ) { }
                        }
                        ImtDiagram(
                            pieChartData = PieChartData(
                                slices = listOf(
                                    PieChartData.Slice(
                                        label = state.bodyMassIndex.toString(),
                                        value = state.bodyMassIndex,
                                        color = _228F7D
                                    ),
                                    PieChartData.Slice(
                                        label = "",
                                        value = 100f - state.bodyMassIndex,
                                        color = Color.White
                                    ),
                                ),
                                plotType = PlotType.Pie
                            ),
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.Transparent, CircleShape),
                            pieChartConfig = PieChartConfig(
                                isAnimationEnable = true,
                                strokeWidth = 0f
                            )
                        )
                    }
                }
                Spacer(Modifier.height(25.dp))
            }

            item {
                CustomLightGreenCard(
                    title = "Сегодняшняя цель",
                    buttonText = "Проверить",
                    modifier = Modifier
                        .fillParentMaxWidth()
                ) {
                    navController.navigate(Route.ActivityTrackerScreen.route)
                }
                Spacer(Modifier.height(30.dp))
            }

            item {
                Text(
                    text = "Статус активности",
                    style = montserrat60016_1D1617,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                Spacer(Modifier.height(10.dp))
            }

            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .background(
                            imtBrush,
                            RoundedCornerShape(20.dp),
                            0.8f
                        ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Частота сердечных сокращений",
                            style = montserrat50012_1D1617
                        )
                        Spacer(Modifier.height(5.dp))
                        androidx.compose.animation.AnimatedVisibility(
                            visible = state.barChartList.isNotEmpty(),
                            enter = slideInHorizontally(tween(500, easing = LinearOutSlowInEasing))
                        ) {
                            BarChart(
                                barChartList = state.barChartList,
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .height(120.dp)
                                    .padding(end = 50.dp),
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = state.barChartList.average().toString()[0].toString() + ", " +
                                    state.barChartList.average().toString()[2].toString() +
                                    " BPM",
                            style = montserrat60014_07856E,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
                Spacer(Modifier.height(20.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .fillParentMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomCard(
                        modifier = Modifier
                            .fillParentMaxWidth(0.48f)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(20.dp)
                        ) {
                            Box(Modifier
                                .size(20.dp, 150.dp)
                                .background(_F7F8F8, RoundedCornerShape(30.dp)))
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Вода",
                                    style = montserrat50012_1D1617
                                )
                                Spacer(Modifier.height(5.dp))
                                Text(
                                    text = if (state.userStatistics.isNotEmpty()) {
                                        state.userStatistics[0].description
                                    }else{
                                        "4л"
                                    },
                                    style = montserrat60014_228F7D
                                )
                                Spacer(Modifier.height(10.dp))
                                Text(
                                    text = "В реал. времени",
                                    style = montserrat40010_B6B4C2
                                )
                                Spacer(Modifier.height(5.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(Modifier
                                        .size(6.dp)
                                        .background(
                                            Brush.linearGradient(
                                                listOf(
                                                    _228F7D, _9CEEDF
                                                )
                                            ), CircleShape, 0.5f
                                        ))
                                    Spacer(Modifier.width(5.dp))
                                    Text(
                                        text = "6:00 - 8:00",
                                        style = montserrat4008_A5A3B0
                                    )
                                }
                            }
                        }
                    }
                    CustomCard(
                        modifier = Modifier
                            .fillParentMaxWidth(0.48f),
                        onClick = { navController.navigate(Route.SleepTrackerScreen.route) }
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(20.dp)
                        ) {
                            Text(
                                text = "Сон",
                                style = montserrat50012_1D1617
                            )
                            Spacer(Modifier.height(5.dp))
                            Text(
                                text = if (state.userStatistics.size >= 2){state.userStatistics[1].description}else{"8 ч. 20 мин."},
                                style = montserrat60014_228F7D
                            )
                            Spacer(Modifier.height(5.dp))
                            AsyncImage(
                                model = "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//Sleep-Graph%20(1).png",
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
    }

    CustomIndicator(state.showIndicator)
}