package com.ame38.euroalert.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ame38.euroalert.rules.AlertCategory
import com.ame38.euroalert.rules.AlertRule
import com.ame38.euroalert.Severity
import java.util.UUID

@Composable
fun RuleEditorScreen(onSave: (AlertRule) -> Unit) {
    var name by remember { mutableStateOf("") }
    var radius by remember { mutableStateOf("50") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Rule name") })
        OutlinedTextField(value = radius, onValueChange = { radius = it }, label = { Text("Radius (km)") })
        Button(onClick = {
            onSave(
                AlertRule(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    category = AlertCategory.SEVERE_WEATHER,
                    minSeverity = Severity.MODERATE,
                    radiusKm = radius.toIntOrNull() ?: 50
                )
            )
        }) {
            Text("Save rule")
        }
    }
}
