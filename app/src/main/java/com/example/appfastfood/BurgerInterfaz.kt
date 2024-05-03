package com.example.appfastfood

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity

class BurgerInterfaz : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.burger)

        val backButton = findViewById<ImageButton>(R.id.backHB)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}