package com.ame38.euroalert

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class AlertCheckWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val location = LocationHelper.lastKnownLocation(applicationContext) ?: return Result.retry()

        val weatherAlerts = AlertsRepository.nearbyWeatherAlerts(applicationContext, location.latitude, location.longitude)
        val earthquakes = AlertsRepository.nearbyEarthquakes(applicationContext, location.latitude, location.longitude)
        val total = weatherAlerts.size + earthquakes.size

        if (total > 0 && !AlertPrefs.isMuted(applicationContext)) {
            val text = applicationContext.getString(R.string.alerts_nearby_count, total)
            NotificationHelper.showAlert(
                applicationContext,
                applicationContext.getString(R.string.alert_notification_title),
                text
            )
        }

        return Result.success()
    }
}
