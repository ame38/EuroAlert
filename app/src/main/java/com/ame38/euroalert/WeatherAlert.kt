package com.ame38.euroalert

/**
 * One active severe weather warning, as published by Meteoalarm.
 */
data class WeatherAlert(
    val id: String,
    val country: String,
    val event: String,
    val severity: Severity,
    val headline: String,
    val latitude: Double,
    val longitude: Double
)

enum class Severity {
    MINOR, MODERATE, SEVERE, EXTREME;

    companion object {
        fun fromMeteoalarmLevel(level: String): Severity = when (level.lowercase()) {
            "yellow" -> MINOR
            "orange" -> MODERATE
            "red" -> SEVERE
            else -> EXTREME
        }
    }
}
