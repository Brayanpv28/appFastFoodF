package com.example.appfastfood


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class BebidaInterfaz : ComponentActivity() {
    private val db = Firebase.firestore
    private var totalCarrito: Int = 0
    private lateinit var txtCarrito: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bebidas)

        txtCarrito = findViewById(R.id.txtCarrito)

        val buttonJMango: Button = findViewById(R.id.AgregarJugomango)
        val buttonJMora: Button = findViewById(R.id.AgregarJugomora)
        val buttonJMaracuya: Button = findViewById(R.id.AgregarJugomaracuya)
        val buttonJLulo: Button = findViewById(R.id.AgregarJugolulo)
        val buttonCCPequeña: Button = findViewById(R.id.Agregarcocacolapq)
        val buttonMPequeña: Button = findViewById(R.id.Agregarmanzanapq)
        val buttonUvaPequeña: Button = findViewById(R.id.Agregaruvapq)
        val buttonColomPequeña: Button = findViewById(R.id.Agregarcolombianapq)
        val buttonCCLitro: Button = findViewById(R.id.Agregarcocacolagr)
        val buttonMLitro: Button = findViewById(R.id.Agregarmanzanagr)
        val buttonULitro: Button = findViewById(R.id.Agregaruvagr)
        val buttonColomLitro: Button = findViewById(R.id.Agregarcolombianagr)

        buttonJMango.setOnClickListener {
            agregarBebida("jugo_mango")
        }

        buttonJMora.setOnClickListener {
            agregarBebida("jugo_mora")
        }

        buttonJMaracuya.setOnClickListener {
            agregarBebida("jugo_maracuya")
        }

        buttonJLulo.setOnClickListener {
            agregarBebida("jugo_lulo")
        }

        buttonCCPequeña.setOnClickListener {
            agregarBebida("cola_personal")
        }

        buttonMPequeña.setOnClickListener {
            agregarBebida("manzana_personal")
        }

        buttonUvaPequeña.setOnClickListener {
            agregarBebida("uva_personal")
        }

        buttonColomPequeña.setOnClickListener {
            agregarBebida("colombiana_perso")
        }

        buttonCCLitro.setOnClickListener {
            agregarBebida("cola_grande")
        }

        buttonMLitro.setOnClickListener {
            agregarBebida("manzana_litro")
        }

        buttonULitro.setOnClickListener {
            agregarBebida("uva_litro")
        }

        buttonColomLitro.setOnClickListener {
            agregarBebida("colombiana_litro")
        }

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

    private fun agregarBebida(tipo: String) {
        Log.d("FirestoreDebug", "Intentando agregar bebida de tipo: $tipo")
        db.collection("comidas")
            .document("menu")
            .collection("bebidas")
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

