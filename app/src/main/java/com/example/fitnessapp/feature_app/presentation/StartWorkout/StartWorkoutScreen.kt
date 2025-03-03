package com.example.fitnessapp.feature_app.presentation.StartWorkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomGreenButton
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.StartWorkout.components.CustomPerformingWorkout
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50012_A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60016Bold_1D1617
import org.koin.androidx.compose.koinViewModel

@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun StartWorkoutScreen(
    navController: NavController,
    viewModel: StartWorkoutViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    if (state.exception.isNotEmpty()) {
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(StartWorkoutEvent.ResetException)
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
                title = "",
                moreInformationClick = {},
                backgroundColor = _F7F8F8,
                textColor = Color.Transparent
            ) {
                navController.popBackStack()
            }
        }

        item {
            AsyncImage(
                model = "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//Video-Section.png",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = Route.StartWorkoutScreen.workout,
                style = montserrat60016Bold_1D1617
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = "Просто | 390 калорий",
                style = montserrat40012_B6B4C2
            )
            Spacer(Modifier.height(20.dp))
        }

        item {
            Text(
                text = "Описание",
                style = montserrat60016Bold_1D1617
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = state.workoutDescription,
                style = montserrat40012_B6B4C2
            )
            Spacer(Modifier.height(15.dp))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Как это сделать",
                    style = montserrat60016Bold_1D1617
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "4 Подхода",
                    style = montserrat50012_A5A3B0
                )
            }
            Spacer(Modifier.height(15.dp))
        }

        items(4) {
            CustomPerformingWorkout(
                title = "Раскиньте руки",
                description = "Чтобы жесты казались более расслабленными, в начале движения вытяните руки. Никаких сгибаний рук.",
                workoutNumber = it,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        item {
            Spacer(Modifier.height(45.dp))
            Box(
                Modifier
                    .padding(
                        bottom = (LocalConfiguration.current.screenHeightDp / 20).dp
                    ),
                contentAlignment = Alignment.BottomCenter
            ) {
                CustomGreenButton(
                    text = "Сохранить",
                    modifier = Modifier
                        .fillMaxWidth()
                ) { }
            }
        }
    }

    CustomIndicator(state.showIndicator)
}