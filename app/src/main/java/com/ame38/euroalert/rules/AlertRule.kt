package com.ame38.euroalert.rules

import com.ame38.euroalert.Severity

/**
 * A user-defined rule describing what should trigger a notification, instead
 * of the single hardcoded radius+severity check the main branch uses.
 */
data class AlertRule(
    val id: String,
    val name: String,
    val category: AlertCategory,
    val minSeverity: Severity,
    val radiusKm: Int,
    val enabled: Boolean = true
)
