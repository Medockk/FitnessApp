package com.example.fitnessapp.feature_app.presentation.SignIn

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomAssistChip
import com.example.fitnessapp.feature_app.presentation.common.CustomGreenButton
import com.example.fitnessapp.feature_app.presentation.common.CustomHorizontalDivider
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomTextField
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.presentation.ui.theme._1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40014_1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40016_1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50012_ADA4A5
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014_228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat70020Bold_1D1617
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    SignInScreen(rememberNavController())
}

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 20
    val paddingTop = LocalConfiguration.current.screenHeightDp / 15

    val authState = client.composeAuth.rememberSignInWithGoogle({
        when (it) {
            NativeSignInResult.ClosedByUser -> {
                Log.e("in", "closed")
            }

            is NativeSignInResult.Error -> {
                viewModel.onEvent(SignInEvent.SetException(it.exception.toString()))
            }

            is NativeSignInResult.NetworkError -> {
                viewModel.onEvent(SignInEvent.SetException(it.message))
            }

            NativeSignInResult.Success -> {
                Log.i("in", "successful")
                viewModel.onEvent(SignInEvent.SignInWithGoogle)
            }
        }
    })

    LaunchedEffect(key1 = !state.isRegistered) {
        if (state.isRegistered) {
            navController.navigate(Route.SuccessRegistrationScreen.route) {
                popUpTo(Route.SignInScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    if (state.exception.isNotEmpty()) {
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(SignInEvent.ResetException)
        }
    }

    Box(Modifier
        .fillMaxSize()
        .background(Color.White))

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
                    style = montserrat70020Bold_1D1617
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
                    .fillMaxWidth(),
                enabled = !state.showIndicator
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
                enabled = !state.showIndicator,
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
                ),
                enabled = !state.showIndicator
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
                        .fillMaxWidth(),
                    enabled = !state.showIndicator
                ) {
                    viewModel.onEvent(SignInEvent.SignInClick)
                }
                Spacer(Modifier.height(25.dp))
                CustomHorizontalDivider(
                    color = _1D1617,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(Modifier.height(25.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val coroutineScope = rememberCoroutineScope()
                    CustomAssistChip(
                        icon = ImageVector.vectorResource(R.drawable.google_icon),
                        modifier = Modifier
                            .size(50.dp),
                        enabled = !state.showIndicator
                    ) {
                        coroutineScope.launch {
                            authState.startFlow()
                        }
                    }
                    Spacer(Modifier.width(30.dp))
                    CustomAssistChip(
                        icon = ImageVector.vectorResource(R.drawable.facebook_icon),
                        modifier = Modifier
                            .size(50.dp),
                        enabled = !state.showIndicator
                    ) { }
                }
                Spacer(Modifier.height(25.dp))
                TextButton(
                    onClick = {
                        navController.navigate(Route.SignUpScreen.route) {
                            popUpTo(Route.SignInScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    ),
                    enabled = !state.showIndicator
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Нет аккаунта? ",
                            style = montserrat40014_1D1617
                        )
                        Text(
                            text = "Зарегистрироваться",
                            style = montserrat50014_228F7D
                        )
                    }
                }
            }
        }
    }

    CustomIndicator(state.showIndicator)
}