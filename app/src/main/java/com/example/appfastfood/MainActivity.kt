package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_fastfood)

        val buttonBurger: ImageButton = findViewById(R.id.burgerButton)
        buttonBurger.setOnClickListener {
            val intent: Intent = Intent(this, BurgerInterfaz::class.java)
            startActivity(intent)
        }

        val buttonPerro: ImageButton = findViewById(R.id.hotdogButton)
        buttonPerro.setOnClickListener {
            val intent: Intent = Intent(this, HotdogInterfaz::class.java)
            startActivity(intent)
        }
        val buttonSalchipapas: ImageButton = findViewById(R.id.potatosButton)
        buttonSalchipapas.setOnClickListener {
            val intent: Intent = Intent(this, SalchipapaInterfaz::class.java)
            startActivity(intent)
        }

        val buttonBebidas: ImageButton = findViewById(R.id.drinksButton)
        buttonBebidas.setOnClickListener {
            val intent: Intent = Intent(this, BebidaInterfaz::class.java)
            startActivity(intent)
        }
    }
}
