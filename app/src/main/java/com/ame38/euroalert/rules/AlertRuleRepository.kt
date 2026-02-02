package com.ame38.euroalert.rules

import android.content.Context
import com.ame38.euroalert.Severity
import org.json.JSONArray
import org.json.JSONObject

/**
 * Stores the user's alert rules as a JSON array in SharedPreferences.
 * No default rules yet - that lands in a follow-up commit.
 */
object AlertRuleRepository {

    private const val PREFS = "alert_rules"
    private const val KEY_RULES = "rules_json"

    fun loadRules(context: Context): List<AlertRule> {
        val raw = prefs(context).getString(KEY_RULES, null) ?: return emptyList()
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

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
}
