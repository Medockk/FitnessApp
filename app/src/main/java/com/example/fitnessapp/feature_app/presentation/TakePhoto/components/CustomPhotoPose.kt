package com.example.fitnessapp.feature_app.presentation.TakePhoto.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitnessapp.ui.theme._F7F8F8

@Composable
fun CustomPhotoPose(
    image: String,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(_F7F8F8),
        onClick = {
            onClick(image)
        }
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = imageModifier
                .padding(horizontal = 20.dp, vertical = 5.dp),
            contentScale = ContentScale.Fit
        )
    }
}