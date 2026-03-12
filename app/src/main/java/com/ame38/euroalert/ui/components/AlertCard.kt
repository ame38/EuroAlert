package com.ame38.euroalert.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ame38.euroalert.WeatherAlert

@Composable
fun AlertCard(alert: WeatherAlert) {
    Card(modifier = Modifier.padding(vertical = 4.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(alert.event, style = MaterialTheme.typography.titleMedium)
            Text(alert.headline, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
