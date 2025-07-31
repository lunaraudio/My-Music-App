package com.example.mymusictime.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
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
import android.content.Intent
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun MainPageScreen(navController: NavController, practiceViewModel: PracticeViewModel = viewModel()) {
    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("MyMusicTimePrefs", 0) }
    val userName = remember { sharedPreferences.getString("user_name", "") ?: "" }
    
    // Initialize PracticeViewModel with context for data persistence
    LaunchedEffect(Unit) {
        practiceViewModel.initialize(context)
    }
    
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
                .padding(bottom = 8.dp)
        )
        
        // Welcome message
        if (userName.isNotEmpty()) {
            Text(
                text = "Welcome back, $userName! 👋",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFFF8C00),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
        
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
                .padding(vertical = 16.dp),
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

        // User Preferences Display
        val defaultInstrument = sharedPreferences.getString("default_instrument", "Piano") ?: "Piano"
        val weeklyGoal = sharedPreferences.getInt("weekly_goal", 300)
        val goalProgress = if (weeklyGoal > 0) (practiceViewModel.totalMinutes.toFloat() / weeklyGoal.toFloat() * 100).toInt() else 0
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF8D6E63)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your Goals",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                // Instrument and Goal Progress
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Instrument: $defaultInstrument",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Weekly Goal: $weeklyGoal minutes",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "$goalProgress%",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (goalProgress >= 100) Color(0xFF4CAF50) else Color(0xFFFF8C00)
                        )
                        Text(
                            text = "Complete",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
                
                // Progress Bar
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = (goalProgress / 100f).coerceIn(0f, 1f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = if (goalProgress >= 100) Color(0xFF4CAF50) else Color(0xFFFF8C00),
                    trackColor = Color.White.copy(alpha = 0.3f)
                )
                
                // Motivational message
                if (goalProgress >= 100) {
                    Text(
                        text = "🎉 Amazing! You've reached your goal!",
                        fontSize = 12.sp,
                        color = Color(0xFF4CAF50),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                } else if (goalProgress >= 75) {
                    Text(
                        text = "🔥 Almost there! Keep going!",
                        fontSize = 12.sp,
                        color = Color(0xFFFF8C00),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                } else if (goalProgress >= 50) {
                    Text(
                        text = "💪 Halfway there! Great progress!",
                        fontSize = 12.sp,
                        color = Color(0xFFFF8C00),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                } else {
                    Text(
                        text = "🎵 Every minute counts! Keep practicing!",
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Share and Tutorial buttons
        val context = LocalContext.current
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    val shareText = "I practiced ${practiceViewModel.totalMinutes} minutes this week! 🎵 #MyMusicTime"
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, shareText)
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(sendIntent, "Share Practice Results"))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                ),
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Text(
                    text = "Share Progress",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
            
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = android.net.Uri.parse("https://www.youtube.com/results?search_query=music+tutorials")
                    }
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                ),
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Text(
                    text = "Tutorials",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }

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