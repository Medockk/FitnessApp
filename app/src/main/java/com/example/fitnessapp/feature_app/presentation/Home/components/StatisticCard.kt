package com.example.fitnessapp.feature_app.presentation.Home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme._1D161712
import com.example.fitnessapp.ui.theme.montserrat50012_1D1617
import com.example.fitnessapp.ui.theme.montserrat60014_228F7D

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    StatisticCard("qwe", "qqw") {
        Spacer(Modifier)
    }
}

@Composable
fun StatisticCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Card(
        modifier = modifier
            .shadow(10.dp, RoundedCornerShape(20.dp), spotColor = _1D161712),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = title,
                style = montserrat50012_1D1617
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = description,
                style = montserrat60014_228F7D
            )
            Spacer(Modifier.height(5.dp))
            content()
        }
    }
}