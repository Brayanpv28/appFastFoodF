package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), CarritoObserver {

    private lateinit var txtCarrito: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_fastfood)

        txtCarrito = findViewById(R.id.txtCarrito)

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

        val buttonCarC: ImageButton = findViewById(R.id.carritoButton)
        buttonCarC.setOnClickListener {
            val intent: Intent = Intent(this, carritoInterfaz::class.java)
            startActivity(intent)
        }

        CarritoManager.agregarObservador(this)
        actualizarTotalCarrito()
    }

    override fun onDestroy() {
        super.onDestroy()
        CarritoManager.removerObservador(this)
    }

    override fun onCarritoActualizado(total: Int) {
        txtCarrito.text = "Total: $total"
    }

    private fun actualizarTotalCarrito() {
        txtCarrito.text = "Total: ${CarritoManager.obtenerTotalCarrito()}"
    }
}
