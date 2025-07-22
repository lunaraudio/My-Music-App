package com.example.mymusictime.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

@Composable
fun PreferencesScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("MyMusicTimePrefs", 0) }
    
    // Default values for preferences
    var userName by remember { 
        mutableStateOf(sharedPreferences.getString("user_name", "") ?: "") 
    }
    var defaultInstrument by remember { 
        mutableStateOf(sharedPreferences.getString("default_instrument", "Piano") ?: "Piano") 
    }
    var weeklyGoal by remember { 
        mutableStateOf(sharedPreferences.getInt("weekly_goal", 300).toString()) 
    }
    var reminderEnabled by remember { 
        mutableStateOf(sharedPreferences.getBoolean("reminder_enabled", false)) 
    }
    var reminderTime by remember { 
        mutableStateOf(sharedPreferences.getString("reminder_time", "18:00") ?: "18:00") 
    }
    var darkMode by remember { 
        mutableStateOf(sharedPreferences.getBoolean("dark_mode", true)) 
    }

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
            text = "Preferences",
            fontSize = 28.sp,
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
                .size(width = 300.dp, height = 160.dp)
                .padding(bottom = 24.dp)
        )

        // User Profile Section
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
                    text = "User Profile",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                // User Name
                Text(
                    text = "Your Name",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    value = userName,
                    onValueChange = { 
                        userName = it
                        sharedPreferences.edit().putString("user_name", it).apply()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFF8C00),
                        unfocusedBorderColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Default Instrument
                Text(
                    text = "Default Instrument",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    value = defaultInstrument,
                    onValueChange = { 
                        defaultInstrument = it
                        sharedPreferences.edit().putString("default_instrument", it).apply()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFF8C00),
                        unfocusedBorderColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
            }
        }

        // Practice Goals Section
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
                    text = "Practice Goals",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                // Weekly Goal
                Text(
                    text = "Weekly Practice Goal (minutes)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    value = weeklyGoal,
                    onValueChange = { 
                        weeklyGoal = it
                        sharedPreferences.edit().putInt("weekly_goal", it.toIntOrNull() ?: 300).apply()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFF8C00),
                        unfocusedBorderColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
            }
        }

        // Notifications Section
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
                    text = "Notifications",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                // Reminder Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Daily Practice Reminder",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Switch(
                        checked = reminderEnabled,
                        onCheckedChange = { 
                            reminderEnabled = it
                            sharedPreferences.edit().putBoolean("reminder_enabled", it).apply()
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFFFF8C00),
                            checkedTrackColor = Color(0xFFFF8C00).copy(alpha = 0.5f),
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color.White.copy(alpha = 0.5f)
                        )
                    )
                }
                
                if (reminderEnabled) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Reminder Time",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = reminderTime,
                        onValueChange = { 
                            reminderTime = it
                            sharedPreferences.edit().putString("reminder_time", it).apply()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFF8C00),
                            unfocusedBorderColor = Color.White,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )
                }
            }
        }

        // Appearance Section
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
                    text = "Appearance",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                // Dark Mode Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Dark Mode",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Switch(
                        checked = darkMode,
                        onCheckedChange = { 
                            darkMode = it
                            sharedPreferences.edit().putBoolean("dark_mode", it).apply()
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFFFF8C00),
                            checkedTrackColor = Color(0xFFFF8C00).copy(alpha = 0.5f),
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color.White.copy(alpha = 0.5f)
                        )
                    )
                }
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
fun PreferencesPreview() {
    PreferencesScreen(navController = rememberNavController())
} 