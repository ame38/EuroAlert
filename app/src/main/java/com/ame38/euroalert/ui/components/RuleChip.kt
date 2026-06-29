package com.ame38.euroalert.ui.components

import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ame38.euroalert.rules.AlertRule

@Composable
fun RuleChip(rule: AlertRule) {
    AssistChip(onClick = {}, label = { Text("${rule.radiusKm}km, ${rule.minSeverity}") })
}
