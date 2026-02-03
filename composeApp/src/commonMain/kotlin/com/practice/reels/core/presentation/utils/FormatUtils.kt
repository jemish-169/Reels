package com.practice.reels.core.presentation.utils

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock.System.now
import kotlin.time.ExperimentalTime

fun parseColor(colorString: String): Color? {
    return try {
        val hex = colorString.removePrefix("#")
        when (hex.length) {
            6 -> Color(
                red = hex.take(2).toInt(16) / 255f,
                green = hex.substring(2, 4).toInt(16) / 255f,
                blue = hex.substring(4, 6).toInt(16) / 255f
            )

            8 -> Color(
                alpha = hex.take(2).toInt(16) / 255f,
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

fun formatCount(count: Int): String {
    return when {
        count >= 1_000_000 -> "${count / 1_000_000}M"
        count >= 1_000 -> "${count / 1_000}K"
        else -> count.toString()
    }
}

fun formatDuration(seconds: Int): String {
    val minutes = seconds / 60
    val secs = seconds % 60
    return "$minutes:${secs.toString().padStart(2, '0')}"
}

private const val MS_PER_MINUTE = 60 * 1000L
private const val MS_PER_HOUR = 60 * MS_PER_MINUTE
private const val MS_PER_DAY = 24 * MS_PER_HOUR
private const val MS_PER_WEEK = 7 * MS_PER_DAY

@OptIn(ExperimentalTime::class)
fun formatRelativeTime(timestampMs: Long): String =
    formatRelativeTime(timestampMs, now().toEpochMilliseconds())

fun formatRelativeTime(timestampMs: Long, nowMs: Long): String {
    val diffMs = (nowMs - timestampMs).coerceAtLeast(0L)
    return when {
        diffMs < MS_PER_MINUTE -> "0m ago"
        diffMs < MS_PER_HOUR -> "${diffMs / MS_PER_MINUTE}m ago"
        diffMs < MS_PER_DAY -> "${diffMs / MS_PER_HOUR}h ago"
        diffMs < MS_PER_WEEK -> "${diffMs / MS_PER_DAY}d ago"
        diffMs < 4 * MS_PER_WEEK -> "${diffMs / MS_PER_WEEK}w ago"
        else -> formatMonthYear(timestampMs)
    }
}

@OptIn(ExperimentalTime::class)
private fun formatMonthYear(timestampMs: Long): String {
    val instant = Instant.fromEpochMilliseconds(timestampMs)
    val date = instant.toLocalDateTime(TimeZone.UTC).date
    val month = date.monthNumber.toString().padStart(2, '0')
    val year = date.year
    return "$month-$year"
}
