package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class InicioSesion : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciosesion)

        val buttonRegis: Button = findViewById(R.id.irRegis)
        buttonRegis.setOnClickListener {
            val intent: Intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }
}