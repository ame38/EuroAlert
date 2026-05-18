package com.ame38.euroalert

import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val radiusGroup = findViewById<RadioGroup>(R.id.radiusGroup)
        val muteSwitch = findViewById<Switch>(R.id.muteSwitch)

        val checkedId = when (AlertPrefs.getRadiusKm(this)) {
            25 -> R.id.radius25
            100 -> R.id.radius100
            200 -> R.id.radius200
            else -> R.id.radius50
        }
        radiusGroup.check(checkedId)
        muteSwitch.isChecked = AlertPrefs.isMuted(this)

        radiusGroup.setOnCheckedChangeListener { _, checkedId ->
            val radiusKm = when (checkedId) {
                R.id.radius25 -> 25
                R.id.radius100 -> 100
                R.id.radius200 -> 200
                else -> 50
            }
            AlertPrefs.setRadiusKm(this, radiusKm)
        }

        muteSwitch.setOnCheckedChangeListener { _, muted ->
            AlertPrefs.setMuted(this, muted)
        }
    }
}
