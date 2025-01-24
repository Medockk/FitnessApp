package com.example.fitnessapp.feature_app.presentation.OnBoard

import androidx.annotation.DrawableRes
import com.example.fitnessapp.R

data class OnBoardItem(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
)

var onBoardItemList = listOf(
    OnBoardItem(
        image = R.drawable.onboard_screen1,
        title = "Сжигай лишнее",
        description = "Давайте продолжать заниматься, чтобы" +
                "достичь своих целей, это больно только" +
                "временно, если ты сдашься сейчас, тебе" +
                "будет больно навсегда."
    ),
    OnBoardItem(
        image = R.drawable.onboard_screen2,
        title = "Питайся правильно",
        description = "Давайте начнем здоровый образ жизни" +
                "вместе с нами, мы сможем определять" +
                "ваш рацион каждый день." +
                "Здоровое питание - это весело"
    ),
    OnBoardItem(
        image = R.drawable.onboard_screen3,
        title = "Улучшите\nкачество сна",
        description = "Улучшайте качество своего сна вместе с" +
                "нами, качественный сон может принести" +
                "хорошее настроение с утра."
    ),
)
