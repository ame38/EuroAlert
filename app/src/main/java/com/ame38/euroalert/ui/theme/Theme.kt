package com.ame38.euroalert.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = AlertOrange,
    error = AlertRed,
    secondary = AlertYellow
)

@Composable
fun EuroAlertRulesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = EuroAlertTypography,
        content = content
    )
}
