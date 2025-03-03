package com.example.fitnessapp.feature_app.presentation.Home.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.feature_app.presentation.ui.theme._1D161712

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(20.dp),
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    Card(
        modifier = modifier
            .shadow(10.dp, shape, spotColor = _1D161712),
        shape = shape,
        colors = CardDefaults.cardColors(Color.White),
        content = content,
        onClick = onClick,
        enabled = enabled,
        elevation = CardDefaults.cardElevation(0.dp)
    )
}