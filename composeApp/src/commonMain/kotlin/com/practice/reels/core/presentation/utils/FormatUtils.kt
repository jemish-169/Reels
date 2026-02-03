package com.practice.reels.core.presentation.utils

import androidx.compose.ui.graphics.Color

/**
 * Parses a hex color string to Compose Color.
 * Supports formats: #RRGGBB and #AARRGGBB
 */
fun parseColor(colorString: String): Color? {
    return try {
        val hex = colorString.removePrefix("#")
        when (hex.length) {
            6 -> Color(
                red = hex.substring(0, 2).toInt(16) / 255f,
                green = hex.substring(2, 4).toInt(16) / 255f,
                blue = hex.substring(4, 6).toInt(16) / 255f
            )
            8 -> Color(
                alpha = hex.substring(0, 2).toInt(16) / 255f,
                red = hex.substring(2, 4).toInt(16) / 255f,
                green = hex.substring(4, 6).toInt(16) / 255f,
                blue = hex.substring(6, 8).toInt(16) / 255f
            )
            else -> null
        }
    } catch (_: Exception) {
        null
    }
}

/**
 * Formats a count number to a readable string with K/M suffixes.
 * Examples: 1000 -> "1K", 1500000 -> "1M"
 */
fun formatCount(count: Int): String {
    return when {
        count >= 1_000_000 -> "${count / 1_000_000}M"
        count >= 1_000 -> "${count / 1_000}K"
        else -> count.toString()
    }
}

/**
 * Formats duration in seconds to mm:ss format.
 * Examples: 65 -> "1:05", 125 -> "2:05"
 */
fun formatDuration(seconds: Int): String {
    val minutes = seconds / 60
    val secs = seconds % 60
    return "$minutes:${secs.toString().padStart(2, '0')}"
}
