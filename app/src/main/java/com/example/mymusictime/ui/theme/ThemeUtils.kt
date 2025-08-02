package com.example.mymusictime.ui.theme

import androidx.compose.ui.graphics.Color

object ThemeUtils {
    
    fun getPrimaryColor(theme: String): Color {
        return when (theme) {
            "blue" -> Color(0xFF2196F3)
            "green" -> Color(0xFF4CAF50)
            "purple" -> Color(0xFF9C27B0)
            else -> Color(0xFFFF8C00) // orange (default)
        }
    }
    
    fun getSecondaryColor(theme: String): Color {
        return when (theme) {
            "blue" -> Color(0xFF64B5F6)
            "green" -> Color(0xFF81C784)
            "purple" -> Color(0xFFBA68C8)
            else -> Color(0xFFFFB74D) // orange light (default)
        }
    }
    
    fun getAccentColor(theme: String): Color {
        return when (theme) {
            "blue" -> Color(0xFF1976D2)
            "green" -> Color(0xFF388E3C)
            "purple" -> Color(0xFF7B1FA2)
            else -> Color(0xFFE65100) // orange dark (default)
        }
    }
    
    fun getSuccessColor(theme: String): Color {
        return when (theme) {
            "blue" -> Color(0xFF4CAF50)
            "green" -> Color(0xFF66BB6A)
            "purple" -> Color(0xFF4CAF50)
            else -> Color(0xFF4CAF50) // green for success (default)
        }
    }
} 