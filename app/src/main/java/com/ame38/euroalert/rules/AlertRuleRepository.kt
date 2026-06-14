package com.ame38.euroalert.rules

import android.content.Context
import com.ame38.euroalert.Severity
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID

/**
 * Stores the user's alert rules as a JSON array in SharedPreferences.
 */
object AlertRuleRepository {

    private fun defaultRules(): List<AlertRule> = listOf(
        AlertRule(
            id = UUID.randomUUID().toString(),
            name = "Severe weather nearby",
            category = AlertCategory.SEVERE_WEATHER,
            minSeverity = Severity.MODERATE,
            radiusKm = 50
        )
    )

    private const val PREFS_NAME = "alert_rules"
    private const val KEY_RULES = "rules_json"
    private const val KEY_LAST_CHECK = "last_check_millis"

    fun loadRules(context: Context): List<AlertRule> {
        val raw = prefs(context).getString(KEY_RULES, null)
        if (raw == null) {
            val seeded = defaultRules()
            saveRules(context, seeded)
            return seeded
        }
        val array = JSONArray(raw)
        return (0 until array.length()).map { i -> ruleFromJson(array.getJSONObject(i)) }
    }

    fun saveRules(context: Context, rules: List<AlertRule>) {
        val array = JSONArray()
        rules.forEach { array.put(ruleToJson(it)) }
        prefs(context).edit().putString(KEY_RULES, array.toString()).apply()
    }

    private fun ruleToJson(rule: AlertRule): JSONObject = JSONObject().apply {
        put("id", rule.id)
        put("name", rule.name)
        put("category", rule.category.name)
        put("minSeverity", rule.minSeverity.name)
        put("radiusKm", rule.radiusKm)
        put("enabled", rule.enabled)
    }

    private fun ruleFromJson(obj: JSONObject): AlertRule = AlertRule(
        id = obj.getString("id"),
        name = obj.getString("name"),
        category = AlertCategory.valueOf(obj.getString("category")),
        minSeverity = Severity.valueOf(obj.getString("minSeverity")),
        radiusKm = obj.getInt("radiusKm"),
        enabled = obj.optBoolean("enabled", true)
    )

    fun setLastCheckTime(context: Context, millis: Long) {
        prefs(context).edit().putLong(KEY_LAST_CHECK, millis).apply()
    }

    fun getLastCheckTime(context: Context): Long =
        prefs(context).getLong(KEY_LAST_CHECK, 0L)

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
}
