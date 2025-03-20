@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.fitnessapp.feature_app.presentation.Notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitnessapp.feature_app.presentation.Notification.components.NotificationCard
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import kotlinx.coroutines.launch

@Composable
fun NotificationScreen(
    navController: NavController,
    viewModel: NotificationViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val coroutineScope = rememberCoroutineScope()
    val refreshState = rememberPullToRefreshState()

    if (state.exception.isNotEmpty()){
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(NotificationEvent.ResetException)
        }
    }

    PullToRefreshBox(
        isRefreshing = state.isRefreshing,
        onRefresh = {
            viewModel.onEvent(NotificationEvent.Refresh)
            if (!state.isRefreshing){
                coroutineScope.launch {
                    refreshState.animateToHidden()
                }
            }
        },
        state = refreshState,
        indicator = {
            Indicator(
                state = refreshState,
                isRefreshing = state.isRefreshing,
                modifier = Modifier
                    .align(Alignment.TopCenter),
                color = _228F7D
            )
        }
    ) {
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
    }

    CustomIndicator(state.showIndicator)
}