package com.ame38.euroalert.rules

/**
 * What kind of thing a rule can match against. Kept separate from
 * [com.ame38.euroalert.Severity] since a rule may care about the category
 * (weather vs. quake) independently of how severe it is.
 */
enum class AlertCategory {
    SEVERE_WEATHER,
    EARTHQUAKE
}
