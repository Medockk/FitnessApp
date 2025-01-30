package com.example.fitnessapp.feature_app.presentation.WorkoutTracker.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.common.CustomSwitch
import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData
import com.example.fitnessapp.ui.theme._C4C4C4
import com.example.fitnessapp.ui.theme.montserrat40010_A5A3B0
import com.example.fitnessapp.ui.theme.montserrat50012_1D1617

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {

}

@Composable
fun UserWorkoutCard(
    userWorkoutData: UserWorkoutData,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit,
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(15.dp)
        ){
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(_C4C4C4, CircleShape),
                contentAlignment = Alignment.Center
            ){
                AsyncImage(
                    model = userWorkoutData.image,
                    contentDescription = "workout image",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(7.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.width(10.dp))
            Column {
                Text(
                    text = userWorkoutData.title,
                    style = montserrat50012_1D1617
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = userWorkoutData.time,
                    style = montserrat40010_A5A3B0
                )
            }
            Spacer(Modifier.weight(1f))
            CustomSwitch(
                checked = userWorkoutData.isTurnOn,
                onCheckedChange = onCheckedChange
            )
        }
    }
}