package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HotdogInterfaz : ComponentActivity() {

    private val db = Firebase.firestore
    private var totalCarrito: Int = 0
    private lateinit var txtCarrito: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotdog)

        txtCarrito = findViewById(R.id.txtCarrito)

        val buttonAgrePerrito: Button = findViewById(R.id.AgregarPerrito)
        val buttonAgrePerro: Button = findViewById(R.id.AgregarPerro)
        val buttonAgrePeEspe: Button = findViewById(R.id.AgregarPerroEspecial)

        buttonAgrePerrito.setOnClickListener {
            agregarHotdog("Perrito")
        }

        buttonAgrePerro.setOnClickListener {
            agregarHotdog("Perro")
        }

        buttonAgrePeEspe.setOnClickListener{
            agregarHotdog("perro_especial")
        }

        val backButton = findViewById<ImageButton>(R.id.backHD)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val buttonCarC: ImageButton = findViewById(R.id.carritoCButton)
        buttonCarC.setOnClickListener {
            val intent: Intent = Intent(this, carritoInterfaz::class.java)
            startActivity(intent)
        }
    }
    private fun agregarHotdog(tipo: String) {

    }
}