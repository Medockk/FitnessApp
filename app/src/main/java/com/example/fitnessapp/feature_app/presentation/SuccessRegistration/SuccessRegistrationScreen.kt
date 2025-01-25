package com.example.fitnessapp.feature_app.presentation.SuccessRegistration

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.common.CustomAlertDialog
import com.example.common.CustomGreenButton
import com.example.fitnessapp.R
import com.example.fitnessapp.Route
import com.example.fitnessapp.ui.theme.montserrat40012_7B6F72
import com.example.fitnessapp.ui.theme.montserrat70020_1D1617
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    SuccessRegistrationScreen(rememberNavController())
}

@Composable
fun SuccessRegistrationScreen(
    navController: NavController,
    viewModel: SuccessRegistrationViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 10
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 20

    if (state.exception.isNotEmpty()){
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(SuccessRegistrationEvent.ResetException)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                top = paddingTop.dp,
                bottom = paddingBottom.dp,
                start = 30.dp,
                end = 30.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.welcome_user_image),
                    contentDescription = "welcome user",
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(25.dp))
                Text(
                    text = "Добро пожаловать, \n" +
                            state.userData.fio,
                    style = montserrat70020_1D1617,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Теперь все готово, давайте\nдостигать ваших целей вместе с\nнами.",
                    style = montserrat40012_7B6F72,
                    textAlign = TextAlign.Center
                )
            }
        }

        item {
            CustomGreenButton(
                text = "Перейти домой",
                modifier = Modifier
                    .fillParentMaxWidth()
            ) {
                navController.navigate(Route.HomeScreen.route){
                    popUpTo(Route.SuccessRegistrationScreen.route){
                        inclusive = true
                    }
                }
            }
        }
    }
}