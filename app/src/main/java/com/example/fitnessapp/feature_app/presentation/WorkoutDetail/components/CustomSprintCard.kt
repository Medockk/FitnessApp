package com.example.fitnessapp.feature_app.presentation.WorkoutDetail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.domain.model.WorkoutSprint
import com.example.fitnessapp.ui.theme.montserrat50012_B6B4C2
import com.example.fitnessapp.ui.theme.montserrat50014_1D1617

@Composable
fun CustomSprintCard(
    workoutSprint: WorkoutSprint,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = workoutSprint.image,
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(60.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(10.dp))
        Column {
            Text(
                text = workoutSprint.title,
                style = montserrat50014_1D1617
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = workoutSprint.description,
                style = montserrat50012_B6B4C2
            )
        }
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick = onClick,
            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.workout_next_icon),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}