package com.example.fitnessapp.feature_app.presentation.Profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme._1D161712
import com.example.fitnessapp.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.ui.theme.montserrat50014_228F7D

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    UserDataCard("12", "1w")
}

@Composable
fun UserDataCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .shadow(10.dp, RoundedCornerShape(16.dp), ambientColor = _1D161712),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                style = montserrat50014_228F7D
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = description,
                style = montserrat40012_B6B4C2
            )
        }
    }
}