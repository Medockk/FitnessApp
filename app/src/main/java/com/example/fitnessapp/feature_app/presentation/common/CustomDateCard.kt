package com.example.fitnessapp.feature_app.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012White
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_7B6F72
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40014_A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014White
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014_7B6F72
import java.time.LocalDate

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    CustomDateCard(lastMountClick = {}) { }
}

@Composable
fun CustomDateCard(
    lastMountClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    day: Int = LocalDate.now().dayOfMonth,
    nextMountClick: () -> Unit = {},
    onDateClick: (Int) -> Unit,
) {
    val currentDay = LocalDate.now()

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
            items(
                if (currentDay.month.value == 2) {
                    currentDay.month.maxLength() - 1
                } else {
                    currentDay.month.maxLength()
                }
            ) {
                Card(
                    modifier = Modifier
                        .widthIn(60.dp),
                    onClick = {
                        onDateClick(it + 1)
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (day == it + 1) {
                            _228F7D
                        } else {
                            _F7F8F8
                        }
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = currentDay.withDayOfMonth(it + 1).dayOfWeek.name[0].toString() +
                                    currentDay.withDayOfMonth(it + 1).dayOfWeek.name[1].toString() +
                                    currentDay.withDayOfMonth(it + 1).dayOfWeek.name[2].toString(),
                            style = if (day == it + 1) {
                                montserrat40012White
                            } else {
                                montserrat40012_7B6F72
                            }
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = (it + 1).toString(),
                            style = if (day == it + 1) {
                                montserrat40012White
                            } else {
                                montserrat40012_7B6F72
                            }
                        )
                    }
                }
                Spacer(Modifier.width(15.dp))
            }
        }
    }
}