# EuroAlert - rules-engine-compose branch

This branch is a parallel exploration of a different approach to the same
app: instead of a single hardcoded severity+radius check, alerts are matched
against a list of user-defined `AlertRule`s (name, category, minimum
severity, radius), and the UI is built with Jetpack Compose + Material 3
instead of the View/XML layouts on `main`. It also targets a higher minSdk
(26 instead of 24) since it doesn't need to support older devices for this
experiment.

Not merged into main - kept here to compare the two approaches side by side.

## What's different from main

- UI: Jetpack Compose screens (`ui/screens`) instead of Activities + XML layouts
- Alerts: `rules/AlertRule` + `RuleEvaluator` instead of a single fixed filter
- State: `AlertsViewModel` with `StateFlow` instead of `runOnUiThread` callbacks
- minSdk 26 instead of 24

## Screens so far

- Home (status + navigation)
- Rules list (enable/disable, delete with confirmation)
- Rule editor (create, with validation)
- Alerts feed (color-coded by severity)

## What's the same

- Data sources: still Meteoalarm for severe weather (EMSC earthquake support
  hasn't been ported over to the rule engine yet)
- Permissions: still coarse location + notifications only
