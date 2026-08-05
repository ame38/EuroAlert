package com.ame38.euroalert

import android.content.Context
import android.location.Location
import android.location.LocationManager

object LocationHelper {

    // coarse permission only, so just the last known fix is enough, we don't
    // need to be actively tracking the user's position
    fun lastKnownLocation(context: Context): Location? {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = locationManager.getProviders(true)

        var best: Location? = null
        for (provider in providers) {
            val location = locationManager.getLastKnownLocation(provider) ?: continue
            if (best == null || location.time > best!!.time) {
                best = location
            }
        }
        return best
    }
}
