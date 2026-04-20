package com.ame38.euroalert

// poc, seeing if EEA air quality data is worth adding as a third alert type.
// not wired into anything yet.
data class AirQualityReading(
    val stationId: String,
    val pollutant: String,
    val value: Double,
    val latitude: Double,
    val longitude: Double
)
