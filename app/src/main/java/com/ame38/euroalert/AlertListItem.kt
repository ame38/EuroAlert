package com.ame38.euroalert

/**
 * Weather warnings and earthquakes are different shapes, this is just the
 * bit the list UI actually needs from either one.
 */
data class AlertListItem(
    val title: String,
    val subtitle: String,
    val severityColorRes: Int
) {
    companion object {
        fun from(alert: WeatherAlert): AlertListItem = AlertListItem(
            title = alert.event.ifBlank { "Weather warning" },
            subtitle = "${alert.country} — ${alert.headline}",
            severityColorRes = when (alert.severity) {
                Severity.MINOR -> R.color.alert_orange_500
                Severity.MODERATE -> R.color.alert_orange_700
                Severity.SEVERE, Severity.EXTREME -> R.color.alert_red_500
            }
        )

        fun from(quake: EarthquakeEvent): AlertListItem = AlertListItem(
            title = "Magnitude ${quake.magnitude}",
            subtitle = quake.place,
            severityColorRes = if (quake.magnitude >= 5.0) R.color.alert_red_500 else R.color.alert_orange_500
        )
    }
}
