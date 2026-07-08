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
import com.ame38.euroalert.rules.AlertRule
import com.ame38.euroalert.Severity
import java.util.UUID

@Composable
fun RuleEditorScreen(onSave: (AlertRule) -> Unit) {
    var name by remember { mutableStateOf("") }
    var radius by remember { mutableStateOf("50") }
    var error by remember { mutableStateOf<String?>(null) }
    var saving by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Rule name") })
        OutlinedTextField(value = radius, onValueChange = { radius = it }, label = { Text("Radius (km)") })
        error?.let { Text(it) }
        Button(enabled = !saving, onClick = {
            saving = true
            val radiusValue = radius.toIntOrNull()
            if (name.isBlank()) {
                error = "Name can't be empty"
                return@Button
            }
            if (radiusValue == null || radiusValue !in 1..500) {
                error = "Radius must be between 1 and 500 km"
                return@Button
            }
            onSave(
                AlertRule(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    category = com.ame38.euroalert.rules.AlertCategory.SEVERE_WEATHER,
                    minSeverity = Severity.MODERATE,
                    radiusKm = radiusValue
                )
            )
        }) {
            Text("Save rule")
        }
    }
}
