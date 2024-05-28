package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BurgerInterfaz : ComponentActivity() {

    private val db = Firebase.firestore
    private var totalCarrito: Int = 0
    private lateinit var txtCarrito: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.burger)

        txtCarrito = findViewById(R.id.txtCarrito)

        val buttonHSencilla: Button = findViewById(R.id.AgregarSencilla2)
        val buttonHEspecial: Button = findViewById(R.id.AgregarEspecial)
        val buttonHSuper: Button = findViewById(R.id.AgregarSuper)

        val backButton = findViewById<ImageButton>(R.id.backHB)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val buttonCarC: ImageButton = findViewById(R.id.carritoButton)
        buttonCarC.setOnClickListener {
            val intent = Intent(this, carritoInterfaz::class.java)
            startActivity(intent)
        }
    }
}
