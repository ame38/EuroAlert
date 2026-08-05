package com.ame38.euroalert

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

/**
 * Pulls recent earthquakes from the EMSC FDSN event webservice.
 *
 * https://www.seismicportal.eu/fdsnws/event/1/query?format=json&minmag=3&limit=50
 */
object EmscClient {

    private const val FEED_URL =
        "https://www.seismicportal.eu/fdsnws/event/1/query?format=json&minmag=3&limit=50"
    private const val TAG = "EmscClient"

    fun fetchRecentEarthquakes(): List<EarthquakeEvent> {
        return try {
            val json = fetchJson(FEED_URL)
            parseEvents(json)
        } catch (e: Exception) {
            Log.w(TAG, "failed to fetch EMSC feed", e)
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

    private fun parseEvents(root: JSONObject): List<EarthquakeEvent> {
        val features: JSONArray = root.optJSONArray("features") ?: return emptyList()
        val events = mutableListOf<EarthquakeEvent>()

        for (i in 0 until features.length()) {
            val feature = features.getJSONObject(i)
            val props = feature.optJSONObject("properties") ?: continue
            val geometry = feature.optJSONObject("geometry") ?: continue
            val coords = geometry.optJSONArray("coordinates") ?: continue
            if (coords.length() < 2) continue

            events.add(
                EarthquakeEvent(
                    id = feature.optString("id", i.toString()),
                    magnitude = props.optDouble("mag", 0.0),
                    place = props.optString("flynn_region", "unknown"),
                    longitude = coords.optDouble(0),
                    latitude = coords.optDouble(1),
                    timeMillis = props.optLong("time")
                )
            )
        }

        return events
    }
}
