@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.fitnessapp.feature_app.presentation.StartWorkout

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.common.CustomAlertDialog
import com.example.common.CustomIndicator
import com.example.fitnessapp.Route
import com.example.fitnessapp.feature_app.presentation.StartWorkout.components.CustomPerformingWorkout
import com.example.fitnessapp.ui.theme._1D1617
import com.example.fitnessapp.ui.theme._F7F8F8
import com.example.fitnessapp.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.ui.theme.montserrat50012_A5A3B0
import com.example.fitnessapp.ui.theme.montserrat60016_1D1617
import org.koin.androidx.compose.koinViewModel

@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun StartWorkoutScreen(
    navController: NavController,
    viewModel: StartWorkoutViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(Uri.parse(url)))
            this.playWhenReady = true
            prepare()
            play()
        }
    }

    if (state.exception.isNotEmpty()){
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
            TopAppBar(
                title = {},
                modifier = Modifier
                    .fillMaxWidth(),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(_F7F8F8, RoundedCornerShape(8.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            tint = _1D1617,
                            contentDescription = "close"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(_F7F8F8, RoundedCornerShape(8.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null,
                            tint = _1D1617,
                            modifier = Modifier
                                .rotate(90f)
                        )
                    }
                }
            )
            Spacer(Modifier.height(30.dp))
        }

        item {
            AndroidView(
                factory = { context ->
                    PlayerView(context).apply {
                        this.player = exoPlayer
                    }
                },
                update = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = Route.StartWorkoutScreen.workout,
                style = montserrat60016_1D1617
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
                style = montserrat60016_1D1617
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
                    style = montserrat60016_1D1617
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "4 Подхода",
                    style = montserrat50012_A5A3B0
                )
            }
        }

        items(4){
            CustomPerformingWorkout(
                title = "Раскиньте руки",
                description = "Чтобы жесты казались более расслабленными, в начале движения вытяните руки. Никаких сгибаний рук.",
                workoutNumber = it,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }

    CustomIndicator(state.showIndicator)
}