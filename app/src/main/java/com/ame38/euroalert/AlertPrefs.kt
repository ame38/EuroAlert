package com.ame38.euroalert

import android.content.Context

object AlertPrefs {

    private const val PREFS_NAME = "alert_prefs"
    private const val KEY_RADIUS_KM = "radius_km"
    private const val KEY_MUTED = "muted"

    const val DEFAULT_RADIUS_KM = 50

    fun getRadiusKm(context: Context): Int {
        return prefs(context).getInt(KEY_RADIUS_KM, DEFAULT_RADIUS_KM)
    }

    fun setRadiusKm(context: Context, radiusKm: Int) {
        prefs(context).edit().putInt(KEY_RADIUS_KM, radiusKm).apply()
    }

    fun isMuted(context: Context): Boolean {
        return prefs(context).getBoolean(KEY_MUTED, false)
    }

    fun setMuted(context: Context, muted: Boolean) {
        prefs(context).edit().putBoolean(KEY_MUTED, muted).apply()
    }

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
}
