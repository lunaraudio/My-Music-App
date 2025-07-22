package com.example.mymusictime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.mymusictime.navigation.AppNavigation
import com.example.mymusictime.ui.theme.MyMusicTimeTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mymusictime.screens.PracticeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMusicTimeTheme {
                val navController = rememberNavController()
                val practiceViewModel: PracticeViewModel = viewModel()
                AppNavigation(navController = navController, practiceViewModel = practiceViewModel)
            }
        }
    }
}