package com.example.fitnessapp.feature_app.presentation.Notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.common.CustomAlertDialog
import com.example.common.CustomIndicator
import com.example.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.Notification.components.NotificationCard
import com.example.fitnessapp.ui.theme._F7F8F8
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    NotificationScreen(rememberNavController())
}

@Composable
fun NotificationScreen(
    navController: NavController,
    viewModel: NotificationViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    if (state.exception.isNotEmpty()){
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(NotificationEvent.ResetException)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopAppBar(
            title = "Уведомления",
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = _F7F8F8,
            moreInformationClick = {},
            textColor = Color.Black
        ) {
            navController.popBackStack()
        }

        Spacer(Modifier.height(30.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(state.notifications){notification ->
                NotificationCard(
                    image = notification.image,
                    title = notification.title,
                    data = notification.data,
                    modifier = Modifier
                        .fillParentMaxWidth()
                ) { }
            }
        }
    }

    CustomIndicator(state.showIndicator)
}