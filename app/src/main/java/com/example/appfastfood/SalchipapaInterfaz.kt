package com.example.appfastfood

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity


class SalchipapaInterfaz : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.salchipapas)

        val backButton = findViewById<ImageButton>(R.id.backSP)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}