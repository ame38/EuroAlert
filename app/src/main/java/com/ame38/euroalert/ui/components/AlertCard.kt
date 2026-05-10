package com.ame38.euroalert.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ame38.euroalert.Severity
import com.ame38.euroalert.WeatherAlert
import com.ame38.euroalert.ui.theme.severityColor
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AlertCard(alert: WeatherAlert) {
    Card(modifier = Modifier.padding(vertical = 4.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(alert.event, style = MaterialTheme.typography.titleMedium, color = severityColor(alert.severity))
            Text(alert.headline, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlertCardPreview() {
    AlertCard(
        WeatherAlert("p1", "ES", "Heat wave", Severity.SEVERE, "Extreme heat expected", 40.0, -3.0)
    )
}
