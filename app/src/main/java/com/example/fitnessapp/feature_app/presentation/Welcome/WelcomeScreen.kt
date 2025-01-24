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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.Route
import com.example.fitnessapp.ui.theme.montserrat40018_CFCFCF
import com.example.fitnessapp.ui.theme.montserrat70036_1D1617
import com.example.fitnessapp.ui.theme.montserrat70036_52B09F

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Prev() {
    WelcomeScreen(rememberNavController())
}

@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: WelcomeViewModel = viewModel()
) {

    val state = viewModel.state.value

    LaunchedEffect(key1 = !state.isComplete) {
        if (state.isComplete){
            navController.navigate(Route.OnBoardScreen.route){
                popUpTo(Route.WelcomeScreen.route){
                    inclusive = true
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
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