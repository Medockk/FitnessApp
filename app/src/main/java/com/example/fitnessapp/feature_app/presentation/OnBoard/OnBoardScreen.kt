package com.example.fitnessapp.feature_app.presentation.OnBoard

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.Route
import com.example.fitnessapp.feature_app.presentation.OnBoard.componets.OnBoardDefaultScreen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    OnBoardScreen(rememberNavController())
}

@Composable
fun OnBoardScreen(
    navController: NavController,
    viewModel: OnBoardViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val pagerState = rememberPagerState { state.pagesCount }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = !state.isComplete) {
        if (state.isComplete){
            navController.navigate(Route.SignInScreen.route){
                popUpTo(Route.OnBoardScreen.route){
                    inclusive = true
                }
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth(),
        userScrollEnabled = false,
        flingBehavior = PagerDefaults.flingBehavior(
            state = pagerState,
            snapAnimationSpec = tween(
                300,
                easing = FastOutLinearInEasing
            )
        )
    ) {
        if (state.currentPage != state.pagesCount){
            OnBoardDefaultScreen(
                onBoardItem = state.onBoardItem
            ) {
                coroutineScope.launch{
                    pagerState.animateScrollToPage(
                        state.currentPage,
                        animationSpec = tween(500, easing = FastOutLinearInEasing)
                    )
                }
                viewModel.onEvent(OnBoardEvent.NextPage(state.currentPage+1))
            }
        }
    }
}