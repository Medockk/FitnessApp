package com.example.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.Route

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    BottomBar(
        Route.ProgressPhotoScreen, rememberNavController(),
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

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NavigationBar {
            repeat(5) { repeat ->
                NavigationBarItem(
                    selected = when (currentPage.route) {
                        Route.HomeScreen.route -> true
                        Route.ProgressPhotoScreen.route -> true
                        Route.ProfileScreen.route -> true
                        else -> false
                    },
                    onClick = {
                        if (
                            currentPage.route != Route.HomeScreen.route &&
                            currentPage.route != Route.ProgressPhotoScreen.route
                        ) {
                            navController.navigate(Route.ProfileScreen.route)
                        } else if (
                            currentPage.route != Route.HomeScreen.route &&
                            currentPage.route != Route.ProfileScreen.route
                        ) {
                            navController.navigate(Route.ProgressPhotoScreen.route)
                        } else if (
                            currentPage.route != Route.ProgressPhotoScreen.route &&
                            currentPage.route != Route.ProfileScreen.route
                        ) {
                            navController.navigate(Route.HomeScreen.route)
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (
                                repeat == 0 && currentPage.route == Route.HomeScreen.route
                            ){
                                ImageVector.vectorResource(R.drawable.home_selected__icon)
                            }else if (repeat == 3 && currentPage.route == Route.ProgressPhotoScreen.route){
                                ImageVector.vectorResource(R.drawable.camera_selected_icon)
                            }
                            else{
                                iconList[repeat]
                            },
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                )
            }
        }
    }
}