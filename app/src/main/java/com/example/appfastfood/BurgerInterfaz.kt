package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BurgerInterfaz : ComponentActivity(){

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

        buttonHEspecial.setOnClickListener {
            agregarHamburguesa("hamburguesa_especial")
        }

        buttonHSencilla.setOnClickListener {
            agregarHamburguesa("hamburguesa_sencilla")
        }

        buttonHSuper.setOnClickListener {
            agregarHamburguesa("hamburguesa_super")
        }

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

    private fun agregarHamburguesa(tipo: String) {
        Log.d("FirestoreDebug", "Intentando agregar hamburguesa de tipo: $tipo")
        db.collection("comidas")
            .document("menu")
            .collection("hamburguesas")
            .document(tipo)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    Log.d("FirestoreDebug", "Documento obtenido exitosamente: ${document.data}")
                    val precio = document.getLong("precio")?.toInt() ?: 0
                    totalCarrito += precio
                    txtCarrito.text = "Total: $totalCarrito"
                    Toast.makeText(
                        this,
                        "${document.getString("nombre")} agregado al carrito",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.e("FirestoreError", "Error: El documento no existe o es nulo")
                    Toast.makeText(this, "Error al agregar al carrito", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Error al obtener el documento: $exception")
                Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
