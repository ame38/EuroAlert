package com.ame38.euroalert.ui.theme

import androidx.compose.ui.graphics.Color

val AlertOrange = Color(0xFFE8752B)
val AlertRed = Color(0xFFC62828)
val AlertYellow = Color(0xFFF2B705)
val SurfaceDark = Color(0xFF1B1B1B)
val SurfaceLight = Color(0xFFFFFBF7)

fun severityColor(severity: com.ame38.euroalert.Severity): Color = when (severity) {
    com.ame38.euroalert.Severity.MINOR -> AlertYellow
    com.ame38.euroalert.Severity.MODERATE -> AlertOrange
    com.ame38.euroalert.Severity.SEVERE, com.ame38.euroalert.Severity.EXTREME -> AlertRed
}
