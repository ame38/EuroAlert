package com.ame38.euroalert.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.ame38.euroalert.ui.viewmodel.LocationState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onViewRules: () -> Unit,
    onViewFeed: () -> Unit,
    locationState: LocationState = LocationState.Loading
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("EuroAlert", style = MaterialTheme.typography.titleLarge)
        if (locationState is LocationState.Loading) {
            CircularProgressIndicator()
        }
        if (locationState is LocationState.Denied) {
            Text("Location permission is needed to check what's near you.")
        }
        Button(onClick = onViewFeed) { Text("View nearby alerts") }
        Button(onClick = onViewRules) { Text("Manage rules") }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onViewRules = {}, onViewFeed = {})
}
