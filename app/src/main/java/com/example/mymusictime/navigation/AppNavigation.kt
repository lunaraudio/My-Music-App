package com.example.mymusictime.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mymusictime.screens.MainPageScreen
import com.example.mymusictime.screens.LogSessionScreen

sealed class Screen(val route: String) {
    object MainPage : Screen("main_page")
    object LogSession : Screen("log_session")
    object Preferences : Screen("preferences")
    object Help : Screen("help")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainPage.route
    ) {
        composable(Screen.MainPage.route) {
            MainPageScreen(navController = navController)
        }
        composable(Screen.LogSession.route) {
            LogSessionScreen(navController = navController)
        }
        composable(Screen.Preferences.route) {
            // Placeholder - user settings screen coming in next phase
            MainPageScreen(navController = navController)
        }
        composable(Screen.Help.route) {
            // Placeholder - help and tutorial screen coming in next phase
            MainPageScreen(navController = navController)
        }
    }
} 