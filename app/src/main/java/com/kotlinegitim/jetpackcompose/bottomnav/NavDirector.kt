package com.kotlinegitim.jetpackcompose.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kotlinegitim.jetpackcompose.screen.HomeScreen
import com.kotlinegitim.jetpackcompose.screen.SettingScreen


@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBar.Home.route
    ) {
        composable(route = BottomBar.Home.route) {
            HomeScreen()

        }
        composable(route = BottomBar.List.route) {
            SettingScreen()

        }
    }
}