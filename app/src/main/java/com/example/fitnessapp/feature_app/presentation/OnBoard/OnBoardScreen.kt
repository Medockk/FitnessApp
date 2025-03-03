package com.example.fitnessapp.feature_app.presentation.OnBoard

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.OnBoard.componets.OnBoardDefaultScreen
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

    LaunchedEffect(!state.isStart){
        if (state.isStart){
            viewModel.onEvent(OnBoardEvent.ChangeIsStartState)
            pagerState.requestScrollToPage(state.currentPage)
        }
    }

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
        flingBehavior = PagerDefaults.flingBehavior(
            state = pagerState,
            snapAnimationSpec = tween(
                700,
                easing = FastOutLinearInEasing
            )
        ),
    ) {
        if (state.currentPage != state.pagesCount){
            Crossfade(targetState = state.onBoardItem[pagerState.currentPage], animationSpec = tween(),) { onBoard ->
                OnBoardDefaultScreen(
                    onBoardItem = onBoard,
                    modifier = Modifier
                ) {
                    viewModel.onEvent(OnBoardEvent.NextPage(pagerState.currentPage+1))
                    pagerState.requestScrollToPage(
                        state.currentPage
                    )
                }
            }
        }
    }
}