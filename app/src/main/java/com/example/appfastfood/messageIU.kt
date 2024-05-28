package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class messageIU : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message)

        val buttonvolver = findViewById<Button>(R.id.continuar)
        buttonvolver.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val buttonTerminarSalir = findViewById<Button>(R.id.terminarsalir)
        buttonTerminarSalir.setOnClickListener {
            finishAffinity()
        }
    }
}