@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.fitnessapp.feature_app.presentation.CompareResult

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomCanvasBarChart
import com.example.fitnessapp.feature_app.presentation.common.CustomGreenButton
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomPhotoCard
import com.example.fitnessapp.feature_app.presentation.CompareResult.compontns.CustomStatisticBar
import com.example.fitnessapp.feature_app.presentation.ui.theme._1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._9CEEDF
import com.example.fitnessapp.feature_app.presentation.ui.theme._A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme._A8E3D9
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40010_42D742
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40016_A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50012White
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50012_42D742
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50016White
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60016_1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60016_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat70016_1D1617
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CompareResultScreen(
    navController: NavController,
    viewModel: CompareResultViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val pagerState = rememberPagerState { 2 }
    val coroutineScope = rememberCoroutineScope()

    if (state.exception.isNotEmpty()){
        CustomAlertDialog(description = state.exception) {
            viewModel.onEvent(CompareResultEvent.ResetException)
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
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                title = {
                    Text(
                        text = "Результат",
                        style = montserrat70016_1D1617,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {navController.popBackStack()},
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .size(32.dp)
                            .background(_F7F8F8, RoundedCornerShape(8.dp))
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                            contentDescription = "back",
                            tint = _1D1617
                        )
                    }
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .size(32.dp)
                                .background(_F7F8F8, RoundedCornerShape(8.dp))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "back",
                                tint = _1D1617
                            )
                        }
                        Spacer(Modifier.width(15.dp))
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .size(32.dp)
                                .background(_F7F8F8, RoundedCornerShape(8.dp))
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                modifier = Modifier
                                    .rotate(90f),
                                contentDescription = "back",
                                tint = _1D1617
                            )
                        }
                    }
                }
            )
            Spacer(Modifier.height(30.dp))
        }

        item {
            Card(
                modifier = Modifier
                    .fillParentMaxWidth(),
                shape = RoundedCornerShape(99.dp),
                colors = CardDefaults.cardColors(_F7F8F8)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(
                                    page = 0,
                                    animationSpec = tween(500, easing = LinearOutSlowInEasing)
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .clip(RoundedCornerShape(99.dp))
                            .background(
                                brush = if (pagerState.currentPage == 0){
                                    Brush.linearGradient(listOf(_228F7D, _9CEEDF))
                                }else{
                                    Brush.linearGradient(listOf(Color.Transparent,Color.Transparent))
                                },
                                RoundedCornerShape(99.dp)
                            ),
                        colors = IconButtonDefaults.iconButtonColors(Color.Transparent)
                    ) {
                        Text(
                            text = "Фото",
                            style = if (pagerState.currentPage==0) montserrat50016White else montserrat40016_A5A3B0
                        )
                    }
                    Spacer(Modifier.width(15.dp))
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(
                                    page = 1,
                                    animationSpec = tween(500, easing = LinearOutSlowInEasing)
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clip(RoundedCornerShape(99.dp))
                            .background(
                                brush = if (pagerState.currentPage == 1){
                                    Brush.linearGradient(listOf(_228F7D, _9CEEDF))
                                }else{
                                    Brush.linearGradient(listOf(Color.Transparent,Color.Transparent))
                                }
                            ),
                        colors = IconButtonDefaults.iconButtonColors(Color.Transparent)
                    ) {
                        Text(
                            text = "Статистика",
                            style = if (pagerState.currentPage==1) montserrat50016White else montserrat40016_A5A3B0
                        )
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
        }

        item {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillParentMaxSize(),
                pageSpacing = 30.dp,
                flingBehavior = PagerDefaults.flingBehavior(
                    state = pagerState,
                    snapAnimationSpec = tween(700, easing = LinearOutSlowInEasing)
                )
            ) {page ->
                LazyColumn(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .background(Color.White)
                ) {
                    if (page == 0){
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Средний прогресс",
                                    style = montserrat60016_1D1617
                                )
                                Spacer(Modifier.weight(1f))
                                Text(
                                    text = state.middleProgress,
                                    style = montserrat50012_42D742
                                )
                            }
                            Spacer(Modifier.height(20.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(99.dp))
                                    .background(_F7F8F8, RoundedCornerShape(99.dp))
                            ){
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(1f / state.floatMiddleProgress)
                                        .background(Brush.linearGradient(listOf(_228F7D, _9CEEDF)), RoundedCornerShape(topStart = 99.dp, bottomStart = 99.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${state.floatMiddleProgress * 100}%",
                                        style = montserrat50012White
                                    )
                                }
                            }
                            Spacer(Modifier.height(30.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = state.firstMonth,
                                    style = montserrat60016_B6B4C2
                                )
                                Spacer(Modifier.weight(1f))
                                Text(
                                    text = state.secondMonth,
                                    style = montserrat60016_B6B4C2
                                )
                            }
                            Spacer(Modifier.height(20.dp))
                        }
                        item {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier
                                    .fillParentMaxSize()
                            ) {
                                itemsIndexed(state.gallery.sortedBy {item -> item.category}){index, gallery ->
                                    Column {
                                        Text(
                                            text = gallery.category,
                                            style = montserrat50014_B6B4C2,
                                            modifier = Modifier
                                                .padding(top = 15.dp),
                                            textAlign = TextAlign.Center
                                        )
                                        CustomPhotoCard(
                                            photo = gallery.photo,
                                            modifier = Modifier
                                                .fillParentMaxWidth(0.47f)
                                                .height(150.dp)
                                                .padding(top = 15.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    else{
                        item {
                            AnimatedVisibility(
                                visible = state.statisticList.isNotEmpty(),
                                enter = slideInHorizontally(tween(500, easing = LinearOutSlowInEasing))
                            ) {
                                CustomCanvasBarChart(
                                    list = state.statisticList,
                                    height = 180.dp,
                                    lineColor = _A8E3D9,
                                    xAxisLineColor = _A5A3B0,
                                    textStyle = montserrat40012_B6B4C2,
                                    modifier = Modifier
                                        .fillParentMaxWidth()
                                )
                            }
                            Spacer(Modifier.height(15.dp))
                            Box(
                                modifier = Modifier
                                    .fillParentMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Card(
                                    colors = CardDefaults.cardColors(Color.White),
                                    shape = RoundedCornerShape(8.dp),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                                ) {
                                    Text(
                                        text = "Увеличено на 43% ",
                                        style = montserrat40010_42D742,
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                }
                            }
                            Spacer(Modifier.height(15.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillParentMaxWidth()
                            ) {
                                Text(
                                    text = state.firstMonth,
                                    style = montserrat60016_B6B4C2
                                )
                                Spacer(Modifier.weight(1f))
                                Text(
                                    text = state.secondMonth,
                                    style = montserrat60016_B6B4C2
                                )
                            }
                            Spacer(Modifier.height(20.dp))
                        }

                        itemsIndexed(state.statistic){ _, statistic ->
                            AnimatedVisibility(
                                visible = state.statistic.isNotEmpty(),
                                enter = fadeIn(tween(500, easing = LinearOutSlowInEasing))
                            ) {
                                CustomStatisticBar(
                                    statisticData = statistic,
                                    modifier = Modifier
                                        .fillParentMaxWidth()
                                )
                            }
                            Spacer(Modifier.height(20.dp))
                        }
                    }
                }
            }
        }

        item {
            Spacer(Modifier.height(30.dp))
            CustomGreenButton(
                text = "Назад",
                modifier = Modifier
                    .fillParentMaxWidth()
            ) {
                navController.popBackStack()
            }
            Spacer(Modifier.height((LocalConfiguration.current.screenHeightDp/20).dp))
        }
    }
}