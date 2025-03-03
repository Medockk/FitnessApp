package com.example.fitnessapp.feature_app.presentation.CategoryBreakfast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomTopAppBar
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.CategoryBreakfast.components.CategoryCard
import com.example.fitnessapp.feature_app.presentation.CategoryBreakfast.components.DietaryCard
import com.example.fitnessapp.feature_app.presentation.CategoryBreakfast.components.PopularMealCard
import com.example.fitnessapp.feature_app.presentation.ui.theme._A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme._C6C4D4
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_C6C4D4
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60016Bold_1D1617
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategoryBreakfastScreen(
    navController: NavController,
    viewModel: CategoryBreakfastViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    if (state.exception.isNotEmpty()){
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(CategoryBreakfastEvent.ResetException)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 30.dp)
    ) {
        item{
            CustomTopAppBar(
                title = "Завтрак",
                moreInformationClick = {
                    navController.navigate(Route.MealScheduleScreen.route)
                },
                backgroundColor = _F7F8F8,
                textColor = Color.Black
            ) {
                navController.popBackStack()
            }
            Spacer(Modifier.height(30.dp))
            Card(
                modifier = Modifier
                    .fillParentMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                TextField(
                    value = state.searchText,
                    onValueChange = {
                        viewModel.onEvent(CategoryBreakfastEvent.EnterSearchText(it))
                    },
                    modifier = Modifier
                        .fillParentMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Поиск",
                            style = montserrat40012_C6C4D4
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search",
                            tint = _A5A3B0
                        )
                    },
                    trailingIcon = {
                        Row {
                            VerticalDivider(
                                color = _C6C4D4
                            )
                            Spacer(Modifier.width(10.dp))
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.search_filter_icon),
                                contentDescription = "search filter",
                                tint = Color.Unspecified
                            )
                        }
                    }
                )
            }
            Spacer(Modifier.height(30.dp))
            Text(
                text = "Категории",
                style = montserrat60016Bold_1D1617
            )
            Spacer(Modifier.height(15.dp))
        }

        item {
            AnimatedVisibility(
                visible = state.categories.isNotEmpty(),
                enter = slideInHorizontally(tween(500, easing = LinearOutSlowInEasing)) +
                        fadeIn(tween(500))
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillParentMaxWidth(),
                ) {
                    items(state.categories){ category ->
                        CategoryCard(
                            category = category,
                        ) { }
                        Spacer(Modifier.width(15.dp))
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
            Text(
                text = "Рекомендации по диете",
                style = montserrat60016Bold_1D1617
            )
            Spacer(Modifier.height(35.dp))
            AnimatedVisibility(
                visible = state.dietaryRecommendations.isNotEmpty(),
                enter = slideInHorizontally(tween(500, easing = LinearOutSlowInEasing)) +
                        fadeIn(tween(500))
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillParentMaxWidth()
                ) {
                    items(state.dietaryRecommendations){dietary ->
                        DietaryCard(
                            dietary = dietary
                        ) {
                            Route.MealDetailScreen.meal = dietary
                            navController.navigate(Route.MealDetailScreen.route)
                        }
                        Spacer(Modifier.width(15.dp))
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
            Text(
                text = "Популярные",
                style = montserrat60016Bold_1D1617
            )
            Spacer(Modifier.height(15.dp))
        }

        items(state.popularMeal){popularMeal ->
            AnimatedVisibility(
                visible = state.popularMeal.isNotEmpty(),
                enter = slideInHorizontally(tween(500, easing = LinearOutSlowInEasing)) +
                        fadeIn(tween(500))
            ) {
                PopularMealCard(
                    popularMeal = popularMeal,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Route.MealDetailScreen.meal = popularMeal
                    navController.navigate(Route.MealDetailScreen.route)
                }
            }
            Spacer(Modifier.height(15.dp))
        }
    }

    CustomIndicator(state.showIndicator)
}