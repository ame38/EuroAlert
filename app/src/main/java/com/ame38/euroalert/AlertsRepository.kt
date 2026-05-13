package com.ame38.euroalert

import android.content.Context

object AlertsRepository {

    fun nearbyWeatherAlerts(context: Context, userLat: Double, userLon: Double): List<WeatherAlert> {
        val radiusKm = AlertPrefs.getRadiusKm(context).toDouble()
        return MeteoalarmClient.fetchActiveAlerts().filter { alert ->
            GeoUtils.distanceKm(userLat, userLon, alert.latitude, alert.longitude) <= radiusKm
        }
    }

    fun nearbyEarthquakes(context: Context, userLat: Double, userLon: Double): List<EarthquakeEvent> {
        val radiusKm = AlertPrefs.getRadiusKm(context).toDouble()
        return EmscClient.fetchRecentEarthquakes().filter { quake ->
            GeoUtils.distanceKm(userLat, userLon, quake.latitude, quake.longitude) <= radiusKm
        }
    }
}
