package com.ame38.euroalert.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.ame38.euroalert.Severity
import com.ame38.euroalert.WeatherAlert
import com.ame38.euroalert.ui.theme.AlertOrange
import com.ame38.euroalert.ui.theme.AlertRed
import com.ame38.euroalert.ui.theme.AlertYellow

private fun colorFor(severity: Severity): Color = when (severity) {
    Severity.MINOR -> AlertYellow
    Severity.MODERATE -> AlertOrange
    Severity.SEVERE, Severity.EXTREME -> AlertRed
}

@Composable
fun AlertCard(alert: WeatherAlert) {
    Card(modifier = Modifier.padding(vertical = 4.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(alert.event, style = MaterialTheme.typography.titleMedium, color = colorFor(alert.severity))
            Text(alert.headline, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
