package com.example.mymusictime.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mymusictime.R
import com.example.mymusictime.navigation.Screen
import com.example.mymusictime.ui.theme.ThemeUtils

@Composable
fun LogSessionScreen(navController: NavController, practiceViewModel: PracticeViewModel = viewModel()) {
    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("MyMusicTimePrefs", 0) }
    val colorTheme = remember { sharedPreferences.getString("color_theme", "orange") ?: "orange" }
    
    // Get theme colors
    val primaryColor = ThemeUtils.getPrimaryColor(colorTheme)
    
    // Initialize PracticeViewModel with context for data persistence
    LaunchedEffect(Unit) {
        practiceViewModel.initialize(context)
    }
    
    // Load saved name and instrument from SharedPreferences
    var userName by remember { 
        mutableStateOf(TextFieldValue(sharedPreferences.getString("user_name", "") ?: "")) 
    }
    var instrumentType by remember { 
        mutableStateOf(TextFieldValue(sharedPreferences.getString("default_instrument", "") ?: "")) 
    }
    
    // Track practice time and notes for each day of the week using PracticeViewModel
    var mondayMinutes by remember { mutableStateOf(TextFieldValue("")) }
    var mondayNotes by remember { mutableStateOf(TextFieldValue("")) }
    var tuesdayMinutes by remember { mutableStateOf(TextFieldValue("")) }
    var tuesdayNotes by remember { mutableStateOf(TextFieldValue("")) }
    var wednesdayMinutes by remember { mutableStateOf(TextFieldValue("")) }
    var wednesdayNotes by remember { mutableStateOf(TextFieldValue("")) }
    var thursdayMinutes by remember { mutableStateOf(TextFieldValue("")) }
    var thursdayNotes by remember { mutableStateOf(TextFieldValue("")) }
    var fridayMinutes by remember { mutableStateOf(TextFieldValue("")) }
    var fridayNotes by remember { mutableStateOf(TextFieldValue("")) }
    var saturdayMinutes by remember { mutableStateOf(TextFieldValue("")) }
    var saturdayNotes by remember { mutableStateOf(TextFieldValue("")) }
    var sundayMinutes by remember { mutableStateOf(TextFieldValue("")) }
    var sundayNotes by remember { mutableStateOf(TextFieldValue("")) }
    
    // Load data from PracticeViewModel when it's initialized
    LaunchedEffect(practiceViewModel) {
        mondayMinutes = TextFieldValue(practiceViewModel.getMinutes("monday"))
        tuesdayMinutes = TextFieldValue(practiceViewModel.getMinutes("tuesday"))
        wednesdayMinutes = TextFieldValue(practiceViewModel.getMinutes("wednesday"))
        thursdayMinutes = TextFieldValue(practiceViewModel.getMinutes("thursday"))
        fridayMinutes = TextFieldValue(practiceViewModel.getMinutes("friday"))
        saturdayMinutes = TextFieldValue(practiceViewModel.getMinutes("saturday"))
        sundayMinutes = TextFieldValue(practiceViewModel.getMinutes("sunday"))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header with title
        Text(
            text = stringResource(R.string.log_my_music_time),
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

        // User Name and Instrument Type inputs
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF8D6E63)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.user_name),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                BasicTextField(
                    value = userName,
                    onValueChange = { 
                        userName = it
                        sharedPreferences.edit().putString("user_name", it.text).apply()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = stringResource(R.string.instrument_type),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                BasicTextField(
                    value = instrumentType,
                    onValueChange = { 
                        instrumentType = it
                        sharedPreferences.edit().putString("default_instrument", it.text).apply()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
            }
        }

        // Days of the week with input fields
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF8D6E63)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Monday
                DayInputRow(
                    dayName = stringResource(R.string.monday),
                    minutesValue = mondayMinutes,
                    onMinutesChange = { 
                        mondayMinutes = it
                        practiceViewModel.setMinutes("monday", it.text)
                    },
                    notesValue = mondayNotes,
                    onNotesChange = { mondayNotes = it }
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Tuesday
                DayInputRow(
                    dayName = stringResource(R.string.tuesday),
                    minutesValue = tuesdayMinutes,
                    onMinutesChange = { 
                        tuesdayMinutes = it
                        practiceViewModel.setMinutes("tuesday", it.text)
                    },
                    notesValue = tuesdayNotes,
                    onNotesChange = { tuesdayNotes = it }
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Wednesday
                DayInputRow(
                    dayName = stringResource(R.string.wednesday),
                    minutesValue = wednesdayMinutes,
                    onMinutesChange = { 
                        wednesdayMinutes = it
                        practiceViewModel.setMinutes("wednesday", it.text)
                    },
                    notesValue = wednesdayNotes,
                    onNotesChange = { wednesdayNotes = it }
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Thursday
                DayInputRow(
                    dayName = stringResource(R.string.thursday),
                    minutesValue = thursdayMinutes,
                    onMinutesChange = { 
                        thursdayMinutes = it
                        practiceViewModel.setMinutes("thursday", it.text)
                    },
                    notesValue = thursdayNotes,
                    onNotesChange = { thursdayNotes = it }
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Friday
                DayInputRow(
                    dayName = stringResource(R.string.friday),
                    minutesValue = fridayMinutes,
                    onMinutesChange = { 
                        fridayMinutes = it
                        practiceViewModel.setMinutes("friday", it.text)
                    },
                    notesValue = fridayNotes,
                    onNotesChange = { fridayNotes = it }
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Saturday
                DayInputRow(
                    dayName = stringResource(R.string.saturday),
                    minutesValue = saturdayMinutes,
                    onMinutesChange = { 
                        saturdayMinutes = it
                        practiceViewModel.setMinutes("saturday", it.text)
                    },
                    notesValue = saturdayNotes,
                    onNotesChange = { saturdayNotes = it }
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Sunday
                DayInputRow(
                    dayName = stringResource(R.string.sunday),
                    minutesValue = sundayMinutes,
                    onMinutesChange = { 
                        sundayMinutes = it
                        practiceViewModel.setMinutes("sunday", it.text)
                    },
                    notesValue = sundayNotes,
                    onNotesChange = { sundayNotes = it }
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

@Composable
fun DayInputRow(
    dayName: String,
    minutesValue: TextFieldValue,
    onMinutesChange: (TextFieldValue) -> Unit,
    notesValue: TextFieldValue,
    onNotesChange: (TextFieldValue) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = dayName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.width(100.dp)
        )
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Practice minutes input field
        BasicTextField(
            value = minutesValue,
            onValueChange = onMinutesChange,
            modifier = Modifier
                .weight(1f)
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(8.dp),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 14.sp,
                color = Color.Black
            ),
            decorationBox = { innerTextField ->
                Box {
                    if (minutesValue.text.isEmpty()) {
                        Text(
                            text = stringResource(R.string.minutes),
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            }
        )
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Practice session notes input field
        BasicTextField(
            value = notesValue,
            onValueChange = onNotesChange,
            modifier = Modifier
                .weight(1.5f)
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(8.dp),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 14.sp,
                color = Color.Black
            ),
            decorationBox = { innerTextField ->
                Box {
                    if (notesValue.text.isEmpty()) {
                        Text(
                            text = stringResource(R.string.notes),
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LogSessionPreview() {
    LogSessionScreen(navController = rememberNavController())
} 