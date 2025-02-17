package com.example.fitnessapp.feature_app.presentation.MealDetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.common.CustomAlertDialog
import com.example.common.CustomGreenButton
import com.example.common.CustomTopAppBar
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.presentation.MealDetail.components.CustomIngredientCard
import com.example.fitnessapp.feature_app.presentation.MealDetail.components.CustomNutritionCard
import com.example.fitnessapp.feature_app.presentation.MealDetail.components.CustomReceipt
import com.example.fitnessapp.ui.theme._07856E
import com.example.fitnessapp.ui.theme._1D1617
import com.example.fitnessapp.ui.theme._81CCBF
import com.example.fitnessapp.ui.theme._F7F8F8
import com.example.fitnessapp.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.ui.theme.montserrat40012_C6C4D4
import com.example.fitnessapp.ui.theme.montserrat60016_1D1617
import com.example.fitnessapp.ui.theme.montserrat70016_1D1617
import org.koin.androidx.compose.koinViewModel

@Composable
fun MealDetailScreen(
    navController: NavController,
    meal: DietaryRecommendation,
    viewModel: MealDetailsViewModel = koinViewModel()
) {
    val state = viewModel.state.value

    if (state.exception.isNotEmpty()){
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(MealDetailsEvent.ResetException)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(_81CCBF, _07856E)))
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTopAppBar(
                    title = "",
                    moreInformationClick = {},
                    backgroundColor = _F7F8F8,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textColor = Color.Transparent
                ) {
                    navController.popBackStack()
                }
                Spacer(Modifier.height(40.dp))
                AsyncImage(
                    model = meal.image,
                    contentDescription = "meal image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        item {
            LazyColumn(
                modifier = Modifier
                    .fillParentMaxSize()
                    .background(Color.White, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .padding(horizontal = 30.dp),
            ) {
                item {
                    Spacer(Modifier.height(10.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillParentMaxWidth()
                    ) {
                        Box(
                            Modifier
                                .size(50.dp, 5.dp)
                                .background(
                                    _1D1617,
                                    RoundedCornerShape(50.dp)
                                )
                                .alpha(0.1f)
                        )
                    }
                }
                item {
                    Spacer(Modifier.height(15.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = meal.title,
                                style = montserrat70016_1D1617
                            )
                            Spacer(Modifier.height(5.dp))
                            Text(
                                text = meal.author,
                                style = montserrat40012_C6C4D4
                            )
                        }
                        Spacer(Modifier.weight(1f))
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.heart_icon),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                    Spacer(Modifier.height(30.dp))
                    Text(
                        text = "Питание",
                        style = montserrat60016_1D1617
                    )
                    Spacer(Modifier.height(20.dp))
                }

                item {
                    AnimatedVisibility(
                        visible = state.nutrition.isNotEmpty(),
                        enter = slideInHorizontally(tween(500, easing = LinearOutSlowInEasing)) +
                            fadeIn()
                    ) {
                        LazyRow(
                            modifier = Modifier
                                .fillParentMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,

                        ) {
                            items(state.nutrition){ nutrition ->
                                CustomNutritionCard(
                                    title = nutrition,
                                    icon = when (nutrition){
                                        meal.calories -> ImageVector.vectorResource(R.drawable.calories_icon)
                                        meal.fat -> ImageVector.vectorResource(R.drawable.fat_icon)
                                        meal.protein -> ImageVector.vectorResource(R.drawable.proteins_icon)
                                        else -> ImageVector.vectorResource(R.drawable.carbo_icon)
                                    }
                                )
                                Spacer(Modifier.width(15.dp))
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = "Описание",
                        style = montserrat60016_1D1617
                    )
                    Spacer(Modifier.height(15.dp))
                    Text(
                        text = meal.details,
                        style = montserrat40012_B6B4C2
                    )
                    Spacer(Modifier.height(30.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Ингредиенты, которые\nвам понадобятся",
                            style = montserrat60016_1D1617
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "${state.ingredientCount} продуктов",
                            style = montserrat40012_B6B4C2
                        )
                    }
                    Spacer(Modifier.height(20.dp))
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(state.ingredients){ ingredient ->
                            CustomIngredientCard(
                                ingredient = ingredient,
                                icon = when (ingredient.trim()[0].toString() + ingredient.trim()[1].toString() + ingredient.trim()[2].toString()){
                                    "Мук" -> ImageVector.vectorResource(R.drawable.flour_icon)
                                    "Сах" -> ImageVector.vectorResource(R.drawable.sugar_icon)
                                    "Сод" -> ImageVector.vectorResource(R.drawable.baking_soda_icon)
                                    "Яйц" -> ImageVector.vectorResource(R.drawable.eggs_icon)
                                    else ->ImageVector.vectorResource(R.drawable.carbo_icon)
                                },
                                ingredientCount = ingredient.dropWhile { it.toString() != "(" },
                            )
                            Spacer(Modifier.width(15.dp))
                        }
                    }
                    Spacer(Modifier.height(30.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Шаги",
                            style = montserrat60016_1D1617
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = state.details.stepSize + " шагов",
                            style = montserrat40012_B6B4C2
                        )
                    }
                    Spacer(Modifier.height(20.dp))
                }

                items(state.receipt.size) { step ->
                    CustomReceipt(
                        step = step+1,
                        text = state.receipt[step],
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(Modifier.height(10.dp))
                }

                item {
                    CustomGreenButton(
                        text = "Добавить к завтраку",
                        modifier = Modifier.fillMaxWidth()
                            .padding(
                                bottom = (LocalConfiguration.current.screenHeightDp / 20).dp
                            )
                    ) {
                        viewModel.onEvent(MealDetailsEvent.AddToBreakfast(meal))
                    }
                }
            }
        }
    }
}