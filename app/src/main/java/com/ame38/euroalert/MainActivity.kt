package com.ame38.euroalert

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // just a placeholder screen for now, wiring up the real checks next
        findViewById<TextView>(R.id.statusText)
    }
}
