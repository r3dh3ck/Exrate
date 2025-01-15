package com.example.exrate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColors(
    primary = Purple80,
    surface = Color(0xFF1C211C),
    onSurface = Color(0xFFDFE4DC)
)

private val LightColorScheme = lightColors(
    primary = Purple40,
    surface = Color(0xFFEBEFE7),
    onSurface = Color(0xFF181D18)
)

@Composable
fun ExrateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(
        colors = colorScheme,
        content = content
    )
}