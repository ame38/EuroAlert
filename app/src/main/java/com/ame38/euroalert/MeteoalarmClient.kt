package com.ame38.euroalert

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

/**
 * Pulls the current all-Europe warnings feed from Meteoalarm (GeoJSON, one
 * feature per active warning polygon) and turns it into our WeatherAlert model.
 *
 * https://feeds.meteoalarm.org/api/v1/warnings/feeds-europe
 */
object MeteoalarmClient {

    private const val FEED_URL = "https://feeds.meteoalarm.org/api/v1/warnings/feeds-europe"
    private const val TAG = "MeteoalarmClient"

    fun fetchActiveAlerts(): List<WeatherAlert> {
        return try {
            val json = fetchJson(FEED_URL)
            parseAlerts(json)
        } catch (e: Exception) {
            Log.w(TAG, "failed to fetch meteoalarm feed", e)
            emptyList()
        }
    }

    private fun fetchJson(url: String): JSONObject {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.connectTimeout = 10_000
        connection.readTimeout = 10_000
        connection.requestMethod = "GET"
        connection.inputStream.use { stream ->
            val body = stream.bufferedReader().readText()
            return JSONObject(body)
        }
    }

    private fun parseAlerts(root: JSONObject): List<WeatherAlert> {
        val features: JSONArray = root.optJSONArray("features") ?: return emptyList()
        val alerts = mutableListOf<WeatherAlert>()

        for (i in 0 until features.length()) {
            val feature = features.getJSONObject(i)
            val props = feature.optJSONObject("properties") ?: continue
            val geometry = feature.optJSONObject("geometry") ?: continue
            val center = centroidOf(geometry) ?: continue

            alerts.add(
                WeatherAlert(
                    id = props.optString("id", i.toString()),
                    country = props.optString("country", ""),
                    event = props.optString("awareness_type", "unknown"),
                    severity = Severity.fromMeteoalarmLevel(props.optString("awareness_level", "")),
                    headline = props.optString("headline", ""),
                    latitude = center.first,
                    longitude = center.second
                )
            )
        }

        return alerts
    }

    // warning areas come back as polygons, we just need a rough center point
    // to do distance filtering later, not the exact shape
    private fun centroidOf(geometry: JSONObject): Pair<Double, Double>? {
        val coordinates = geometry.optJSONArray("coordinates") ?: return null
        val ring = firstRing(coordinates) ?: return null

        var latSum = 0.0
        var lonSum = 0.0
        var count = 0

        for (i in 0 until ring.length()) {
            val point = ring.optJSONArray(i) ?: continue
            lonSum += point.optDouble(0)
            latSum += point.optDouble(1)
            count++
        }

        if (count == 0) return null
        return Pair(latSum / count, lonSum / count)
    }

    private fun firstRing(coordinates: JSONArray): JSONArray? {
        // Polygon: [ [ [lon,lat], ... ] ], MultiPolygon: [ [ [ [lon,lat], ... ] ] ]
        val first = coordinates.opt(0) ?: return null
        return when (first) {
            is JSONArray -> {
                val inner = first.opt(0)
                if (inner is JSONArray) first.optJSONArray(0) ?: first else coordinates
            }
            else -> null
        }
    }
}
