package com.example.fitnessapp.feature_app.presentation.MealSchedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitnessapp.feature_app.presentation.MealSchedule.MealScheduleItem
import com.example.fitnessapp.ui.theme._00F0FF
import com.example.fitnessapp.ui.theme._00FF66
import com.example.fitnessapp.ui.theme._A5A3B0
import com.example.fitnessapp.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.ui.theme.montserrat40014_1D1617

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    MealCard(MealScheduleItem("","","")) { }
}

@Composable
fun MealCard(
    mealScheduleItem: MealScheduleItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val background = Brush.linearGradient(listOf(
        _00F0FF, _00FF66
    ))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(background, RoundedCornerShape(12.dp), 0.8f)
        ) {
            AsyncImage(
                model = mealScheduleItem.image,
                contentDescription = "meal image",
                modifier = Modifier
                    .padding(12.dp)
                    .size(40.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.width(10.dp))
        Column {
            Text(
                text = mealScheduleItem.name,
                style = montserrat40014_1D1617
            )
            Text(
                text = mealScheduleItem.time,
                style = montserrat40012_B6B4C2
            )
        }
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .border(1.dp, _A5A3B0, CircleShape)
                .size(30.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = _A5A3B0
            )
        }
    }
}