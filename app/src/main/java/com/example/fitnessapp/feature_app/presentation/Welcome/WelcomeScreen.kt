package com.example.fitnessapp.feature_app.presentation.Welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40018_CFCFCF
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat70036_1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat70036_52B09F

@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: WelcomeViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 30

    LaunchedEffect(key1 = !state.isComplete) {
        if (state.isComplete) {
            navController.navigate(Route.OnBoardScreen.route) {
                popUpTo(Route.WelcomeScreen.route) {
                    inclusive = true
                }
            }
        }
    }
    LaunchedEffect(key1 = !state.isQueueComplete) {
        if (state.isQueueComplete) {
            navController.navigate(Route.SignInScreen.route) {
                popUpTo(Route.WelcomeScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingBottom.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.welcome_screen),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(bottom = 15.dp),
            contentScale = ContentScale.Crop
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Wild",
                style = montserrat70036_52B09F
            )
            Text(
                text = "Way",
                style = montserrat70036_1D1617
            )
        }
        Text(
            text = "Каждый может тренироваться",
            style = montserrat40018_CFCFCF
        )
    }
}