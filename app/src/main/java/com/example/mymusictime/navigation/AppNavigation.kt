package com.example.mymusictime.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mymusictime.screens.MainPageScreen
import com.example.mymusictime.screens.LogSessionScreen
import com.example.mymusictime.screens.PreferencesScreen
import com.example.mymusictime.screens.HelpScreen
import com.example.mymusictime.screens.PracticeViewModel

sealed class Screen(val route: String) {
    object MainPage : Screen("main_page")
    object LogSession : Screen("log_session")
    object Preferences : Screen("preferences")
    object Help : Screen("help")
}

@Composable
fun AppNavigation(navController: NavHostController, practiceViewModel: PracticeViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainPage.route
    ) {
        composable(Screen.MainPage.route) {
            MainPageScreen(navController = navController, practiceViewModel = practiceViewModel)
        }
        composable(Screen.LogSession.route) {
            LogSessionScreen(navController = navController, practiceViewModel = practiceViewModel)
        }
        composable(Screen.Preferences.route) {
            PreferencesScreen(navController = navController)
        }
        composable(Screen.Help.route) {
            HelpScreen(navController = navController)
        }
    }
} 