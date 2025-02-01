package com.example.fitnessapp.feature_app.presentation.MealSchedule

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.CustomAlertDialog
import com.example.common.CustomDateCard
import com.example.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.MealSchedule.components.CustomProductMassCard
import com.example.fitnessapp.feature_app.presentation.MealSchedule.components.MealCard
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme._F7F8F8
import com.example.fitnessapp.ui.theme.montserrat60016_1D1617
import org.koin.androidx.compose.koinViewModel

@Composable
fun MealScheduleScreen(
    navController: NavController,
    viewModel: MealScheduleViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    if (state.exception.isNotEmpty()){
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(MealScheduleEvent.ResetException)
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
                title = "График питания",
                moreInformationClick = {},
                backgroundColor = _F7F8F8
            ) {
                navController.popBackStack()
            }
            Spacer(Modifier.height(30.dp))
            CustomDateCard({}) { }
            Spacer(Modifier.height(30.dp))

            if (state.breakfastMeal.isNotEmpty()){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Завтрак",
                        style = montserrat60016_1D1617
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "${state.breakfastMeal.size} блюда | ${state.breakfastCalories} кКал"
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
        }

        items(state.breakfastMeal){meal ->
            MealCard(
                mealScheduleItem = meal,
                modifier = Modifier
                    .fillMaxWidth()
            ) { }
            Spacer(Modifier.height(10.dp))
        }

        item {
            if (state.launchMeal.isNotEmpty()){
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Обед",
                        style = montserrat60016_1D1617
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "${state.launchMeal.size} блюда | ${state.lunchCalories} кКал"
                    )
                }
            }
        }

        items(state.launchMeal){meal ->
            MealCard(
                mealScheduleItem = meal,
                modifier = Modifier
                    .fillMaxWidth()
            ) { }
            Spacer(Modifier.height(10.dp))
        }
        item {
            if (state.afternoonMeal.isNotEmpty()){
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Полдник",
                        style = montserrat60016_1D1617
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "${state.afternoonMeal.size} блюда | ${state.afternoonCalories} кКал"
                    )
                }
            }
        }

        items(state.afternoonMeal){meal ->
            MealCard(
                mealScheduleItem = meal,
                modifier = Modifier
                    .fillMaxWidth()
            ) { }
            Spacer(Modifier.height(10.dp))
        }
        item {
            if (state.dinnerMeal.isNotEmpty()){
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Ужин",
                        style = montserrat60016_1D1617
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "${state.dinnerMeal.size} блюда | ${state.dinnerCalories} кКал"
                    )
                }
            }
        }

        items(state.dinnerMeal){meal ->
            MealCard(
                mealScheduleItem = meal,
                modifier = Modifier
                    .fillMaxWidth()
            ) { }
            Spacer(Modifier.height(10.dp))
        }

        item{
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Питание сегодня",
                style = montserrat60016_1D1617
            )
            Spacer(Modifier.height(15.dp))
        }

        items(4){ item ->
            AnimatedVisibility(
                visible = state.dietaryRecommendation.isNotEmpty(),
                enter = slideInHorizontally(tween(500, easing = LinearOutSlowInEasing))
            ) {
                CustomProductMassCard(
                    item = item,
                    value = when (item){
                        0 -> state.caloriesSum.toString()
                        1 -> state.proteinSum.toString()
                        2 -> state.fatSum.toString()
                        else -> state.carboSum.toString()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(Modifier.height(15.dp))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 40.dp, end = 303.dp
            ),
        contentAlignment = Alignment.BottomEnd
    ){
        FloatingActionButton(
            {
                navController.popBackStack()
            },
            shape = CircleShape,
            containerColor = _228F7D,
            elevation = FloatingActionButtonDefaults.elevation(0.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                tint = Color.White,
                contentDescription = "add"
            )
        }
    }
}