package com.ame38.euroalert.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.ame38.euroalert.WeatherAlert
import com.ame38.euroalert.ui.components.AlertCard

@Composable
fun AlertsFeedScreen(alerts: List<WeatherAlert>) {
    LazyColumn {
        items(alerts) { alert -> AlertCard(alert) }
    }
}
