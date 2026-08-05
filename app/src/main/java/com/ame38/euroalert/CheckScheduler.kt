package com.ame38.euroalert

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object CheckScheduler {

    private const val WORK_NAME = "periodic_alert_check"

    fun schedulePeriodicCheck(context: Context) {
        val request = PeriodicWorkRequestBuilder<AlertCheckWorker>(1, TimeUnit.HOURS).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }
}
