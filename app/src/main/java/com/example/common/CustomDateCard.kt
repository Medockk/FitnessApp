package com.example.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme._A5A3B0
import com.example.fitnessapp.ui.theme._F7F8F8
import com.example.fitnessapp.ui.theme.montserrat40012White
import com.example.fitnessapp.ui.theme.montserrat40012_7B6F72
import com.example.fitnessapp.ui.theme.montserrat40014_A5A3B0
import com.example.fitnessapp.ui.theme.montserrat50014White
import com.example.fitnessapp.ui.theme.montserrat50014_7B6F72
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    CustomDateCard(lastMountClick = {}) { }
}

@Composable
fun CustomDateCard(
    lastMountClick: () -> Unit,
    modifier: Modifier = Modifier,
    nextMountClick: () -> Unit
) {
    val now = Clock.System.now().toString().dropLast(1)
    val currentDay = LocalDateTime.parse(now)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = lastMountClick,
                modifier = Modifier
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "last mount",
                    tint = _A5A3B0
                )
            }
            Spacer(Modifier.width(25.dp))
            Text(
                text = currentDay.month.name + " ${currentDay.year}",
                style = montserrat40014_A5A3B0
            )
            Spacer(Modifier.width(25.dp))
            IconButton(
                onClick = nextMountClick,
                modifier = Modifier
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "next mount",
                    tint = _A5A3B0
                )
            }
        }
        Spacer(Modifier.height(15.dp))
        LazyRow(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(currentDay.month.maxLength()-1){
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = if (currentDay.dayOfMonth-1 == it) _228F7D else _F7F8F8),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = currentDay.dayOfWeek.plus(it.toLong()).toString()[0].toString() +
                                    currentDay.dayOfWeek.plus(it.toLong()).toString()[1].toString() +
                                    currentDay.dayOfWeek.plus(it.toLong()).toString()[2].toString(),
                            style = if (currentDay.dayOfMonth-1 == it) montserrat40012White else montserrat40012_7B6F72
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = (it+1).toString(),
                            style = if (currentDay.dayOfMonth-1 == it) montserrat50014White else montserrat50014_7B6F72
                        )
                    }
                }
                Spacer(Modifier.width(15.dp))
            }
        }
    }
}