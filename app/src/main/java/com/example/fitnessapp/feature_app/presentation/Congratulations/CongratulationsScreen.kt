package com.example.fitnessapp.feature_app.presentation.Congratulations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.common.CustomGreenButton
import com.example.fitnessapp.Route
import com.example.fitnessapp.ui.theme.montserrat40012_7B6F72
import com.example.fitnessapp.ui.theme.montserrat70020Bold_1D1617

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    CongratulationsScreen(rememberNavController())
}

@Composable
fun CongratulationsScreen(
    navController: NavController,
    viewModel: CongratulationViewModel = viewModel()
) {

    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 10

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingTop.dp,
                start = 30.dp, end = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                AsyncImage(
                    model = state.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(30.dp))
                Text(
                    text = "Поздравляем, вы завершили тренировку",
                    style = montserrat70020Bold_1D1617,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = "Упражнения – король, а питание – королева. Объедините их, и вы получите королевство.",
                    style = montserrat40012_7B6F72,
                    textAlign = TextAlign.Center
                )
            }
        }

        item {
            CustomGreenButton(
                text = "Завершить",
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(bottom = (LocalConfiguration.current.screenHeightDp / 20).dp)
            ) {
                navController.navigate(Route.HomeScreen.route){
                    popUpTo(Route.CongratulationsScreen.route){
                        inclusive = true
                    }
                }
            }
        }
    }
}