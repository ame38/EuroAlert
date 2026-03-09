package com.ame38.euroalert.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ame38.euroalert.WeatherAlert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AlertsViewModel : ViewModel() {

    private val _matchedAlerts = MutableStateFlow<List<WeatherAlert>>(emptyList())
    val matchedAlerts: StateFlow<List<WeatherAlert>> = _matchedAlerts

    fun setMatchedAlerts(alerts: List<WeatherAlert>) {
        _matchedAlerts.update { alerts }
    }
}
