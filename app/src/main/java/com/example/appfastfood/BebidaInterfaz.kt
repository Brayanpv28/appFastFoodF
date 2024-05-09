package com.example.appfastfood

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity

class BebidaInterfaz : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bebidas)

        val backButton = findViewById<ImageButton>(R.id.backBb)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val buttonCarC: ImageButton = findViewById(R.id.carritoCButton)
        buttonCarC.setOnClickListener {
            val intent: Intent = Intent(this, carritoInterfaz::class.java)
            startActivity(intent)
        }
    }
}