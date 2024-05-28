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

class HotdogInterfaz : ComponentActivity(){

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
            agregarHotdog("hot_perrito")
        }

        buttonAgrePerro.setOnClickListener {
            agregarHotdog("hot_perro")
        }

        buttonAgrePeEspe.setOnClickListener {
            agregarHotdog("hot_perroespecial")
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
        Log.d("FirestoreDebug", "Intentando agregar el hotDog de tipo: $tipo")
        db.collection("comidas")
            .document("menu")
            .collection("hotdog")
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
                        "${document.getString("nombre")} agregada al carrito",
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