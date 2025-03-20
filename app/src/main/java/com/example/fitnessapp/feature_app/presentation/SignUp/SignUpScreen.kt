@file:Suppress("UNCHECKED_CAST")

package com.example.fitnessapp.feature_app.presentation.SignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.data.data_source.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomAssistChip
import com.example.fitnessapp.feature_app.presentation.common.CustomGreenButton
import com.example.fitnessapp.feature_app.presentation.common.CustomHorizontalDivider
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomTextField
import com.example.fitnessapp.feature_app.presentation.ui.theme._1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._ADA4A5
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40010_ADA4A5
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40016_1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014_228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014_ADA4A5
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat70020Bold_1D1617
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 15
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 20
    val signUpList = listOf(
        listOf(
            state.fio,
            { it: String -> viewModel.onEvent(SignUpEvent.EnterFIO(it)) },
            ImageVector.vectorResource(R.drawable.fio_icon),
            "ФИО"
        ),
        listOf(
            state.phone,
            { it: String -> viewModel.onEvent(SignUpEvent.EnterPhone(it)) },
            ImageVector.vectorResource(R.drawable.phone_icon),
            "Номер телефона"
        ),
        listOf(
            state.email,
            { it: String -> viewModel.onEvent(SignUpEvent.EnterEmail(it)) },
            ImageVector.vectorResource(R.drawable.email_icon),
            "Почта"
        ),
        listOf(
            state.password,
            { it: String -> viewModel.onEvent(SignUpEvent.EnterPassword(it)) },
            ImageVector.vectorResource(R.drawable.password_icon),
            "Пароль"
        ),
    )

    val authState = client.composeAuth.rememberSignInWithGoogle({
        when (it) {
            NativeSignInResult.ClosedByUser -> {

            }

            is NativeSignInResult.Error -> {
                viewModel.onEvent(SignUpEvent.SetException(it.exception.toString()))
            }

            is NativeSignInResult.NetworkError -> {
                viewModel.onEvent(SignUpEvent.SetException(it.message))
            }

            NativeSignInResult.Success -> {
                viewModel.onEvent(SignUpEvent.SignUpWithGoogle)
            }
        }
    })

    LaunchedEffect(key1 = !state.isFirstRegistration) {
        if (state.isFirstRegistration) {
            navController.navigate(Route.CreateProfileScreen.route) {
                popUpTo(Route.SignUpScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(key1 = !state.isNotFirstRegistration) {
        if (state.isNotFirstRegistration) {
            navController.navigate(Route.SuccessRegistrationScreen.route) {
                popUpTo(Route.SignUpScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    if (state.exception.isNotEmpty()) {
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(SignUpEvent.ResetException)
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 30.dp, end = 30.dp,
                top = paddingTop.dp, bottom = paddingBottom.dp
            )
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Привет,",
            style = montserrat40016_1D1617
        )
        Spacer(Modifier.height(5.dp))
        Text(
            text = "Создай аккаунт",
            style = montserrat70020Bold_1D1617
        )
        Spacer(Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(signUpList){
                    CustomTextField(
                        value = it[0] as String,
                        onValueChange = it[1] as (String) -> Unit,
                        icon = it[2] as ImageVector,
                        hint = it[3] as String,
                        enabled = !state.showIndicator,
                        tag = it[3] as String,
                        modifier = Modifier
                            .fillMaxWidth(),
                        isPassword = signUpList[3] == it,
                        showHidePasswordState = if (signUpList[3] == it) {
                            state.showHidePasswordState
                        } else {
                            false
                        },
                        showHidePasswordClick = {
                            viewModel.onEvent(SignUpEvent.ChangeShowHidePasswordState)
                        }
                    )
                    Spacer(Modifier.height(15.dp))
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Checkbox(
                            checked = state.checkBoxState,
                            onCheckedChange = {
                                viewModel.onEvent(SignUpEvent.ChangeCheckBoxState(it))
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = _228F7D,
                                uncheckedColor = _ADA4A5,
                                checkmarkColor = Color.White,
                                disabledCheckedColor = _228F7D,
                                disabledUncheckedColor = _ADA4A5
                            ),
                            enabled = !state.showIndicator,
                            modifier = Modifier.clip(RoundedCornerShape(3.dp))
                        )
                        TextButton(
                            onClick = {
                                viewModel.onEvent(SignUpEvent.ChangeCheckBoxState(!state.checkBoxState))
                            },
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = Color.Transparent
                            ),
                            enabled = !state.showIndicator
                        ) {
                            Text(
                                text = "Продолжая, вы принимаете нашу Политику конфиденциальности и Условия использования.",
                                style = montserrat40010_ADA4A5
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            CustomGreenButton(
                text = "Зарегистрироваться",
                modifier = Modifier
                    .fillMaxWidth(),
                tag = "btn",
                enabled = !state.showIndicator
            ) {
                viewModel.onEvent(SignUpEvent.SignUp)
            }
            Spacer(Modifier.height(25.dp))

            CustomHorizontalDivider(
                color = _1D1617,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(25.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomAssistChip(
                    icon =
                        ImageVector.vectorResource(R.drawable.google_icon),
                    modifier = Modifier
                        .size(50.dp),
                    enabled = !state.showIndicator
                ) {
                    authState.startFlow()
                }
                Spacer(Modifier.width(30.dp))
                CustomAssistChip(
                    icon =
                        ImageVector.vectorResource(R.drawable.facebook_icon),
                    modifier = Modifier
                        .size(50.dp),
                    enabled = !state.showIndicator
                ) { }
            }
            Spacer(Modifier.height(20.dp))
            TextButton(
                onClick = {
                    navController.navigate(Route.SignInScreen.route) {
                        popUpTo(Route.SignUpScreen.route) {
                            inclusive = true
                        }
                    }
                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent
                ),
                enabled = !state.showIndicator
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Имеете уже аккаунт? ",
                        style = montserrat50014_ADA4A5
                    )
                    Text(
                        text = "Войти",
                        style = montserrat50014_228F7D
                    )
                }
            }
        }
    }

    CustomIndicator(state.showIndicator)
}