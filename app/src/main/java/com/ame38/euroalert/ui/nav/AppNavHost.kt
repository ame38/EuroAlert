package com.ame38.euroalert.ui.nav

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Rules : Screen("rules")
    object RuleEditor : Screen("rule_editor")
    object Feed : Screen("feed")
}
