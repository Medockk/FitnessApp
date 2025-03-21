@file:Suppress("UNCHECKED_CAST")

package com.example.fitnessapp.feature_app.presentation.Profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.presentation.Profile.components.UserAccountCard
import com.example.fitnessapp.feature_app.presentation.Profile.components.UserDataCard
import com.example.fitnessapp.feature_app.presentation.Route
import com.example.fitnessapp.feature_app.presentation.common.BottomBar
import com.example.fitnessapp.feature_app.presentation.common.CustomAlertDialog
import com.example.fitnessapp.feature_app.presentation.common.CustomGreenButton
import com.example.fitnessapp.feature_app.presentation.common.CustomIndicator
import com.example.fitnessapp.feature_app.presentation.common.CustomSwitch
import com.example.fitnessapp.feature_app.presentation.common.CustomTopAppBar
import com.example.fitnessapp.feature_app.presentation.ui.theme._1D161712
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._C4C4C4
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40014_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014_1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat60016Bold_1D1617

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    val context = LocalContext.current
    val selectImage = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {
            viewModel.onEvent(ProfileEvent.ChangeImageView(it))
            val item = context.contentResolver.openInputStream(it)
            viewModel.onEvent(ProfileEvent.ChangeImage(item?.readBytes()))
            item?.close()
        }
    }

    val accountCategoryList = listOf(
        listOf(
            ImageVector.vectorResource(R.drawable.profile_icon),
            "Данные аккаунта",
            {}
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.achievement_icon),
            "Достижения",
            {}
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.activity_icon),
            "История активности",
            {}
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.progress_icon),
            "Прогресс занятий",
            {}
        ),
    )
    val otherCategoryList = listOf(
        listOf(
            ImageVector.vectorResource(R.drawable.message_icon),
            "Контакты",
            {}
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.privacy_icon),
            "Политика кондефициальности",
            {}
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.setting_icon),
            "Настройки",
            {}
        ),
    )

    if (state.exception.isNotEmpty()) {
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(ProfileEvent.ResetException)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            CustomTopAppBar(
                title = "Профиль",
                moreInformationClick = {
                    navController.navigate(Route.SleepTrackerScreen.route)
                },
                backgroundColor = _F7F8F8,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                textColor = Color.Black
            ) {
                navController.popBackStack()
            }
        },
        bottomBar = {
            BottomBar(
                currentPage = Route.ProfileScreen,
                navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 30.dp)
        ) {
            item {
                Column {
                    Spacer(Modifier.height(30.dp))

                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .size(55.dp)
                                .clip(CircleShape)
                                .background(_C4C4C4, CircleShape),
                            contentAlignment = Alignment.Center,
                        ) {
                            androidx.compose.animation.AnimatedVisibility(
                                visible = state.image.isNotEmpty(),
                                enter = fadeIn(tween(1000))
                            ) {
                                AsyncImage(
                                    model = state.image,
                                    contentDescription = "your image",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(5.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = state.userData?.fio ?: "",
                                style = montserrat50014_1D1617
                            )
                            Spacer(Modifier.height(5.dp))
                            Text(
                                text = state.purpose,
                                style = montserrat40014_B6B4C2
                            )
                        }
                        CustomGreenButton(
                            text = "Изменить",
                            enabled = !state.showIndicator
                        ) {
                            selectImage.launch(
                                "image/*"
                            )
                        }
                    }

                    Spacer(Modifier.height(15.dp))

                    LazyRow(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        item {
                            Row {
                                Spacer(Modifier.width(5.dp))
                                UserDataCard(
                                    title = if (state.userData != null) state.userData.height else "",
                                    description = "Рост",
                                    modifier = Modifier
                                        .fillParentMaxWidth(0.3f)
                                )
                            }
                        }
                        item {
                            UserDataCard(
                                title = if (state.userData != null) state.userData.weight else "",
                                description = "Вес",
                                modifier = Modifier
                                    .fillParentMaxWidth(0.3f)
                            )
                        }
                        item {
                            Row {
                                UserDataCard(
                                    title = if (state.userData != null) state.userData.birthdayData else "",
                                    description = "Лет",
                                    modifier = Modifier
                                        .fillParentMaxWidth(0.3f)
                                )
                                Spacer(Modifier.width(5.dp))
                            }
                        }
                    }

                    Spacer(Modifier.height(30.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(10.dp, RoundedCornerShape(16.dp), spotColor = _1D161712),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            Text(
                                text = "Аккаунт",
                                style = montserrat60016Bold_1D1617
                            )
                            accountCategoryList.forEach { account ->
                                UserAccountCard(
                                    icon = account[0] as ImageVector,
                                    title = account[1] as String,
                                    onClick = account[2] as () -> Unit,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(15.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(10.dp, RoundedCornerShape(16.dp), spotColor = _1D161712),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Уведомления",
                                style = montserrat60016Bold_1D1617
                            )
                            Spacer(Modifier.height(10.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.notification_icon),
                                    contentDescription = "notification",
                                    tint = _228F7D
                                )
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    text = "Уведомления",
                                    style = montserrat40012_B6B4C2
                                )
                                Spacer(Modifier.weight(1f))
                                CustomSwitch(
                                    checked = state.isNotificationTurnOn
                                ) { switchState ->
                                    viewModel.onEvent(
                                        ProfileEvent.ChangeNotificationState(
                                            switchState
                                        )
                                    )
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(15.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(10.dp, RoundedCornerShape(16.dp), spotColor = _1D161712),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            Text(
                                text = "Остальное",
                                style = montserrat60016Bold_1D1617
                            )
                            otherCategoryList.forEach { other ->
                                UserAccountCard(
                                    icon = other[0] as ImageVector,
                                    title = other[1] as String,
                                    onClick = other[2] as () -> Unit,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(15.dp))
            }
        }
    }

    CustomIndicator(state.showIndicator)
}