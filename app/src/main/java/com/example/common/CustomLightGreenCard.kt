package com.example.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme._5CBDAC
import com.example.fitnessapp.ui.theme._68C6B6
import com.example.fitnessapp.ui.theme.montserrat40011White
import com.example.fitnessapp.ui.theme.montserrat50014_1D1617

@Composable
fun CustomLightGreenCard(
    title: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = _5CBDAC),
        shape = RoundedCornerShape(100.dp),
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = title,
                style = montserrat50014_1D1617
            )
            Spacer(Modifier.weight(1f))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(_68C6B6, RoundedCornerShape(100.dp))
                    .clickable {
                        onClick()
                    }
            ) {
                Text(
                    text = buttonText,
                    style = montserrat40011White,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}