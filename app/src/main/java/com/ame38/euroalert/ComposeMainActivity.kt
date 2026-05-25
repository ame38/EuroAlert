package com.ame38.euroalert

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.ame38.euroalert.rules.RuleBasedCheckWorker
import com.ame38.euroalert.ui.theme.EuroAlertRulesTheme
import java.util.concurrent.TimeUnit

/**
 * Entry point for the compose + rule-engine rewrite. Kept separate from
 * MainActivity so this branch stays independently launchable while diverging.
 */
class ComposeMainActivity : ComponentActivity() {

    private val requestNotificationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleRuleBasedCheck()
        requestNotificationPermissionIfNeeded()
        setContent {
            EuroAlertRulesTheme {
                // screens wired in over the next few commits
            }
        }
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestNotificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun scheduleRuleBasedCheck() {
        val request = PeriodicWorkRequestBuilder<RuleBasedCheckWorker>(1, TimeUnit.HOURS).build()
        WorkManager.getInstance(this).enqueue(request)
    }
}
