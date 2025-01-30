package com.example.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.Route
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme._9CEEDF

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    BottomBar(
        Route.ProfileScreen, rememberNavController(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp)
    )
}

@Composable
fun BottomBar(
    currentPage: Route,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val iconList = listOf(
        ImageVector.vectorResource(R.drawable.home_icon),
        ImageVector.vectorResource(R.drawable.activity_icon),
        Icons.Default.Search,
        ImageVector.vectorResource(R.drawable.camera_icon),
        ImageVector.vectorResource(R.drawable.profile_icon),
    )
    val brush = Brush.linearGradient(
        listOf(
            _228F7D,
            _9CEEDF
        )
    )

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NavigationBar(
            containerColor = Color.White
        ) {
            repeat(5) { repeat ->
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        if (
                            repeat == 4 && currentPage.route != Route.ProfileScreen.route
                        ) {
                            navController.navigate(Route.ProfileScreen.route)
                        } else if (
                            repeat == 3 && currentPage.route != Route.ProgressPhotoScreen.route
                        ) {
                            navController.navigate(Route.ProgressPhotoScreen.route)
                        } else if (
                            repeat == 0 && currentPage.route != Route.HomeScreen.route
                        ) {
                            navController.navigate(Route.HomeScreen.route)
                        }else if (
                            repeat == 1 && currentPage.route != Route.WorkoutTrackerScreen.route
                        ){
                            navController.navigate(Route.WorkoutTrackerScreen.route)
                        }else if (
                            repeat == 2
                        ){
                            navController.navigate(Route.CategoryBreakfastScreen.route)
                        }
                    },
                    icon = {
                        if (repeat == 2){
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(brush, CircleShape)
                                    .clickable {
                                        navController.navigate(Route.CategoryBreakfastScreen.route)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = iconList[repeat],
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .padding(15.dp)
                                )
                            }
                        }else{
                            Icon(
                                imageVector = if (
                                    repeat == 0 && currentPage.route == Route.HomeScreen.route
                                ){
                                    ImageVector.vectorResource(R.drawable.home_selected__icon)
                                }else if (repeat == 3 && currentPage.route == Route.ProgressPhotoScreen.route){
                                    ImageVector.vectorResource(R.drawable.camera_selected_icon)
                                }else if (repeat == 4 && currentPage.route == Route.ProfileScreen.route){
                                    ImageVector.vectorResource(R.drawable.profile_selected_icon)
                                }
                                else{
                                    iconList[repeat]
                                },
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Transparent,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}