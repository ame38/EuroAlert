package com.ame38.euroalert.rules

import com.ame38.euroalert.WeatherAlert
import com.ame38.euroalert.GeoUtils

/**
 * Checks a weather alert against a set of rules and returns the ones that matched,
 * instead of the single hardcoded severity+radius filter on main.
 */
object RuleEvaluator {

    fun matchingRules(
        alert: WeatherAlert,
        userLat: Double,
        userLon: Double,
        rules: List<AlertRule>
    ): List<AlertRule> = rules.filter { rule ->
        rule.enabled &&
            rule.category == AlertCategory.SEVERE_WEATHER &&
            alert.severity >= rule.minSeverity &&
            GeoUtils.distanceKm(userLat, userLon, alert.latitude, alert.longitude) <= rule.radiusKm
    }
}
