@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.fitnessapp.feature_app.presentation.AddWorkoutSchedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.CustomAlertDialog
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
    val timeInputState = rememberTimePickerState(
        is24Hour = true,
        initialHour = state.hour.toInt(),
        initialMinute = state.minute.toInt()
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
                backgroundColor = _F7F8F8
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
                TimePicker(
                    state = timeInputState,
                    layoutType = TimePickerLayoutType.Vertical
                )

            }
        }
    }
}