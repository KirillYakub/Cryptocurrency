package com.example.cryptocurrency.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val darkColor = darkColorScheme(
    primary = Green,
    background = DarkGray,
    onBackground = White,
    onPrimary = DarkGray
)

private val lightColor = lightColorScheme(
    primary = GrassGreen,
    background = LightGray,
    onBackground = DarkGray,
    onPrimary = LightGray
)

@Composable
fun CryptocurrencyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColor else lightColor
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}