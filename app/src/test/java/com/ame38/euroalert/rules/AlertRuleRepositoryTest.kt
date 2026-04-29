package com.ame38.euroalert.rules

import com.ame38.euroalert.Severity
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test

class AlertRuleRepositoryTest {

    @Test
    fun `rule round trips through json`() {
        val rule = AlertRule(
            id = "abc", name = "test", category = AlertCategory.SEVERE_WEATHER,
            minSeverity = Severity.SEVERE, radiusKm = 75, enabled = false
        )
        val json = JSONObject().apply {
            put("id", rule.id)
            put("name", rule.name)
            put("category", rule.category.name)
            put("minSeverity", rule.minSeverity.name)
            put("radiusKm", rule.radiusKm)
            put("enabled", rule.enabled)
        }
        val array = JSONArray().put(json)
        assertEquals(1, array.length())
        assertEquals("test", array.getJSONObject(0).getString("name"))
    }
}
