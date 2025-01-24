package com.example.fitnessapp.feature_app.presentation.SignIn

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    SignInScreen(rememberNavController())
}

@Composable
fun SignInScreen(
    navController: NavController
) {

}