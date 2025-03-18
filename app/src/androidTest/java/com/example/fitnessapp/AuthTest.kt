package com.example.fitnessapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignInUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignInWithGoogleUseCase
import com.example.fitnessapp.feature_app.presentation.SignIn.SignInScreen
import com.example.fitnessapp.feature_app.presentation.SignIn.SignInViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthTest {

    private lateinit var authRepository: AuthTestRepoImpl
    private lateinit var signInUseCase: SignInUseCase
    private lateinit var signInWithGoogleUseCase: SignInWithGoogleUseCase

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun clientInit() {
        authRepository = AuthTestRepoImpl()
        signInUseCase = SignInUseCase(authRepository)
        signInWithGoogleUseCase = SignInWithGoogleUseCase(authRepository)
    }

    @Test
    fun emailValidateFailed() {
        rule.setContent {
            SignInScreen(rememberNavController(), SignInViewModel(signInUseCase, signInWithGoogleUseCase))
        }

        rule.onAllNodesWithTag("Почта")
            .onFirst().performTextInput("qwerty")
        rule.onAllNodesWithTag("btn").onFirst().performClick()
        rule.onAllNodesWithTag("dialog")
            .onFirst().performClick()
    }

    @Test
    fun emailValidateSuccessful() {
        rule.setContent {
            SignInScreen(
                rememberNavController(),
                SignInViewModel(
                    signInUseCase, signInWithGoogleUseCase
                )
            )
        }

        rule.onAllNodesWithTag("Почта").onFirst().performTextInput("qwerty@gmail.com")
        rule.onAllNodesWithTag("btn").onFirst().performClick()
    }

    @Test
    fun passwordValidateFailed() {
        rule.setContent {
            SignInScreen(rememberNavController(), SignInViewModel(signInUseCase, signInWithGoogleUseCase))
        }
        rule.onAllNodesWithTag("Пароль")
            .onFirst().performTextInput("qwerty")
        rule.onAllNodesWithTag("btn")
            .onFirst().performClick()
        rule.onAllNodesWithTag("dialog")
            .onFirst().performClick()
    }

    @Test
    fun passwordValidateSuccessful() {
        rule.setContent {
            SignInScreen(rememberNavController(), SignInViewModel(signInUseCase, signInWithGoogleUseCase))
        }
        rule.onAllNodesWithTag("Пароль")
            .onFirst().performTextInput("qW3eRt6Y")
        rule.onAllNodesWithTag("btn")
            .onFirst().performClick()
    }

    @Test
    fun authSuccessful() {
        rule.setContent {
            SignInScreen(rememberNavController(), SignInViewModel(signInUseCase, signInWithGoogleUseCase))
        }
        rule.onAllNodesWithTag("Почта")
            .onFirst().performTextInput("test2@gmail.com")
        rule.onAllNodesWithTag("Пароль")
            .onFirst().performTextInput("qw2E4rT5y")
        rule.onAllNodesWithTag("btn")
            .onFirst().performClick()
    }

    @Test
    fun authFailed() {
        rule.setContent {
            SignInScreen(rememberNavController(), SignInViewModel(signInUseCase, signInWithGoogleUseCase))
        }
        rule.onAllNodesWithTag("Почта")
            .onFirst().performTextInput("test1")
        rule.onAllNodesWithTag("Пароль")
            .onFirst().performTextInput("qwe")
        rule.onAllNodesWithTag("btn")
            .onFirst().performClick()
        rule.onAllNodesWithTag("dialog")
            .onFirst().performClick()
    }
}
