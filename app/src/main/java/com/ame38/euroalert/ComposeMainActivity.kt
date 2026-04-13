package com.ame38.euroalert

import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleRuleBasedCheck()
        setContent {
            EuroAlertRulesTheme {
                // screens wired in over the next few commits
            }
        }
    }

    private fun scheduleRuleBasedCheck() {
        val request = PeriodicWorkRequestBuilder<RuleBasedCheckWorker>(1, TimeUnit.HOURS).build()
        WorkManager.getInstance(this).enqueue(request)
    }
}
