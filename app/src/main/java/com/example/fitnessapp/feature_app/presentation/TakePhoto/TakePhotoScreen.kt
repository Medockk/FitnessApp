package com.example.fitnessapp.feature_app.presentation.TakePhoto

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.TakePhoto.components.CustomPhotoPose
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.ui.theme._07856E
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._81CCBF
import com.example.fitnessapp.feature_app.presentation.ui.theme._9CEEDF
import com.example.fitnessapp.feature_app.presentation.ui.theme._B6B4C2

@Composable
fun TakePhotoScreen(
    navController: NavController,
    viewModel: TakePhotoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val photo = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        if (it != null) {
            viewModel.onEvent(TakePhotoEvent.TakePhoto(it))
        }
    }

    BackHandler(state.showIndicator) {
        if (!state.showIndicator){
            navController.navigate(Route.ProgressPhotoScreen.route) {
                popUpTo(
                    Route.TakePhotoScreen.route
                ) { inclusive = true }
            }
        }
    }

    if (state.exception.isNotEmpty()) {
        CustomAlertDialog(state.exception) {
            viewModel.onEvent(TakePhotoEvent.ResetException)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        _81CCBF, _07856E
                    )
                ),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillParentMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.weight(1f))
                AsyncImage(
                    model = "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//takePhotoScreen.png",
                    contentDescription = null,
                    modifier = Modifier
                        .fillParentMaxWidth(0.5f),
                    contentScale = ContentScale.Fit
                )
                Spacer(Modifier.weight(1f))
                Card(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(horizontal = 40.dp)
                        .alpha(0.8f)
                        .background(Color.White, RoundedCornerShape(99.dp)),
                    colors = CardDefaults.cardColors(Color.Transparent, Color.Transparent),
                    shape = RoundedCornerShape(99.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 15.dp, horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    Color.Transparent
                                ),
                            enabled = !state.showIndicator
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.flash_off_icon),
                                contentDescription = "take a photo",
                                tint = _B6B4C2
                            )
                        }
                        IconButton(
                            onClick = {
                                photo.launch(null)
                            },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    Brush.linearGradient(
                                        listOf(
                                            _228F7D, _9CEEDF
                                        )
                                    ),
                                    CircleShape
                                ),
                            enabled = !state.showIndicator
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.camera_icon),
                                contentDescription = "take a photo",
                                tint = Color.White
                            )
                        }
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.Transparent),
                            enabled = !state.showIndicator
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.camera_icon),
                                contentDescription = "take a photo?",
                                tint = _B6B4C2
                            )
                        }
                    }
                }
                Spacer(Modifier.weight(1f))
                LazyRow(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .background(Color.White)
                ) {
                    itemsIndexed(state.imageBitmap) { _, bitmap ->
                        CustomPhotoPose(
                            bitmap = bitmap,
                            imageModifier = Modifier
                                .height(65.dp),
                            modifier = Modifier
                                .padding(vertical = 30.dp)
                        ) {}
                        Spacer(Modifier.width(20.dp))
                    }
                }
            }
        }
    }

    CustomIndicator(state.showIndicator)
}