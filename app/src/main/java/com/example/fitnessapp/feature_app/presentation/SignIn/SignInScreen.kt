package com.example.fitnessapp.feature_app.presentation.SignIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.common.CustomGreenButton
import com.example.common.CustomTextField
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.theme.montserrat40016_1D1617
import com.example.fitnessapp.ui.theme.montserrat50012_ADA4A5
import com.example.fitnessapp.ui.theme.montserrat70020_1D1617

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    SignInScreen(rememberNavController())
}

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = viewModel()
) {

    val state = viewModel.state.value
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 20
    val paddingTop = LocalConfiguration.current.screenHeightDp / 15

    Box(Modifier.fillMaxSize().background(Color.White))

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 30.dp,
                end = 30.dp,
                bottom = paddingBottom.dp,
                top = paddingTop.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillParentMaxWidth()
            ) {
                Text(
                    text = "Привет,",
                    style = montserrat40016_1D1617
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Добро пожаловать",
                    style = montserrat70020_1D1617
                )
            }
            Spacer(Modifier.height(30.dp))

            CustomTextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(SignInEvent.EnterEmail(it))
                },
                icon = ImageVector.vectorResource(R.drawable.email_icon),
                hint = "Почта",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(15.dp))

            CustomTextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(SignInEvent.EnterPassword(it))
                },
                icon = ImageVector.vectorResource(R.drawable.password_icon),
                hint = "Пароль",
                modifier = Modifier
                    .fillMaxWidth(),
                isPassword = true,
                showHidePasswordState = state.showHidePasswordState,
                showHidePasswordClick = {
                    viewModel.onEvent(SignInEvent.ShowHidePassword)
                }
            )

            Spacer(Modifier.height(10.dp))

            TextButton(
                onClick = {
                    viewModel.onEvent(SignInEvent.ForgotPassword)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    text = "Забыл пароль?",
                    style = montserrat50012_ADA4A5
                )
            }
        }

        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillParentMaxWidth()
            ) {
                CustomGreenButton(
                    text = "Войти",
                    isSignInScreen = true,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    viewModel.onEvent(SignInEvent.SignInClick)
                }


            }
        }
    }
}