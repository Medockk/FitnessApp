package com.example.fitnessapp.feature_app.presentation.Comparison

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertCard
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomDropDownMenu
import com.example.fitnessapp.feature_app.presentation.common.CustomGreenButton
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.ui.theme._1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import org.koin.androidx.compose.koinViewModel

@Suppress("UNCHECKED_CAST")
@Composable
fun ComparisonScreen(
    navController: NavController,
    viewModel: ComparisonViewModel = koinViewModel()
) {
    val state = viewModel.state.value

    val comparisonList = listOf(
        listOf(
            ImageVector.vectorResource(R.drawable.birthday_data_icon),
            "Выбрать первый месяц",
            state.firstMount,
            { it: String -> viewModel.onEvent(ComparisonEvent.EnterFirstMonth(it)) },
            state.showFirstMonthDropDownMenu,
            { viewModel.onEvent(ComparisonEvent.ChangeFirstMonthShowDropdownMenuState) } as () -> Unit
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.birthday_data_icon),
            "Выбрать второй месяц",
            state.secondMount,
            { it: String -> viewModel.onEvent(ComparisonEvent.EnterSecondMonth(it)) },
            state.showSecondMonthDropDownMenu,
            { viewModel.onEvent(ComparisonEvent.ChangeSecondMonthShowDropdownMenuState) } as () -> Unit
        ),
    )

    if (state.exception.isNotEmpty()) {
        CustomAlertDialog(description = state.exception) {
            viewModel.onEvent(ComparisonEvent.ResetException)
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
                title = "Сравнение",
                moreInformationClick = {},
                textColor = _1D1617,
                backgroundColor = _F7F8F8
            ) {
                navController.popBackStack()
            }
            Spacer(Modifier.height(30.dp))
        }

        items(comparisonList) {
            Row {
                CustomAlertCard(
                    icon = it[0] as ImageVector,
                    title = it[1] as String,
                    description = it[2] as String,
                    modifier = Modifier
                        .fillParentMaxWidth(),
                    onClick = it[5] as () -> Unit
                )
                androidx.compose.animation.AnimatedVisibility(
                    visible = it[4] as Boolean,
                    enter = fadeIn(tween(500, easing = LinearOutSlowInEasing))
                ) {
                    CustomDropDownMenu(
                        expanded = it[4] as Boolean,
                        list = state.monthList,
                        onDismissClick = it[5] as () -> Unit
                    ) { string ->
                        (it[3] as (String) -> Unit).invoke(string)
                        (it[5] as () -> Unit).invoke()
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
                start = 30.dp, end = 30.dp,
                bottom = (LocalConfiguration.current.screenHeightDp / 20).dp
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        CustomGreenButton(
            text = "Сравнить",
            modifier = Modifier
                .fillMaxWidth()
        ) {
            viewModel.onEvent(ComparisonEvent.ComparisonClick)
            navController.navigate(Route.CompareResultScreen.route)
        }
    }
}