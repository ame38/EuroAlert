package com.ame38.euroalert.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = AlertOrange,
    error = AlertRed,
    secondary = AlertYellow,
    background = SurfaceLight
)

private val DarkColors = darkColorScheme(
    primary = AlertOrange,
    error = AlertRed,
    secondary = AlertYellow,
    background = SurfaceDark
)

@Composable
fun EuroAlertRulesTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = EuroAlertTypography,
        content = content
    )
}
