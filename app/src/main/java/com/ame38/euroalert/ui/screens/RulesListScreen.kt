package com.ame38.euroalert.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ame38.euroalert.rules.AlertRule

@Composable
fun RulesListScreen(
    rules: List<AlertRule>,
    onToggle: (AlertRule, Boolean) -> Unit
) {
    LazyColumn {
        items(rules) { rule ->
            Row(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                Text(rule.name, style = MaterialTheme.typography.titleMedium)
                Switch(checked = rule.enabled, onCheckedChange = { onToggle(rule, it) })
            }
        }
    }
}
