package com.example.appfastfood

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity

class Interfaz1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_fastfood)

        val buttonBurger: ImageButton = findViewById(R.id.burgerButton)
        buttonBurger.setOnClickListener{
            val intent: Intent = Intent(this, BurgerInterfaz::class.java)
            startActivity(intent)
        }

        val buttonHotdog: ImageButton = findViewById(R.id.hotdogButton)
        buttonHotdog.setOnClickListener{
            val intent: Intent = Intent(this, HotdogInterfaz::class.java)
            startActivity(intent)
        }

        val buttonSalchipapa: ImageButton = findViewById(R.id.potatosButton)
        buttonSalchipapa.setOnClickListener{
            val intent: Intent = Intent(this, SalchipapaInterfaz::class.java)
            startActivity(intent)
        }

        val buttonbebidas: ImageButton = findViewById(R.id.drinksButton)
        buttonbebidas.setOnClickListener{
            val intent: Intent = Intent(this, BebidaInterfaz::class.java)
            startActivity(intent)
        }
    }
}