package com.example.fitnessapp

import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QueueTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun emailValidateFailed(){
        rule.setContent {
           Column {
               val text = remember { mutableStateOf("") }
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
                        assert(Patterns.EMAIL_ADDRESS.matcher(text.value).matches())
                   },
                   modifier = Modifier
                       .testTag("btn")
               ) { }
           }
        }

        rule.onAllNodesWithTag("email")
            .onFirst().performTextInput("qwerty")
        rule.onAllNodesWithTag("btn").onFirst().performClick()
    }


}