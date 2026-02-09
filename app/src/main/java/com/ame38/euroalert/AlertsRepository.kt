package com.ame38.euroalert

object AlertsRepository {

    // TODO(#8): make this configurable, hardcoded for now
    private const val DEFAULT_RADIUS_KM = 50.0

    fun nearbyWeatherAlerts(userLat: Double, userLon: Double): List<WeatherAlert> {
        return MeteoalarmClient.fetchActiveAlerts().filter { alert ->
            GeoUtils.distanceKm(userLat, userLon, alert.latitude, alert.longitude) <= DEFAULT_RADIUS_KM
        }
    }

    fun nearbyEarthquakes(userLat: Double, userLon: Double): List<EarthquakeEvent> {
        return EmscClient.fetchRecentEarthquakes().filter { quake ->
            GeoUtils.distanceKm(userLat, userLon, quake.latitude, quake.longitude) <= DEFAULT_RADIUS_KM
        }
    }
}
