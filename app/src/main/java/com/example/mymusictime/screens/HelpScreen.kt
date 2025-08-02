package com.example.mymusictime.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mymusictime.R
import com.example.mymusictime.navigation.Screen
import com.example.mymusictime.ui.theme.ThemeUtils

@Composable
fun HelpScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("MyMusicTimePrefs", 0) }
    val colorTheme = remember { sharedPreferences.getString("color_theme", "orange") ?: "orange" }
    
    // Get theme colors
    val primaryColor = ThemeUtils.getPrimaryColor(colorTheme)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header with title and instruments icon
        Text(
            text = "Help & Tutorial",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor,
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
                .size(width = 300.dp, height = 160.dp)
                .padding(bottom = 24.dp)
        )

        // About the App Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF8D6E63)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "About My Music Time",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                Text(
                    text = "My Music Time is a practice tracker designed to help musicians stay accountable to their practice goals. Track your daily practice sessions, set weekly goals, and monitor your progress over time.",
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Text(
                    text = "Whether you're learning piano, guitar, violin, or any other instrument, this app helps you build consistent practice habits and achieve your musical goals.",
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 20.sp
                )
            }
        }

        // Main Features Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF8D6E63)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Main Features",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                Text(
                    text = "• Main Page: View your weekly practice progress and total minutes practiced\n\n• Log Session: Record daily practice sessions with minutes and notes\n\n• Preferences: Customize your app settings and practice goals\n\n• Help: Access tutorials and app information",
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 20.sp
                )
            }
        }

        // How to Use Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF8D6E63)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "How to Use the App",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                Text(
                    text = "1. Start by setting up your preferences (name, instrument, goals)\n\n2. Use the Log Session screen to record your daily practice\n\n3. Enter the number of minutes practiced for each day\n\n4. Add notes about what you worked on or any challenges\n\n5. Check your progress on the Main Page",
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 20.sp
                )
            }
        }

        // Preferences Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF8D6E63)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Preferences Settings",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                Text(
                    text = "User Profile:\n• Set your name and default instrument\n\nPractice Goals:\n• Set your weekly practice goal in minutes\n\nNotifications:\n• Enable daily practice reminders\n• Set reminder time\n\nAppearance:\n• Toggle dark mode on/off",
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 20.sp
                )
            }
        }

        // Tips Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF8D6E63)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Practice Tips",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                Text(
                    text = "• Set realistic weekly goals based on your schedule\n\n• Log your practice immediately after each session\n\n• Use the notes section to track what you worked on\n\n• Review your progress regularly to stay motivated\n\n• Consistency is more important than long practice sessions",
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

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
                    containerColor = primaryColor
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
                    containerColor = primaryColor
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
                    containerColor = primaryColor
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
                    containerColor = primaryColor
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
fun HelpPreview() {
    HelpScreen(navController = rememberNavController())
} 