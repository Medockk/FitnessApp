package com.example.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme._1D1617
import com.example.fitnessapp.ui.theme.montserrat40012_1D1617

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    CustomHorizontalDivider(
        _1D1617,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, start = 30.dp, end = 30.dp)
    )
}

@Composable
fun CustomHorizontalDivider(
    color: Color,
    text: String = "Или",
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Box(Modifier
            .fillMaxWidth(0.45f)
            .height(1.dp)
            .background(color))
        Spacer(Modifier.width(5.dp))
        Text(
            text = text,
            style = montserrat40012_1D1617
        )
        Spacer(Modifier.width(5.dp))
        Box(Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color))
    }
}