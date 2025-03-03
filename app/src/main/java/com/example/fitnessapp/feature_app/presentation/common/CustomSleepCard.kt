package com.example.fitnessapp.feature_app.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.model.SleepTracker
import com.example.fitnessapp.feature_app.presentation.ui.theme._A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40014_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014_1D1617

@Composable
fun CustomSleepCard(
    sleep: SleepTracker,
    icon: String,
    sleepEnd: String,
    modifier: Modifier = Modifier,
    changeEnabledClick: (Boolean) -> Unit,
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = icon,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Сон, ",
                        style = montserrat50014_1D1617
                    )
                    Text(
                        text = sleep.time,
                        style = montserrat40012_B6B4C2
                    )
                }
                Spacer(Modifier.height(5.dp))
                Text(
                    text = sleepEnd,
                    style = montserrat40014_B6B4C2
                )
            }
            Spacer(Modifier.weight(1f))
            Column {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp).align(Alignment.End),
                    tint = _A5A3B0,
                )
                Spacer(Modifier.height(15.dp))
                CustomSwitch(
                    checked = sleep.enabled,
                    onCheckedChange = changeEnabledClick
                )
            }
        }
    }
}


@Composable
fun CustomSleepCard(
    alarmClockTracker: AlarmClockTracker,
    icon: String,
    alarmEnd: String,
    modifier: Modifier = Modifier,
    changeEnabledClick: (Boolean) -> Unit,
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = icon,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Будильник, ",
                        style = montserrat50014_1D1617
                    )
                    Text(
                        text = alarmClockTracker.time,
                        style = montserrat40012_B6B4C2
                    )
                }
                Spacer(Modifier.height(5.dp))
                Text(
                    text = alarmEnd,
                    style = montserrat40014_B6B4C2
                )
            }
            Spacer(Modifier.weight(1f))
            Column {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp).align(Alignment.End),
                    tint = _A5A3B0,
                )
                Spacer(Modifier.height(15.dp))
                CustomSwitch(
                    checked = alarmClockTracker.enabled,
                    onCheckedChange = changeEnabledClick
                )
            }
        }
    }
}