package com.ame38.euroalert.rules

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ame38.euroalert.LocationHelper
import com.ame38.euroalert.MeteoalarmClient
import com.ame38.euroalert.NotificationHelper


/**
 * Background check that evaluates every enabled rule, instead of the
 * single hardcoded severity+radius check AlertCheckWorker does on main.
 */
class RuleBasedCheckWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val location = LocationHelper.lastKnownLocation(applicationContext) ?: return Result.success()
        val rules = AlertRuleRepository.loadRules(applicationContext)
        if (rules.isEmpty()) return Result.success()

        val alerts = try {
            MeteoalarmClient.fetchActiveAlerts()
        } catch (e: Exception) {
            return Result.retry()
        }
        alerts.forEach { alert ->
            val matched = RuleEvaluator.matchingRules(alert, location.latitude, location.longitude, rules)
            matched.firstOrNull()?.let { rule ->
                NotificationHelper.showAlert(applicationContext, "${rule.name}: ${alert.event}", alert.headline)
            }
        }
        return Result.success()
    }
}
