package com.example.mymusictime.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PracticeViewModel : ViewModel() {
    var mondayMinutes by mutableStateOf("")
    var tuesdayMinutes by mutableStateOf("")
    var wednesdayMinutes by mutableStateOf("")
    var thursdayMinutes by mutableStateOf("")
    var fridayMinutes by mutableStateOf("")
    var saturdayMinutes by mutableStateOf("")
    var sundayMinutes by mutableStateOf("")

    val totalMinutes: Int
        get() = listOf(
            mondayMinutes,
            tuesdayMinutes,
            wednesdayMinutes,
            thursdayMinutes,
            fridayMinutes,
            saturdayMinutes,
            sundayMinutes
        ).sumOf { it.toIntOrNull() ?: 0 }

    fun setMinutes(day: String, value: String) {
        when (day) {
            "monday" -> mondayMinutes = value
            "tuesday" -> tuesdayMinutes = value
            "wednesday" -> wednesdayMinutes = value
            "thursday" -> thursdayMinutes = value
            "friday" -> fridayMinutes = value
            "saturday" -> saturdayMinutes = value
            "sunday" -> sundayMinutes = value
        }
    }

    fun getMinutes(day: String): String {
        return when (day) {
            "monday" -> mondayMinutes
            "tuesday" -> tuesdayMinutes
            "wednesday" -> wednesdayMinutes
            "thursday" -> thursdayMinutes
            "friday" -> fridayMinutes
            "saturday" -> saturdayMinutes
            "sunday" -> sundayMinutes
            else -> ""
        }
    }
} 