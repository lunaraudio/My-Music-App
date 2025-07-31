package com.example.mymusictime.screens

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PracticeViewModel : ViewModel() {
    private var context: Context? = null
    
    var mondayMinutes by mutableStateOf("")
    var tuesdayMinutes by mutableStateOf("")
    var wednesdayMinutes by mutableStateOf("")
    var thursdayMinutes by mutableStateOf("")
    var fridayMinutes by mutableStateOf("")
    var saturdayMinutes by mutableStateOf("")
    var sundayMinutes by mutableStateOf("")
    
    fun initialize(context: Context) {
        this.context = context
        loadData()
    }
    
    private fun loadData() {
        context?.let { ctx ->
            val sharedPreferences = ctx.getSharedPreferences("PracticeData", Context.MODE_PRIVATE)
            mondayMinutes = sharedPreferences.getString("monday_minutes", "") ?: ""
            tuesdayMinutes = sharedPreferences.getString("tuesday_minutes", "") ?: ""
            wednesdayMinutes = sharedPreferences.getString("wednesday_minutes", "") ?: ""
            thursdayMinutes = sharedPreferences.getString("thursday_minutes", "") ?: ""
            fridayMinutes = sharedPreferences.getString("friday_minutes", "") ?: ""
            saturdayMinutes = sharedPreferences.getString("saturday_minutes", "") ?: ""
            sundayMinutes = sharedPreferences.getString("sunday_minutes", "") ?: ""
        }
    }
    
    private fun saveData() {
        context?.let { ctx ->
            viewModelScope.launch {
                val sharedPreferences = ctx.getSharedPreferences("PracticeData", Context.MODE_PRIVATE)
                sharedPreferences.edit().apply {
                    putString("monday_minutes", mondayMinutes)
                    putString("tuesday_minutes", tuesdayMinutes)
                    putString("wednesday_minutes", wednesdayMinutes)
                    putString("thursday_minutes", thursdayMinutes)
                    putString("friday_minutes", fridayMinutes)
                    putString("saturday_minutes", saturdayMinutes)
                    putString("sunday_minutes", sundayMinutes)
                    apply()
                }
            }
        }
    }

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
        saveData()
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