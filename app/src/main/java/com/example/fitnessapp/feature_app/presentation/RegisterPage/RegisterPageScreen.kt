package com.example.fitnessapp.feature_app.presentation.RegisterPage

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.common.CustomAlertDialog
import com.example.common.CustomGreenButton
import com.example.fitnessapp.Route
import com.example.fitnessapp.ui.theme._07856E
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme._81CCBF
import com.example.fitnessapp.ui.theme._9CEEDF
import com.example.fitnessapp.ui.theme.montserrat40012White
import com.example.fitnessapp.ui.theme.montserrat40012_7B6F72
import com.example.fitnessapp.ui.theme.montserrat60014White
import com.example.fitnessapp.ui.theme.montserrat70020_1D1617
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    RegisterPageScreen(rememberNavController())
}

@Composable
fun RegisterPageScreen(
    navController: NavController,
    viewModel: RegisterPageViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    val paddingTop = LocalConfiguration.current.screenHeightDp / 15
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 20

    val pagerState = rememberPagerState { state.yourPurpose.size }
    val horizontalBoxBrush = Brush.linearGradient(
        listOf(
            _228F7D,
            _9CEEDF
        )
    )
    val centerCardBrush = Brush.linearGradient(
        listOf(
            _81CCBF,
            _07856E
        )
    )

    LaunchedEffect(key1 = !state.isComplete) {
        if (state.isComplete){
            navController.navigate(Route.SuccessRegistrationScreen.route){
                popUpTo(Route.RegisterPageScreen.route){
                    inclusive = true
                }
            }
        }
    }

    if (state.exception.isNotEmpty()){
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(RegisterEvent.ResetException)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                top = paddingTop.dp,
                bottom = paddingBottom.dp
            ),
        flingBehavior = PagerDefaults.flingBehavior(
            state = pagerState,
            snapAnimationSpec = tween(500, easing = FastOutLinearInEasing),

        )
    ) { page ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Какова ваша цель?",
                    style = montserrat70020_1D1617
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Это поможет нам подобрать\nдля вас лучшую программу.",
                    style = montserrat40012_7B6F72
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .background(horizontalBoxBrush, RoundedCornerShape(
                            topEnd = 22.dp,
                            bottomEnd = 22.dp
                        ), 0.3f)
                        .height(300.dp)
                        .width(30.dp)
                )
                Card(
                    modifier = Modifier
                        .background(centerCardBrush, RoundedCornerShape(22.dp))
                        .width((LocalConfiguration.current.screenWidthDp/1.5).dp)
                        .height((LocalConfiguration.current.screenHeightDp/1.6).dp),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(state.yourPurpose[page].image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = state.yourPurpose[page].title,
                                style = montserrat60014White,
                                textAlign = TextAlign.Center
                            )
                            Spacer(Modifier.height(20.dp))
                            Text(
                                text = state.yourPurpose[page].description,
                                style = montserrat40012White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .background(horizontalBoxBrush, RoundedCornerShape(
                            topStart = 22.dp,
                            bottomStart = 22.dp
                        ), 0.3f)
                        .width(30.dp)
                        .height(300.dp)
                )
            }

            CustomGreenButton(
                text = "Подтвердить",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                viewModel.onEvent(RegisterEvent.SelectPurpose(page))
            }
        }
    }
}