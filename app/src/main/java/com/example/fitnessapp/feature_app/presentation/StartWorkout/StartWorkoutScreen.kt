package com.example.fitnessapp.feature_app.presentation.StartWorkout

import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.StartWorkout.components.CustomPerformingWorkout
import com.example.fitnessapp.feature_app.presentation.StartWorkout.components.CustomUserWorkoutRepeats
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomGreenButton
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50012_A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60016Bold_1D1617
import org.koin.androidx.compose.koinViewModel

@OptIn(UnstableApi::class)
@Composable
fun StartWorkoutScreen(
    navController: NavController,
    viewModel: StartWorkoutViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val context = LocalContext.current
    val activity = LocalActivity.current

    LaunchedEffect(key1 = state.videoUrl.isEmpty()) {
        if (state.videoUrl.isNotEmpty()) {
            val exoPlayer = androidx.media3.exoplayer.ExoPlayer.Builder(context).build().also {
                val mediaItem = MediaItem.fromUri(state.videoUrl)
                it.setMediaItem(mediaItem)
                it.prepare()
                it.addListener(object : Player.Listener {
                    override fun onPlayerError(error: PlaybackException) {
                        viewModel.onEvent(StartWorkoutEvent.SetException(error.errorCodeName))
                    }

                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        super.onIsPlayingChanged(isPlaying)
                        if (activity != null) {
                            if (!state.isFullScreen){
                                viewModel.onEvent(StartWorkoutEvent.ChangeFullScreenOrientation(activity,
                                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE))
                            }
                        }
                    }
                })
                it.seekTo(state.videoPosition)
            }
            viewModel.onEvent(StartWorkoutEvent.SetExoplayer(exoPlayer))
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            if (state.exoPlayer != null) {
                viewModel.onEvent(StartWorkoutEvent.ChangeVideoPosition)
                state.exoPlayer.release()
            }
        }
    }

    BackHandler {
        if (state.videoUrl.isNotEmpty() && state.exoPlayer != null) {
            state.exoPlayer.release()
        }
        if (activity != null) {
            viewModel.onEvent(
                StartWorkoutEvent.ChangeFullScreenOrientation(
                    activity,
                    ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                )
            )
        }
        navController.popBackStack()
    }

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
                if (state.videoUrl.isNotEmpty() && state.exoPlayer != null) {
                    state.exoPlayer.release()
                }
                if (activity != null) {
                    viewModel.onEvent(
                        StartWorkoutEvent.ChangeFullScreenOrientation(
                            activity,
                            ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                        )
                    )
                }
                navController.popBackStack()
            }
        }

        item {
            Crossfade(targetState = state.exoPlayer != null) {
                if (!it) {
                    AsyncImage(
                        model = "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//Video-Section.png",
                        contentDescription = "feature video",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                AnimatedVisibility(
                    visible = it
                ) {
                    AndroidView(
                        factory = { factoryContext ->
                            PlayerView(factoryContext).apply {
                                player = state.exoPlayer
                                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                                if (this.player?.playWhenReady != null) {
                                    viewModel.onEvent(StartWorkoutEvent.ChangeVideoPosition)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(22.dp))
                    ) {  }
                }
            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = Route.StartWorkoutScreen.title,
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    CustomUserWorkoutRepeats(
                        initialValue = 1,
                        height = 110.dp,
                        modifier = Modifier
                            .fillParentMaxWidth()
                    ) { viewModel.onEvent(StartWorkoutEvent.ChangeUserRepeatable(it)) }
                }
            }
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