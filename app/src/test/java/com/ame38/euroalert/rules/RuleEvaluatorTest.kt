package com.ame38.euroalert.rules

import com.ame38.euroalert.Severity
import com.ame38.euroalert.WeatherAlert
import org.junit.Assert.assertEquals
import org.junit.Test

class RuleEvaluatorTest {

    private val rule = AlertRule(
        id = "1",
        name = "test rule",
        category = AlertCategory.SEVERE_WEATHER,
        minSeverity = Severity.MODERATE,
        radiusKm = 100
    )

    @Test
    fun `matches when within radius and severity`() {
        val alert = WeatherAlert(
            id = "a1", country = "PL", event = "Storm", severity = Severity.SEVERE,
            headline = "test", latitude = 52.0, longitude = 21.0
        )
        val matched = RuleEvaluator.matchingRules(alert, 52.0, 21.0, listOf(rule))
        assertEquals(1, matched.size)
    }

    @Test
    fun `does not match when below min severity`() {
        val alert = WeatherAlert(
            id = "a2", country = "PL", event = "Storm", severity = Severity.MINOR,
            headline = "test", latitude = 52.0, longitude = 21.0
        )
        val matched = RuleEvaluator.matchingRules(alert, 52.0, 21.0, listOf(rule))
        assertEquals(0, matched.size)
    }
}
