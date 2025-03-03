package com.example.fitnessapp.feature_app.presentation.ProgressPhoto

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
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnessapp.feature_app.presentation.common.BottomBar
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomGreenButton
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomLightGreenCard
import com.example.fitnessapp.feature_app.presentation.common.CustomPhotoCard
import com.example.fitnessapp.feature_app.presentation.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.ui.theme._1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._9CEEDF
import com.example.fitnessapp.feature_app.presentation.ui.theme._B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme._FF0000
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_FF0000
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50012_1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50012_A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014_1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60016_1D1617
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProgressPhotoScreen(
    navController: NavController,
    viewModel: ProgressPhotoViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val lazyStaggeredGridCells = rememberLazyStaggeredGridState()

    if (state.exception.isNotEmpty()) {
        CustomAlertDialog(description = state.exception) {
            viewModel.onEvent(ProgressPhotoEvent.ResetException)
        }
    }

    CustomIndicator(state.showIndicator)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            CustomTopAppBar(
                title = "Фото прогресса",
                moreInformationClick = {},
                textColor = _1D1617,
                backgroundColor = _F7F8F8,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                navController.navigate(Route.HomeScreen.route) {
                    popUpTo(Route.ProgressPhotoScreen.route) {
                        inclusive = true
                    }
                }
            }
        },
        bottomBar = {
            BottomBar(
                currentPage = Route.ProgressPhotoScreen,
                navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 30.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .background(
                            Brush.linearGradient(listOf(_FF0000, _FF0000)),
                            RoundedCornerShape(20.dp),
                            0.1f
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(Color.Transparent, Color.Transparent),
                    onClick = {
                        navController.navigate(Route.TakePhotoScreen.route)
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color.White, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//PhotoNotification.png",
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Напоминание!",
                                style = montserrat40012_FF0000
                            )
                            Spacer(Modifier.height(5.dp))
                            Text(
                                text = "Следующие фото ${state.nextPhotoDate}",
                                style = montserrat50014_1D1617
                            )
                        }
                        Spacer(Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.Close,
                            tint = _B6B4C2,
                            contentDescription = "close",
                            modifier = Modifier
                                .align(Alignment.Top)
                                .size(10.dp)
                        )
                    }
                }
                Spacer(Modifier.height(20.dp))
            }

            item {
                Card(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .background(
                            Brush.linearGradient(listOf(_228F7D, _9CEEDF)),
                            RoundedCornerShape(22.dp),
                            0.8f
                        ),
                    colors = CardDefaults.cardColors(Color.Transparent, Color.Transparent),
                    shape = RoundedCornerShape(22.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Отслеживайте свой\nпрогресс каждый месяц",
                                style = montserrat50012_1D1617
                            )
                            Spacer(Modifier.height(15.dp))
                            CustomGreenButton(
                                text = "Больше",
                            ) { }
                        }
                        AsyncImage(
                            model = "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//checkYourProgress.png",
                            contentDescription = "check your progress every day",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(Modifier.height(30.dp))
            }

            item {
                CustomLightGreenCard(
                    title = "Сравните свое фото",
                    buttonText = "Сравнивать",
                    modifier = Modifier
                        .fillParentMaxWidth()
                ) { navController.navigate(Route.ComparisonScreen.route) }
                Spacer(Modifier.height(30.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillParentMaxWidth()
                ) {
                    Text(
                        text = "Галлерея",
                        style = montserrat60016_1D1617
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "Больше",
                        style = montserrat50012_A5A3B0
                    )
                }
                Spacer(Modifier.height(15.dp))
            }

            item {
                LazyHorizontalStaggeredGrid(
                    rows = StaggeredGridCells.Fixed(3),
                    modifier = Modifier
                        .fillParentMaxSize(),
                    state = lazyStaggeredGridCells,
                    horizontalItemSpacing = 10.dp,
                    verticalArrangement = Arrangement.spacedBy((-15).dp),
                ) {
                    itemsIndexed(state.gallery.sortedBy { item -> item.date }) { index, gallery ->
                        Column {
                            if (lazyStaggeredGridCells.firstVisibleItemIndex == state.gallery[index].id) {
                                Text(text = gallery.date)
                                Spacer(Modifier.height(10.dp))
                            }
                            CustomPhotoCard(
                                photo = gallery.photo,
                                modifier = Modifier
                                    .size(100.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}