package com.ame38.euroalert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ame38.euroalert.ui.theme.EuroAlertRulesTheme

/**
 * Entry point for the compose + rule-engine rewrite. Kept separate from
 * MainActivity so this branch stays independently launchable while diverging.
 */
class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EuroAlertRulesTheme {
                // screens wired in over the next few commits
            }
        }
    }
}
