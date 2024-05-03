package com.example.appfastfood

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity

class HotdogInterfaz : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotdog)

        val backButton = findViewById<ImageButton>(R.id.backHD)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}