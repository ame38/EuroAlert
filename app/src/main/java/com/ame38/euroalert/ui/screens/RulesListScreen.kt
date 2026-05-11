package com.ame38.euroalert.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ame38.euroalert.rules.AlertRule
import com.ame38.euroalert.rules.AlertCategory
import com.ame38.euroalert.Severity

// onToggle must persist immediately - it used to only update the in-memory
// list, so a toggle would revert after leaving the screen
@Composable
fun RulesListScreen(
    rules: List<AlertRule>,
    onToggle: (AlertRule, Boolean) -> Unit
) {
    if (rules.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No rules yet - add one to start getting alerts.")
        }
        return
    }
    LazyColumn {
        items(rules) { rule ->
            Row(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                Text(rule.name, style = MaterialTheme.typography.titleMedium)
                Switch(checked = rule.enabled, onCheckedChange = { onToggle(rule, it) })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RulesListScreenPreview() {
    RulesListScreen(
        rules = listOf(
            AlertRule("1", "Severe weather nearby", AlertCategory.SEVERE_WEATHER, Severity.MODERATE, 50)
        ),
        onToggle = { _, _ -> }
    )
}
