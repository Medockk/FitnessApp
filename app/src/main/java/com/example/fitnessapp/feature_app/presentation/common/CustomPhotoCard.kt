package com.example.fitnessapp.feature_app.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CustomPhotoCard(
    photo: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = photo,
        contentDescription = null,
        modifier = modifier
            .clip(RoundedCornerShape(15.dp)),
        contentScale = ContentScale.Crop
    )
}