package com.example.fitnessapp.feature_app.presentation.Comparison

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import com.example.common.CustomAlertCard
import com.example.common.CustomAlertDialog
import com.example.common.CustomGreenButton
import com.example.common.CustomIndicator
import com.example.common.CustomTopAppBar
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.theme._1D1617
import com.example.fitnessapp.ui.theme._F7F8F8
import org.koin.androidx.compose.koinViewModel

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
            {}
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.birthday_data_icon),
            "Выбрать второй месяц",
            state.secondMount,
            {}
        ),
    )

    if (state.exception.isNotEmpty()){
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

        items(comparisonList){
            CustomAlertCard(
                icon = it[0] as ImageVector,
                title = it[1] as String,
                description = it[2] as String,
                modifier = Modifier
                    .fillParentMaxWidth(),
                onClick = {it[3]}
            )
            Spacer(Modifier.height(10.dp))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 30.dp, end = 30.dp,
                bottom = (LocalConfiguration.current.screenHeightDp/20).dp
            ),
        contentAlignment = Alignment.BottomCenter
    ){
        CustomGreenButton(
            text = "Сравнить",
            modifier = Modifier
                .fillMaxWidth()
        ) {
           viewModel.onEvent(ComparisonEvent.ComparisonClick)
        }
    }
}