package com.example.fitnessapp.feature_app.presentation.CreateProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.theme.montserrat40012_7B6F72
import com.example.fitnessapp.ui.theme.montserrat40012_ADA4A5
import com.example.fitnessapp.ui.theme.montserrat70020_1D1617

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    CreateProfileScreen(rememberNavController())
}

@Composable
fun CreateProfileScreen(
    navController: NavController,
    viewModel: CreateProfileViewModel = viewModel()
) {

    val state = viewModel.state.value
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 20
    val textFieldList = listOf(
        listOf(
            ImageVector.vectorResource(R.drawable.gender_icon),
            "Ваш пол",
            state.gender,
            {it: String -> viewModel.onEvent(CreateProfileEvent.EnterGender(it))},
            true,
            false,
            false
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.birthday_data_icon),
            "Дата рождения",
            state.birthdayData,
            {it: String -> viewModel.onEvent(CreateProfileEvent.EnterBirthdayData(it))},
            false,
            false,
            false
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.weight_scale_icon),
            "Ваш вес",
            state.weight,
            {it: String -> viewModel.onEvent(CreateProfileEvent.EnterWeight(it))},
            false,
            true,
            false
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.height_icon),
            "Ваш рост",
            state.height,
            {it: String -> viewModel.onEvent(CreateProfileEvent.EnterHeight(it))},
            false,
            false,
            true
        ),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                bottom = paddingBottom.dp,
                start = 20.dp, end = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        item{
            Image(
                painter = painterResource(R.drawable.registration_page_image),
                contentDescription = "create your profile",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        item {
            Column(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Давайте создадим\n" +
                            "ваш профиль",
                    style = montserrat70020_1D1617,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Это поможет нам узнать о вас больше!",
                    style = montserrat40012_7B6F72,
                    textAlign = TextAlign.Center
                )
            }
        }
        items(textFieldList) {
            TextField(
                value = it[2] as String,
                onValueChange = it[3] as (String) -> Unit,
                label = {
                    Text(
                        text = it[1] as String,
                        style = montserrat40012_ADA4A5
                    )
                }
            )
        }
    }
}