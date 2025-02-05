package com.example.fitnessapp.feature_app.presentation.ActivityTracker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.common.CustomAlertDialog
import com.example.common.CustomIndicator
import com.example.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.ActivityTracker.components.ActivityBarChart
import com.example.fitnessapp.ui.theme._07856E
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme._81CCBF
import com.example.fitnessapp.ui.theme._83F2A5
import com.example.fitnessapp.ui.theme._9CEEDF
import com.example.fitnessapp.ui.theme._A5A3B0
import com.example.fitnessapp.ui.theme._C4C4C4
import com.example.fitnessapp.ui.theme._F7F8F8
import com.example.fitnessapp.ui.theme.montserrat40010White
import com.example.fitnessapp.ui.theme.montserrat40010_ADA4A5
import com.example.fitnessapp.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.ui.theme.montserrat50012_1D1617
import com.example.fitnessapp.ui.theme.montserrat50012_A5A3B0
import com.example.fitnessapp.ui.theme.montserrat50014_228F7D
import com.example.fitnessapp.ui.theme.montserrat60014_1D1617
import com.example.fitnessapp.ui.theme.montserrat60016_1D1617
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    ActivityTrackerScreen(rememberNavController())
}

@Composable
fun ActivityTrackerScreen(
    navController: NavController,
    viewModel: ActivityTrackerViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 13

    if (state.exception.isNotEmpty()){
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(ActivityTrackerEvent.ResetException)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 30.dp, end = 30.dp,
                bottom = paddingBottom.dp
            )
    ) {
        item {
            CustomTopAppBar(
                title = "Трекер активности",
                moreInformationClick = {},
                backgroundColor = _F7F8F8,
                modifier = Modifier
                    .fillParentMaxWidth()
            ) {
                navController.popBackStack()
            }
            Spacer(Modifier.height(30.dp))
        }

        item {
            Card(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .clip(RoundedCornerShape(22.dp))
                    .background(
                        brush = Brush.linearGradient(listOf(_228F7D, _9CEEDF)),
                        RoundedCornerShape(22.dp),
                        0.3f
                    ),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Сегодняшняя цель",
                            style = montserrat60014_1D1617
                        )
                        Spacer(Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    brush = Brush.linearGradient(listOf(_81CCBF, _07856E)),
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    viewModel.onEvent(ActivityTrackerEvent.AddPurpose)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "add purpose",
                                tint = Color.White,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(15.dp))
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        items(state.currentPurpose) { purpose ->
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillParentMaxWidth(0.47f)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(10.dp)
                                ) {
                                    AsyncImage(
                                        model = purpose.image,
                                        contentDescription = purpose.title,
                                        modifier = Modifier
                                            .size(25.dp, 34.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Column {
                                        Text(
                                            text = purpose.title,
                                            style = montserrat50014_228F7D
                                        )
                                        Text(
                                            text = purpose.description,
                                            style = montserrat40012_B6B4C2
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
        }

        item {
            Column(
                modifier = Modifier
                    .fillParentMaxWidth()
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Прогресс активности",
                        style = montserrat60016_1D1617
                    )
                    Spacer(Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(
                                brush = Brush.linearGradient(listOf(_228F7D, _9CEEDF)),
                                RoundedCornerShape(50.dp)
                            )
                            .clickable {

                            },
                        contentAlignment = Alignment.Center
                    ){
                        Row(
                            modifier = Modifier
                                .padding(7.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Неделя",
                                style = montserrat40010White
                            )
                            Spacer(Modifier.width(5.dp))
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = Color.White,

                            )
                        }
                    }
                }

                Spacer(Modifier.height(15.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        ActivityBarChart(
                            barChartList = state.activityProgress,
                            lineColor = _83F2A5,
                            padding = (LocalConfiguration.current.screenWidthDp/8).dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .height(200.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Последняя активность",
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

        item {
            AnimatedVisibility(
                visible = state.lastActivity.isNotEmpty(),
                enter = slideInVertically(tween(500, easing = LinearOutSlowInEasing))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillParentMaxSize()
                ) {
                    items(state.lastActivity){activity ->
                        Card(
                            modifier = Modifier
                                .fillParentMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(_C4C4C4, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    AsyncImage(
                                        model = activity.image,
                                        contentDescription = activity.title,
                                        modifier = Modifier
                                            .padding(7.dp)
                                            .size(40.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Spacer(Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = activity.title,
                                        style = montserrat50012_1D1617
                                    )
                                    Text(
                                        text = activity.date,
                                        style = montserrat40010_ADA4A5
                                    )
                                }
                                Spacer(Modifier.weight(1f))
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .align(Alignment.Top),
                                    tint = _A5A3B0
                                )
                            }
                        }
                        Spacer(Modifier.height(15.dp))
                    }
                }
            }
        }
    }
    CustomIndicator(state.showIndicator)
}