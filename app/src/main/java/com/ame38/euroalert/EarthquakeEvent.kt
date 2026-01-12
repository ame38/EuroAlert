package com.ame38.euroalert

/**
 * One earthquake event, as published by EMSC.
 */
data class EarthquakeEvent(
    val id: String,
    val magnitude: Double,
    val place: String,
    val latitude: Double,
    val longitude: Double,
    val timeMillis: Long
)
