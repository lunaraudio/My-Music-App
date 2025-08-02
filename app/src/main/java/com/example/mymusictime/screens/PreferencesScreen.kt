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
import com.example.mymusictime.ui.theme.ThemeUtils

@Composable
fun PreferencesScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("MyMusicTimePrefs", 0) }
    val colorTheme = remember { sharedPreferences.getString("color_theme", "orange") ?: "orange" }
    
    // Get theme colors
    val primaryColor = ThemeUtils.getPrimaryColor(colorTheme)
    
    // Default values for preferences
    var weeklyGoal by remember { 
        mutableStateOf(sharedPreferences.getInt("weekly_goal", 300).toString()) 
    }
    var reminderEnabled by remember { 
        mutableStateOf(sharedPreferences.getBoolean("reminder_enabled", false)) 
    }
    var reminderTime by remember { 
        mutableStateOf(sharedPreferences.getString("reminder_time", "18:00") ?: "18:00") 
    }
    var selectedColorTheme by remember { 
        mutableStateOf(sharedPreferences.getString("color_theme", "orange") ?: "orange") 
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
                        focusedBorderColor = primaryColor,
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
                            checkedThumbColor = primaryColor,
                            checkedTrackColor = primaryColor.copy(alpha = 0.5f),
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
                            focusedBorderColor = primaryColor,
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
                
                // Color Theme Selection
                Text(
                    text = "Color Theme",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                // Theme Options
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Orange Theme
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedColorTheme == "orange",
                            onClick = { 
                                selectedColorTheme = "orange"
                                sharedPreferences.edit().putString("color_theme", "orange").apply()
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFFFF8C00),
                                unselectedColor = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Classic Orange",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = if (selectedColorTheme == "orange") FontWeight.Bold else FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(Color(0xFFFF8C00), RoundedCornerShape(4.dp))
                        )
                    }
                    
                    // Blue Theme
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedColorTheme == "blue",
                            onClick = { 
                                selectedColorTheme = "blue"
                                sharedPreferences.edit().putString("color_theme", "blue").apply()
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF2196F3),
                                unselectedColor = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Ocean Blue",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = if (selectedColorTheme == "blue") FontWeight.Bold else FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(Color(0xFF2196F3), RoundedCornerShape(4.dp))
                        )
                    }
                    
                    // Green Theme
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedColorTheme == "green",
                            onClick = { 
                                selectedColorTheme = "green"
                                sharedPreferences.edit().putString("color_theme", "green").apply()
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF4CAF50),
                                unselectedColor = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Forest Green",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = if (selectedColorTheme == "green") FontWeight.Bold else FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(Color(0xFF4CAF50), RoundedCornerShape(4.dp))
                        )
                    }
                    
                    // Purple Theme
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedColorTheme == "purple",
                            onClick = { 
                                selectedColorTheme = "purple"
                                sharedPreferences.edit().putString("color_theme", "purple").apply()
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF9C27B0),
                                unselectedColor = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Sunset Purple",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = if (selectedColorTheme == "purple") FontWeight.Bold else FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(Color(0xFF9C27B0), RoundedCornerShape(4.dp))
                        )
                    }
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
fun PreferencesPreview() {
    PreferencesScreen(navController = rememberNavController())
} 