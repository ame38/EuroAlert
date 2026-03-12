package com.ame38.euroalert

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var statusText: TextView

    private val requestLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                runChecks()
            } else {
                statusText.setText(R.string.location_permission_denied)
            }
        }

    // just so the worker's notification can actually show, we don't do
    // anything special if this gets denied, the checks still run either way
    private val requestNotificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        NotificationHelper.createChannel(this)
        requestNotificationPermissionIfNeeded()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            runChecks()
        } else {
            requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun runChecks() {
        val location = LocationHelper.lastKnownLocation(this)
        if (location == null) {
            statusText.setText(R.string.no_last_location)
            return
        }

        statusText.setText(R.string.checking_status)

        Thread {
            val weatherAlerts = AlertsRepository.nearbyWeatherAlerts(location.latitude, location.longitude)
            val earthquakes = AlertsRepository.nearbyEarthquakes(location.latitude, location.longitude)
            val total = weatherAlerts.size + earthquakes.size

            runOnUiThread {
                statusText.text = if (total == 0) {
                    getString(R.string.no_alerts_nearby)
                } else {
                    getString(R.string.alerts_nearby_count, total)
                }
            }
        }.start()
    }
}
