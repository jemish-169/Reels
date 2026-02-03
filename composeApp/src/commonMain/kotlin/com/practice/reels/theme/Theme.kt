package com.practice.reels.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.practice.reels.core.domain.util.ThemeOption

private val DarkColorScheme = darkColorScheme(
    primary = blue,
    onPrimary = white,

    secondary = lightGrey,
    onSecondary = black,

    tertiary = black,
    onTertiary = white,

    background = darkGrey,
    onBackground = white,

    surface = black,
    onSurface = white
)

private val LightColorScheme = lightColorScheme(
    primary = blue,
    onPrimary = white,

    secondary = darkGrey,
    onSecondary = white,

    tertiary = white,
    onTertiary = black,

    background = lightGrey,
    onBackground = black,

    surface = white,
    onSurface = black
)

@Composable
fun ReelsTheme(
    themeOption: ThemeOption = ThemeOption.SYSTEM,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeOption) {
        ThemeOption.LIGHT -> LightColorScheme
        ThemeOption.DARK -> DarkColorScheme
        ThemeOption.SYSTEM -> {
            if (isSystemInDarkTheme()) DarkColorScheme
            else LightColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = poppinsTypography(),
        content = content
    )
}

@Composable
fun PreviewWithTheme(content: @Composable () -> Unit) {
    ReelsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}