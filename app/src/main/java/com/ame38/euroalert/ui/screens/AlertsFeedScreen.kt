package com.ame38.euroalert.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ame38.euroalert.WeatherAlert
import com.ame38.euroalert.ui.components.AlertCard

@Composable
fun AlertsFeedScreen(alerts: List<WeatherAlert>) {
    // category filtering is a no-op for now since we only have weather alerts
    // wired into the compose branch; kept as a hook for when EMSC support lands here
    if (alerts.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Nothing nearby right now.")
        }
        return
    }
    LazyColumn {
        items(alerts) { alert -> AlertCard(alert) }
    }
}
