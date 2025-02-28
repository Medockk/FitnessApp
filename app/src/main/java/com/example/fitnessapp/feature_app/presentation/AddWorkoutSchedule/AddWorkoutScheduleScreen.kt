@file:Suppress("UNCHECKED_CAST")

package com.example.fitnessapp.feature_app.presentation.AddWorkoutSchedule

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.CustomAlertCard
import com.example.common.CustomAlertDialog
import com.example.common.CustomDropDownMenu
import com.example.common.CustomGreenButton
import com.example.common.CustomIndicator
import com.example.common.CustomTopAppBar
import com.example.fitnessapp.R
import com.example.fitnessapp.Route
import com.example.fitnessapp.ui.theme._B6B4C2
import com.example.fitnessapp.ui.theme._F7F8F8
import com.example.fitnessapp.ui.theme.montserrat40014_B6B4C2
import com.example.fitnessapp.ui.theme.montserrat50014_1D1617
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddWorkoutScheduleScreen(
    navController: NavController,
    viewModel: AddWorkoutScheduleViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    val workoutDetailList = listOf(
        listOf(
            ImageVector.vectorResource(R.drawable.dumbbells_icon),
            "Трениовка",
            state.title,
            listOf("Тренировка вверхней части","Тренировка нижней части","Тренировка пресса")
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.height_icon),
            "Сложность",
            "Начинающй",
            listOf("")
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.custom_repeats_icon),
            "Пользовательские повторы",
            "",
            listOf("")
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.custom_repeats_icon),
            "Пользовательские веса",
            "",
            listOf("")
        ),
    )

    if (state.exception.isNotEmpty()){
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(AddWorkoutScheduleEvent.ResetException)
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
                title = "Добавить расписание",
                moreInformationClick = {},
                backgroundColor = _F7F8F8,
                textColor = Color.Black
            ) {
                navController.navigate(Route.WorkoutScheduleScreen.route){
                    popUpTo(Route.AddWorkoutScheduleScreen.route){
                        inclusive = true
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.birthday_data_icon),
                    contentDescription = "current data",
                    tint = _B6B4C2
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "${state.currentDayName}.,${state.currentData} ${state.monthName} ${state.year}",
                    style = montserrat40014_B6B4C2
                )
            }
            Spacer(Modifier.height(30.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Время",
                    style = montserrat50014_1D1617
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                     modifier = Modifier
                         .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "3",
                    )
                    Text(
                        text = "00",
                    )
                }

            }
            Spacer(Modifier.height(30.dp))
            Text(
                text = "Детали тренировки",
                style = montserrat50014_1D1617
            )
            Spacer(Modifier.height(10.dp))
        }

        items(workoutDetailList) { list ->
            var isDropDownMenuOpen by remember { mutableStateOf(false) }
            Column {
                Row {
                    CustomAlertCard(
                        icon = list[0] as ImageVector,
                        title = list[1] as String,
                        description = list[2] as String,
                        onClick = {isDropDownMenuOpen = !isDropDownMenuOpen},
                        modifier = Modifier
                            .weight(1f)
                    )
                    androidx.compose.animation.AnimatedVisibility(
                        visible = isDropDownMenuOpen,
                        enter = slideInVertically(tween(500, easing = LinearOutSlowInEasing))
                    ) {
                        CustomDropDownMenu(
                            list = list[3] as List<String>,
                            expanded = true,
                            onDismissClick = {
                                isDropDownMenuOpen = false
                            },
                            modifier = Modifier
                        ) { string ->
                            viewModel.onEvent(AddWorkoutScheduleEvent.SetWorkoutTitle(string))
                            isDropDownMenuOpen = false
                        }
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp, bottom = (LocalConfiguration.current.screenHeightDp / 20).dp),
        contentAlignment = Alignment.BottomCenter
    ){
        CustomGreenButton(
            text = "Сохранить",
            modifier = Modifier
                .fillMaxWidth()
        ) {
            viewModel.onEvent(AddWorkoutScheduleEvent.AddWorkout)
        }
    }

    CustomIndicator(state.showIndicator)
}