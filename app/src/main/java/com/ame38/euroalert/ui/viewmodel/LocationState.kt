package com.ame38.euroalert.ui.viewmodel

sealed class LocationState {
    object Loading : LocationState()
    object Denied : LocationState()
    data class Available(val lat: Double, val lon: Double) : LocationState()
}
