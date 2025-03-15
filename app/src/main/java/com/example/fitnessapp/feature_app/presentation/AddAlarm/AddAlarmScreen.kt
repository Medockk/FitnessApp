@file:Suppress("UNCHECKED_CAST")

package com.example.fitnessapp.feature_app.presentation.AddAlarm

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertCard
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomDropDownMenu
import com.example.fitnessapp.feature_app.presentation.common.CustomGreenButton
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8

@Composable
fun AddAlarmScreen(
    navController: NavController,
    viewModel: AddAlarmViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val settingCardList = listOf(
        listOf(
            ImageVector.vectorResource(R.drawable.bed_icon),
            "Сон",
            state.sleepTimeStart,
            {

            },
            false
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.clock_icon),
            "Часов для сна",
            state.sleepTimeEnd,
            {},
            false
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.repeat_icon),
            "Повтор",
            state.repeatDays,
            {},
            false
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.vibration_icon),
            "Вибрировать при звуке",
            state.vibrationState,
            {it: Boolean -> viewModel.onEvent(AddAlarmEvent.ChangeVibrationState(it))},
            true,
        ),
    )

    if (state.exception.isNotEmpty()){
        CustomAlertDialog(description = state.exception) {
            viewModel.onEvent(AddAlarmEvent.ResetException)
        }
    }

    CustomIndicator(state.showIndicator)

    LaunchedEffect(key1 = !state.isAdded) {
        if (state.isAdded){
            navController.navigate(Route.SleepScheduleScreen.route){
                popUpTo(Route.AddAlarmScreen.route){
                    inclusive = true
                }
            }
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
                title = "Добавить часы",
                moreInformationClick = {},
                backgroundColor = _F7F8F8,
                textColor = Color.Black
            ) {
                navController.navigate(Route.SleepScheduleScreen.route){
                    popUpTo(Route.AddAlarmScreen.route){
                        inclusive = true
                    }
                }
            }
            Spacer(Modifier.height(30.dp))

        }

        items(settingCardList){
            var isDropDownMenuOpened by remember {  mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .fillParentMaxWidth()
            ) {
                CustomAlertCard(
                    icon = it[0] as ImageVector,
                    title = it[1] as String,
                    description = if (it[4] as Boolean) "" else it[2] as String,
                    isSwitch = it[4] as Boolean,
                    switchState = if (it[4] as Boolean) it[2] as Boolean else false,
                    onSwitchChange = if (it[4] as Boolean) {
                        {switchState-> (it[3] as (Boolean) -> Unit).invoke(switchState)}
                    } else { {} },
                    modifier = Modifier
                        .weight(1f),
                    onClick = {
                        isDropDownMenuOpened = !isDropDownMenuOpened
                    }
                )
                androidx.compose.animation.AnimatedVisibility(
                    visible = isDropDownMenuOpened && settingCardList.indexOf(it)==0,
                    enter = slideInVertically(tween(500, easing = LinearOutSlowInEasing)),
                    exit = shrinkVertically(tween(500, easing = LinearOutSlowInEasing))
                ) {
                    CustomDropDownMenu(
                        list = state.commonSleepTimeStartList,
                        expanded = isDropDownMenuOpened,
                        onDismissClick = {
                            isDropDownMenuOpened = false
                        }
                    ) { string ->
                        viewModel.onEvent(AddAlarmEvent.SetSleepTime(string))
                        isDropDownMenuOpened = !isDropDownMenuOpened
                    }
                }
                androidx.compose.animation.AnimatedVisibility(
                    visible = isDropDownMenuOpened && settingCardList.indexOf(it)==1,
                    enter = slideInVertically(tween(500, easing = LinearOutSlowInEasing)),
                    exit = shrinkVertically(tween(500, easing = LinearOutSlowInEasing))
                ) {
                    CustomDropDownMenu(
                        list = state.commonSleepTimeEndList,
                        expanded = isDropDownMenuOpened,
                        onDismissClick = {
                            isDropDownMenuOpened = false
                        }
                    ) { string ->
                        viewModel.onEvent(AddAlarmEvent.SetAlarmTime(string))
                        isDropDownMenuOpened = !isDropDownMenuOpened
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 30.dp,
                end = 30.dp,
                bottom = (LocalConfiguration.current.screenHeightDp / 20).dp
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        CustomGreenButton(
            text = "Добавить",
            modifier = Modifier
                .fillMaxWidth()
        ) { viewModel.onEvent(AddAlarmEvent.AddAlarmClick) }
    }
}