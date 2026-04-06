package com.ame38.euroalert

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AlertsActivity : AppCompatActivity() {

    private lateinit var alertsList: RecyclerView
    private lateinit var emptyText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alerts)

        alertsList = findViewById(R.id.alertsList)
        alertsList.layoutManager = LinearLayoutManager(this)
        emptyText = findViewById(R.id.emptyText)

        loadAlerts()
    }

    private fun loadAlerts() {
        val location = LocationHelper.lastKnownLocation(this)
        if (location == null) {
            showEmpty()
            return
        }

        Thread {
            val items = AlertsRepository.nearbyWeatherAlerts(location.latitude, location.longitude)
                .map { AlertListItem.from(it) } +
                AlertsRepository.nearbyEarthquakes(location.latitude, location.longitude)
                    .map { AlertListItem.from(it) }

            runOnUiThread {
                if (items.isEmpty()) {
                    showEmpty()
                } else {
                    emptyText.visibility = View.GONE
                    alertsList.visibility = View.VISIBLE
                    alertsList.adapter = AlertAdapter(items)
                }
            }
        }.start()
    }

    private fun showEmpty() {
        emptyText.visibility = View.VISIBLE
        alertsList.visibility = View.GONE
    }
}
