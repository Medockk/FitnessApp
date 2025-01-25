package com.example.fitnessapp.feature_app.presentation.RegisterPage

import com.example.fitnessapp.R

data class RegisterPageState(
    val yourPurpose: List<PurposeItem> = purposeList,
    val currentPage: Int = 0,

    val isComplete: Boolean = false,
)

val purposeList = listOf(
    PurposeItem(
        image = R.drawable.reg_page_image_1,
        title = "Улучшить форму",
        description = "У меня мало жира в организме, и мне нужно нарастить больше мышечной массы."
    ),
    PurposeItem(
        image = R.drawable.reg_page_image_2,
        title = "Тонус",
        description = "Я «худой толстый». выглядят тонкими, но не имеют формы. Я хочу правильно нарастить мышечную массу"
    ),
    PurposeItem(
        image = R.drawable.reg_page_image_1,
        title = "Похудеть",
        description = "Мне нужно сбросить более 20 кг. Я хочу сбросить весь этот жир и набрать мышечную массу.."
    ),
)
