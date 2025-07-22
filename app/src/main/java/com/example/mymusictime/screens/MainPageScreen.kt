package com.example.mymusictime.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mymusictime.R
import com.example.mymusictime.navigation.Screen
import com.example.mymusictime.screens.PracticeViewModel

@Composable
fun MainPageScreen(navController: NavController, practiceViewModel: PracticeViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header with app name
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF8C00),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        
        // Centered instruments icon
        Image(
            painter = painterResource(id = R.drawable.ic_instruments),
            contentDescription = "Instruments",
            modifier = Modifier
                .size(width = 360.dp, height = 240.dp)
                .padding(bottom = 32.dp)
        )

        // Minutes practiced this week display
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFF8C00)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = practiceViewModel.totalMinutes.toString(),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = stringResource(R.string.minutes_practiced_this_week),
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom navigation buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { navController.navigate(Screen.MainPage.route) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF8C00)
                ),
                modifier = Modifier.weight(1f).padding(end = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.main_page),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
            
            Button(
                onClick = { navController.navigate(Screen.LogSession.route) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF8C00)
                ),
                modifier = Modifier.weight(1.2f).padding(horizontal = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.log_session),
                    color = Color.White,
                    fontSize = 11.sp
                )
            }
            
            Button(
                onClick = { navController.navigate(Screen.Preferences.route) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF8C00)
                ),
                modifier = Modifier.weight(1f).padding(horizontal = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.preferences),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
            
            Button(
                onClick = { navController.navigate(Screen.Help.route) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF8C00)
                ),
                modifier = Modifier.weight(1f).padding(start = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.help),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    MainPageScreen(navController = rememberNavController())
} 