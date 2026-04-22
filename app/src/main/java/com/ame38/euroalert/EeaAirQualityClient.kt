package com.ame38.euroalert

// poc, EEA's download service isn't a simple single JSON endpoint like the
// other two — looks like it wants a station/pollutant query built up first.
// parking this for now, not worth the added complexity yet.
object EeaAirQualityClient {

    fun fetchNearbyReadings(): List<AirQualityReading> {
        TODO("figure out the actual EEA query format")
    }
}
