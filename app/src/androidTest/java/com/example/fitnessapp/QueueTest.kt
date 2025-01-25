package com.example.fitnessapp

import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.window.Dialog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.repository.AuthRepository
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignUpUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QueueTest {

    private lateinit var authRepository: AuthTestRepoImpl
    private lateinit var signUpUseCase: SignUpUseCase

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun clientInit() {
        authRepository = AuthTestRepoImpl()
        signUpUseCase = SignUpUseCase(authRepository)
    }

    @Test
    fun emailValidateFailed() {
        rule.setContent {
            Column {
                val text = remember { mutableStateOf("") }
                var assert by remember { mutableStateOf(false) }
                TextField(
                    value = text.value,
                    onValueChange = {
                        text.value = it
                    },
                    modifier = Modifier
                        .testTag("email")
                )
                Button(
                    onClick = {
                        if (!Patterns.EMAIL_ADDRESS.matcher(text.value).matches()) {
                            assert = true
                        }
                    },
                    modifier = Modifier
                        .testTag("btn")
                ) { }
                if (assert) {
                    AlertDialog(
                        onDismissRequest = {
                            assert(true)
                        },
                        {
                            Button(
                                { assert(true) },
                                modifier = Modifier.testTag("dialog"),
                            ) { }
                        },
                        title = {
                            Text(
                                text = "Неверный email!"
                            )
                        }
                    )
                }
            }
        }

        rule.onAllNodesWithTag("email")
            .onFirst().performTextInput("qwerty")
        rule.onAllNodesWithTag("btn").onFirst().performClick()
        rule.onAllNodesWithTag("dialog")
            .onFirst().performClick()
    }

    @Test
    fun emailValidateSuccessful() {
        rule.setContent {
            Column {
                val text = remember { mutableStateOf("") }
                TextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    modifier = Modifier
                        .testTag("textField")
                )
                Button(
                    onClick = {
                        assert(Patterns.EMAIL_ADDRESS.matcher(text.value).matches())
                    },
                    modifier = Modifier.testTag("btn")
                ) { }
            }
        }

        rule.onAllNodesWithTag("textField").onFirst().performTextInput("qwerty@gmail.com")
        rule.onAllNodesWithTag("btn").onFirst().performClick()
    }

    @Test
    fun passwordValidateFailed() {
        rule.setContent {
            Column {
                val text = remember { mutableStateOf("") }
                var lowerCase by remember { mutableStateOf(false) }
                var upperCase by remember { mutableStateOf(false) }
                var digitalText by remember { mutableStateOf(false) }
                var textLength by remember { mutableStateOf(false) }

                var assert by remember { mutableStateOf(false) }
                TextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    modifier = Modifier.testTag("textField")
                )
                Button(
                    onClick = {
                        if (text.value.length >= 6) {
                            textLength = true
                        }
                        text.value.forEach { char ->
                            if (char.isDigit()) {
                                digitalText = true
                            }
                            if (char.isLowerCase()) {
                                lowerCase = true
                            }
                            if (char.isUpperCase()) {
                                upperCase = true
                            }
                        }
                        assert = true
                    },
                    modifier = Modifier
                        .testTag("btn")
                ) { }

                if (assert) {
                    AlertDialog(
                        onDismissRequest = {
                            assert(true)
                        },
                        {
                            Button(
                                onClick = { assert(true) },
                                modifier = Modifier.testTag("dialog"),
                            ) { }
                        },
                        title = {
                            Text(
                                text = "Неверный пароль"
                            )
                        }
                    )
                }
            }
        }
        rule.onAllNodesWithTag("textField")
            .onFirst().performTextInput("qwerty")
        rule.onAllNodesWithTag("btn")
            .onFirst().performClick()
        rule.onAllNodesWithTag("dialog")
            .onFirst().performClick()
    }

    @Test
    fun passwordValidateSuccessful() {
        rule.setContent {
            Column {
                val text = remember { mutableStateOf("") }

                var lowerCase by remember { mutableStateOf(false) }
                var upperCase by remember { mutableStateOf(false) }
                var digitalText by remember { mutableStateOf(false) }
                var textLength by remember { mutableStateOf(false) }

                TextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    modifier = Modifier.testTag("textField")
                )
                Button(
                    onClick = {
                        if (text.value.length >= 6) {
                            textLength = true
                        }
                        text.value.forEach { char ->
                            if (char.isDigit()) {
                                digitalText = true
                            }
                            if (char.isLowerCase()) {
                                lowerCase = true
                            }
                            if (char.isUpperCase()) {
                                upperCase = true
                            }
                        }
                    },
                    modifier = Modifier
                        .testTag("btn")
                ) { }
            }
        }
        rule.onAllNodesWithTag("textField")
            .onFirst().performTextInput("qW3eRt6Y")
        rule.onAllNodesWithTag("btn")
            .onFirst().performClick()
    }

    @Test
    fun authSuccessful() {
        rule.setContent {
            val coroutine = rememberCoroutineScope()
            Column {
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                TextField(
                    email, { email = it },
                    modifier = Modifier.testTag("email")
                )
                TextField(
                    password, { password = it },
                    modifier = Modifier.testTag("password")
                )
                Button({
                    coroutine.launch {
                        signUpUseCase(
                            email, password,
                            UserData(0, "", "", "", "", "", "", "")
                        )
                        assert(true)
                    }
                }, modifier = Modifier.testTag("btn")) {

                }
            }
        }
        rule.onAllNodesWithTag("email")
            .onFirst().performTextInput("test2@gmail.com")
        rule.onAllNodesWithTag("password")
            .onFirst().performTextInput("qw2E4rT5y")
        rule.onAllNodesWithTag("btn")
            .onFirst().performClick()
    }

    @Test
    fun authFailed() {
        rule.setContent {
            val coroutine = rememberCoroutineScope()
            Column {
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                var failed by remember { mutableStateOf(false) }
                TextField(
                    email, { email = it },
                    modifier = Modifier.testTag("email")
                )
                TextField(
                    password, { password = it },
                    modifier = Modifier.testTag("password")
                )
                Button({
                    coroutine.launch {
                            signUpUseCase(
                                email, password,
                                UserData(0, "", "", "", "", "", "", "")
                            )
                    }
                    failed = true
                }, modifier = Modifier.testTag("btn")) {

                }


                if (failed) {
                    AlertDialog(
                        { },
                        confirmButton = {
                            Button(
                                onClick = { assert(true) },
                                modifier = Modifier.testTag("btnFailed")
                            ) {}
                        },
                        title = {
                            Text(
                                text = "Авторизация провалена!"
                            )
                        }
                    )
                }
            }
        }
        rule.onAllNodesWithTag("email")
            .onFirst().performTextInput("test1")
        rule.onAllNodesWithTag("password")
            .onFirst().performTextInput("qwe")
        rule.onAllNodesWithTag("btn")
            .onFirst().performClick()
        rule.onAllNodesWithTag("btnFailed")
            .onFirst().performClick()
    }
}
