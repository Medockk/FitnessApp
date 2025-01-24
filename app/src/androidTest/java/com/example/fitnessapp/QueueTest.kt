package com.example.fitnessapp

import android.util.Log
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fitnessapp.feature_app.domain.usecase.Queue.CheckQueueUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Queue.ClearQueueUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Queue.GetQueueUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Queue.QueueUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Queue.SetQueueUseCase
import com.example.fitnessapp.feature_app.presentation.OnBoard.OnBoardItem
import com.example.fitnessapp.feature_app.presentation.OnBoard.OnBoardScreen
import com.example.fitnessapp.feature_app.presentation.OnBoard.OnBoardViewModel
import com.example.fitnessapp.feature_app.presentation.OnBoard.onBoardItemList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QueueTest {

    private lateinit var viewModel: OnBoardViewModel
    private lateinit var queueUseCase: QueueUseCase

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun clientInit(){
        val manger = QueueMangerImpl()

        queueUseCase = QueueUseCase(
            GetQueueUseCase(manger),
            SetQueueUseCase(manger),
            ClearQueueUseCase(manger),
            CheckQueueUseCase(manger)
        )

        viewModel = OnBoardViewModel(
            queueUseCase
        )


    }

    @Test
    fun u(){
        rule.setContent {
            val t = remember { mutableStateOf("") }
           Column {
               TextField(
                   value = t.value,
                   onValueChange = {
                       t.value = it
                   },
                   modifier = Modifier
                       .testTag("tf")
               )
               Button(
                   onClick = {

                   },
                   modifier = Modifier
                       .testTag("btn")
               ) { }
           }
        }

        rule.onAllNodesWithTag("tf").onFirst().performTextInput("qwerty")
        rule.onAllNodesWithTag("tf").onFirst().assertTextEquals("qwerty")
    }

    @Test
    fun firstTest(){
        runBlocking {
            queueUseCase.clearQueueUseCase()

            onBoardItemList = listOf(
                OnBoardItem(
                    R.drawable.onboard_screen1,
                    "test1",
                    "description1"
                ),
                OnBoardItem(
                    R.drawable.onboard_screen2,
                    "test2",
                    "description2"
                ),
                OnBoardItem(
                    R.drawable.onboard_screen2,
                    "test3",
                    "description3"
                ),
            )
            Log.e("m", onBoardItemList.toString())

            viewModel = OnBoardViewModel(
                queueUseCase
            )

        }

        rule.setContent {
            OnBoardScreen(
                navController = rememberNavController(),
                viewModel = viewModel
            )
        }

        rule.onAllNodesWithText("test1").onFirst().assertExists()
        rule.onAllNodesWithTag("btnNext").onFirst().performClick()
        rule.onAllNodesWithText("test2").onFirst().assertExists()
        rule.onAllNodesWithTag("btnNext").onFirst().performClick()
        rule.onAllNodesWithText("test3").onFirst().assertExists()
        rule.onAllNodesWithTag("btnNext").onFirst().performClick()
    }
}