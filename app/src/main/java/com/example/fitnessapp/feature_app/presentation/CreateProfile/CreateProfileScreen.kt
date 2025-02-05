package com.example.fitnessapp.feature_app.presentation.CreateProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.common.CustomAlertDialog
import com.example.common.CustomGreenButton
import com.example.common.CustomIndicator
import com.example.fitnessapp.R
import com.example.fitnessapp.Route
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme._7B6F72
import com.example.fitnessapp.ui.theme._9CEEDF
import com.example.fitnessapp.ui.theme._ADA4A5
import com.example.fitnessapp.ui.theme._F7F8F8
import com.example.fitnessapp.ui.theme.montserrat40012White
import com.example.fitnessapp.ui.theme.montserrat40012_7B6F72
import com.example.fitnessapp.ui.theme.montserrat40012_ADA4A5
import com.example.fitnessapp.ui.theme.montserrat50012White
import com.example.fitnessapp.ui.theme.montserrat70020_1D1617
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    CreateProfileScreen(rememberNavController())
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateProfileScreen(
    navController: NavController,
    viewModel: CreateProfileViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 20
    val textFieldList = listOf(
        listOf(
            ImageVector.vectorResource(R.drawable.gender_icon),
            "Ваш пол",
            state.gender,
            {it: String -> viewModel.onEvent(CreateProfileEvent.EnterGender(it))},
            true,
            false,
            false
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.birthday_data_icon),
            "Дата рождения",
            state.birthdayData,
            {it: String -> viewModel.onEvent(CreateProfileEvent.EnterBirthdayData(it))},
            false,
            false,
            false
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.weight_scale_icon),
            "Ваш вес",
            state.weight,
            {it: String -> viewModel.onEvent(CreateProfileEvent.EnterWeight(it))},
            false,
            true,
            false
        ),
        listOf(
            ImageVector.vectorResource(R.drawable.height_icon),
            "Ваш рост",
            state.height,
            {it: String -> viewModel.onEvent(CreateProfileEvent.EnterHeight(it))},
            false,
            false,
            true
        ),
    )
    val brush = Brush.linearGradient(
        listOf(
            _228F7D,
            _9CEEDF
        )
    )

    if (state.exception.isNotEmpty()){
        CustomAlertDialog(
            description = state.exception
        ) {
            viewModel.onEvent(CreateProfileEvent.ResetException)
        }
    }

    LaunchedEffect(key1 = !state.isComplete) {
        if (state.isComplete){
            navController.navigate(Route.RegisterPageScreen.route){
                popUpTo(Route.CreateProfileScreen.route){
                    inclusive = true
                }
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                bottom = paddingBottom.dp,
                start = 20.dp, end = 20.dp
            )
            .imePadding()
            .imeNestedScroll(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        item{
            Image(
                painter = painterResource(R.drawable.registration_page_image),
                contentDescription = "create your profile",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        item {
            Column(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Давайте создадим\n" +
                            "ваш профиль",
                    style = montserrat70020_1D1617,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Это поможет нам узнать о вас больше!",
                    style = montserrat40012_7B6F72,
                    textAlign = TextAlign.Center
                )
            }
        }
        items(textFieldList) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                TextField(
                    value = it[2] as String,
                    onValueChange = it[3] as (String) -> Unit,
                    label = {
                        Text(
                            text = it[1] as String,
                            style = montserrat40012_ADA4A5
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = it[0] as ImageVector,
                            contentDescription = null,
                            tint = _7B6F72
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = _F7F8F8,
                        unfocusedContainerColor = _F7F8F8,
                    ),
                    trailingIcon = {
                        if (it[4] as Boolean){
                            IconButton(
                                onClick = {
                                    viewModel.onEvent(CreateProfileEvent.ChangeDropDownMenuState)
                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color.Transparent, CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    tint = _ADA4A5
                                )
                            }
                            DropdownMenu(
                                expanded = state.isDropDownMenuOpen,
                                onDismissRequest = {
                                    viewModel.onEvent(CreateProfileEvent.ChangeDropDownMenuState)
                                },
                                modifier = Modifier
                                    .background(brush)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .padding(10.dp)
                                ) {
                                    Text(
                                        text = UserData.male,
                                        style = montserrat40012White,
                                        modifier = Modifier
                                            .clickable {
                                                viewModel.onEvent(CreateProfileEvent.EnterGender(UserData.male))
                                                viewModel.onEvent(CreateProfileEvent.ChangeDropDownMenuState)
                                            }
                                    )
                                    Spacer(Modifier.height(10.dp))
                                    Text(
                                        text = UserData.female,
                                        style = montserrat40012White,
                                        modifier = Modifier
                                            .clickable {
                                                viewModel.onEvent(CreateProfileEvent.EnterGender(UserData.female))
                                                viewModel.onEvent(CreateProfileEvent.ChangeDropDownMenuState)
                                            }
                                    )
                                }
                            }
                        }
                    },
                    shape = RoundedCornerShape(100.dp),
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                )
                if (it[5] as Boolean || it[6] as Boolean){
                    Spacer(Modifier.width(10.dp))
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(brush, CircleShape)
                    ) {
                        Text(
                            text = if (it[5] as Boolean) "КГ" else "СМ",
                            style = montserrat50012White
                        )
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
        }

        item {
            CustomGreenButton(
                text = "Далее",
                isNextButton = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                viewModel.onEvent(CreateProfileEvent.CreateProfileClick)
            }
        }
    }

    CustomIndicator(state.showIndicator)
}