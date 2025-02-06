package com.example.fitnessapp.feature_app.presentation.WorkoutDetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.common.CustomGreenButton
import com.example.common.CustomIndicator
import com.example.common.CustomTopAppBar
import com.example.fitnessapp.R
import com.example.fitnessapp.Route
import com.example.fitnessapp.feature_app.domain.model.WorkoutData
import com.example.fitnessapp.feature_app.presentation.WorkoutDetail.components.CustomSprintCard
import com.example.fitnessapp.feature_app.presentation.WorkoutDetail.components.CustomWorkoutInfo
import com.example.fitnessapp.ui.theme._00F0FF
import com.example.fitnessapp.ui.theme._00FF66
import com.example.fitnessapp.ui.theme._07856E
import com.example.fitnessapp.ui.theme._1D1617
import com.example.fitnessapp.ui.theme._81CCBF
import com.example.fitnessapp.ui.theme._F7F8F8
import com.example.fitnessapp.ui.theme.montserrat40012Black
import com.example.fitnessapp.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.ui.theme.montserrat50012_1D1617
import com.example.fitnessapp.ui.theme.montserrat50012_A5A3B0
import com.example.fitnessapp.ui.theme.montserrat60016_1D1617
import com.example.fitnessapp.ui.theme.montserrat70016_1D1617
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    WorkoutDetailScreen(
        rememberNavController(),
        WorkoutData(0, "", "", "", "")
    )
}

@Composable
fun WorkoutDetailScreen(
    navController: NavController,
    workoutData: WorkoutData,
    viewModel: WorkoutDetailViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val backgroundBrush = Brush.linearGradient(
        listOf(
            _81CCBF, _07856E
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                CustomTopAppBar(
                    title = "",
                    backgroundColor = _F7F8F8,
                    moreInformationClick = {},
                    modifier = Modifier
                        .fillMaxWidth(),
                    textColor = Color.Transparent
                ) {
                    navController.popBackStack()
                }

                AsyncImage(
                    model = workoutData.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

            }
        }

        item {
            Column(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(10.dp))
                Box(
                    Modifier
                        .size(50.dp, 5.dp)
                        .background(_1D1617, RoundedCornerShape(50.dp))
                        .alpha(0.1f)
                )
                Spacer(Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = workoutData.title,
                            style = montserrat70016_1D1617
                        )
                        Spacer(Modifier.height(5.dp))
                        Text(
                            text = workoutData.workoutCount,
                            style = montserrat40012_B6B4C2
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        onClick = {},
                        colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White),
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White, RoundedCornerShape(8.dp))
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.heart_icon),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                CustomWorkoutInfo(
                    backgroundBrush = Brush.linearGradient(listOf(_00F0FF, _00FF66)),
                    icon = ImageVector.vectorResource(R.drawable.birthday_data_icon),
                    title = "Время тренировки",
                    description = LocalDateTime.parse(
                        Clock.System.now().toString().dropLast(1)
                    ).date.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                ) { }

                Spacer(Modifier.height(15.dp))


                CustomWorkoutInfo(
                    backgroundBrush = Brush.linearGradient(listOf(_81CCBF, _07856E)),
                    icon = ImageVector.vectorResource(R.drawable.height_icon),
                    title = "Сложность",
                    description = "Начинающий",
                    modifier = Modifier
                        .fillMaxWidth()
                ) { }

                Spacer(Modifier.height(30.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Вам понадобится",
                        style = montserrat60016_1D1617
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "${workoutData.itemCount} предметов",
                        style = montserrat50012_A5A3B0
                    )
                }
                Spacer(Modifier.height(15.dp))

                AnimatedVisibility(
                    visible = workoutData.itemCount.isNotEmpty()
                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items(workoutData.itemCount.toInt()) { item ->
                            Column {
                                Card(
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = _F7F8F8),
                                ) {
                                    AsyncImage(
                                        model = "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//sportItem${item + 1}.png",
                                        contentDescription = "item for workout",
                                        modifier = Modifier
                                            .padding(25.dp)
                                            .size(60.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Spacer(Modifier.height(10.dp))
                                Text(
                                    text = when (item) {
                                        0 -> "Гантели"
                                        1 -> "Скакалка"
                                        2 -> "Бутылка воды"
                                        else -> "Спортивный коврик"
                                    },
                                    style = montserrat40012Black
                                )
                            }
                            Spacer(Modifier.width(15.dp))
                        }
                    }
                }
                Spacer(Modifier.height(30.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Упражнения",
                        style = montserrat60016_1D1617
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "2 подхода",
                        style = montserrat50012_A5A3B0
                    )
                }
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Подход 1",
                    style = montserrat50012_1D1617,
                    modifier = Modifier
                        .align(Alignment.Start)
                )
                Spacer(Modifier.height(15.dp))
            }
        }

        items(state.sprint1) { sprint ->
            AnimatedVisibility(
                visible = state.sprint1.isNotEmpty(),
                enter = slideInVertically(tween(500)),
                modifier = Modifier
                    .fillParentMaxWidth()
                    .background(Color.White)
            ) {
                CustomSprintCard(
                    workoutSprint = sprint,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(horizontal = 30.dp)
                ) {
                    Route.StartWorkoutScreen.workout = sprint.title
                    navController.navigate(Route.StartWorkoutScreen.route)
                }
            }
            Spacer(Modifier.height(15.dp).fillParentMaxWidth().background(Color.White))
        }

        item {
            Text(
                text = "Подход 2",
                style = montserrat50012_1D1617,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 30.dp)
            )
            Spacer(Modifier.height(15.dp).fillParentMaxWidth().background(Color.White))
        }

        items(state.sprint2) { workoutSprint ->
            AnimatedVisibility(
                visible = state.sprint2.isNotEmpty(),
                enter = slideInVertically(tween(500)),
                modifier = Modifier
                    .fillParentMaxWidth()
                    .background(Color.White)
            ) {
                CustomSprintCard(
                    workoutSprint = workoutSprint,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(horizontal = 30.dp)
                ) {
                    Route.StartWorkoutScreen.workout = workoutSprint.title
                    navController.navigate(Route.StartWorkoutScreen.route)
                }
            }
            Spacer(Modifier.height(15.dp).fillParentMaxWidth().background(Color.White))
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = (LocalConfiguration.current.screenHeightDp / 30).dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        CustomGreenButton(
            text = "Начать",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) { }
    }

    CustomIndicator(state.showIndicator)
}