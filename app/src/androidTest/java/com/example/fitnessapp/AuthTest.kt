package com.example.fitnessapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fitnessapp.feature_app.data.dao.UserDataDao
import com.example.fitnessapp.feature_app.data.dao.UserDataDaoImpl
import com.example.fitnessapp.feature_app.domain.dao.UserDao
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignUpUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignUpWithGoogleUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Dao.UpsertUserDataDaoUseCase
import com.example.fitnessapp.feature_app.presentation.SignUp.SignUpScreen
import com.example.fitnessapp.feature_app.presentation.SignUp.SignUpViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthTest {

    private lateinit var authRepository: AuthTestRepoImpl
    private lateinit var signUpUseCase: SignUpUseCase
    private lateinit var signUpWithGoogleUseCase: SignUpWithGoogleUseCase
    private lateinit var userDataDao: UserDataDao
    private lateinit var userDao: UserDao

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun clientInit() {
        authRepository = AuthTestRepoImpl()
        signUpUseCase = SignUpUseCase(authRepository)
        signUpWithGoogleUseCase = SignUpWithGoogleUseCase(authRepository)
    }

    @Test
    fun emailValidateFailed() {
        rule.setContent {
            val context = LocalContext.current
            userDataDao = UserDataDao.createDataBase(context)
            userDao = userDataDao.userDao
            SignUpScreen(
                rememberNavController(),
                SignUpViewModel(signUpUseCase, signUpWithGoogleUseCase, UpsertUserDataDaoUseCase(
                    UserDataDaoImpl(userDao)
                ))
            )
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
            val context = LocalContext.current
            userDataDao = UserDataDao.createDataBase(context)
            userDao = userDataDao.userDao
            SignUpScreen(
                rememberNavController(),
                SignUpViewModel(signUpUseCase, signUpWithGoogleUseCase, UpsertUserDataDaoUseCase(
                    UserDataDaoImpl(userDao)
                )
                )
            )
        }

        rule.onAllNodesWithTag("Почта").onFirst().performTextInput("qwerty@gmail.com")
        rule.onAllNodesWithTag("btn").onFirst().performClick()
    }

    @Test
    fun passwordValidateFailed() {
        rule.setContent {
            val context = LocalContext.current
            userDataDao = UserDataDao.createDataBase(context)
            userDao = userDataDao.userDao
            SignUpScreen(
                rememberNavController(),
                SignUpViewModel(signUpUseCase, signUpWithGoogleUseCase, UpsertUserDataDaoUseCase(UserDataDaoImpl(userDao)))
            )
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
            val context = LocalContext.current
            userDataDao = UserDataDao.createDataBase(context)
            userDao = userDataDao.userDao
            SignUpScreen(
                rememberNavController(),
                SignUpViewModel(signUpUseCase, signUpWithGoogleUseCase, UpsertUserDataDaoUseCase(UserDataDaoImpl(userDao)))
            )
        }
        rule.onAllNodesWithTag("Пароль")
            .onFirst().performTextInput("qW3eRt6Y")
        rule.onAllNodesWithTag("btn")
            .onFirst().performClick()
    }

    @Test
    fun authSuccessful() {
        rule.setContent {
            val context = LocalContext.current
            userDataDao = UserDataDao.createDataBase(context)
            userDao = userDataDao.userDao
            SignUpScreen(
                rememberNavController(),
                SignUpViewModel(signUpUseCase, signUpWithGoogleUseCase, UpsertUserDataDaoUseCase(UserDataDaoImpl(userDao)))
            )
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
            val context = LocalContext.current
            userDataDao = UserDataDao.createDataBase(context)
            userDao = userDataDao.userDao
            SignUpScreen(
                rememberNavController(),
                SignUpViewModel(signUpUseCase, signUpWithGoogleUseCase, UpsertUserDataDaoUseCase(UserDataDaoImpl(userDao)))
            )
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
