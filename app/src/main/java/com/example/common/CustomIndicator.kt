package com.example.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme._F7F8F8

@Composable
fun CustomIndicator(
    showIndicator: Boolean,
) {
    AnimatedVisibility(
        visible = showIndicator,
        enter = fadeIn(tween(500, easing = LinearOutSlowInEasing)),
        exit = fadeOut(tween(500, easing = LinearOutSlowInEasing))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.linearGradient(listOf(_F7F8F8, _F7F8F8)), alpha = 0.3f),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator(
                color = _228F7D
            )
        }
    }
}