package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class PagoInterfaz : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment)

        val sformaPago: Spinner = findViewById(R.id.sformaPago)
        val opcionesPago = resources.getStringArray(R.array.pago)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesPago)
        val titleDevuPago: TextView = findViewById(R.id.titleDevuelta)
        val txtValorD: EditText = findViewById(R.id.txtValorD)
        val titleNumT: TextView = findViewById(R.id.titleNumT)
        val numTarjeta: EditText = findViewById(R.id.numTarjeta)
        val txtCVC: EditText = findViewById(R.id.txtCVC)
        val fechaVenT: EditText = findViewById(R.id.txtVenciTarje)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sformaPago.adapter = adapter
        sformaPago.setSelection(0, false)


        sformaPago.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val formaPagoSeleccionada = parent?.getItemAtPosition(position).toString()

                titleDevuPago.visibility = View.GONE
                txtValorD.visibility = View.GONE
                titleNumT.visibility = View.GONE
                numTarjeta.visibility = View.GONE
                txtCVC.visibility = View.GONE
                fechaVenT.visibility = View.GONE

                when (formaPagoSeleccionada) {
                    "Efectivo" -> {
                        titleDevuPago.visibility = View.VISIBLE
                        txtValorD.visibility = View.VISIBLE
                    }

                    "Tarjeta" -> {
                        titleNumT.visibility = View.VISIBLE
                        numTarjeta.visibility = View.VISIBLE
                        txtCVC.visibility = View.VISIBLE
                        fechaVenT.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(
                    applicationContext,
                    "Por favor seleccina un opcion de pago",
                    Toast.LENGTH_SHORT
                ).show()
                sformaPago.setSelection(0)
            }
        }

        val pagarButton: ImageButton = findViewById(R.id.pagoyButton)

        fun esMastercardOVisa(numeroTarjeta: String): Boolean {
            val primerosDigitos = numeroTarjeta.take(2).toIntOrNull()

            return when (primerosDigitos) {
                in 40..49 -> true // Visa
                in 50..55 -> true // Mastercard
                else -> false
            }
        }

        fun pagoTarjeta(numeroTarjeta: String): Boolean {
            // Aquí pondrías la lógica para procesar el pago con la tarjeta
            // Retorna true si el pago se realizó con éxito, de lo contrario, false
            return true
        }

        fun realizarPago() {
            val numeroTarjeta = "xxxxxxxxxxxxxxxx"

            if (esMastercardOVisa(numeroTarjeta)) {
                val pagoExitoso = pagoTarjeta(numeroTarjeta) // Corregido el nombre de la función

                if (pagoExitoso) {
                    val intent = Intent(this, messageIU::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this,
                        "No se logró realizar el pago, vuelve a intentarlo",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "El pago solo se puede realizar con tarjetas Mastercard o Visa",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        pagarButton.setOnClickListener {
            realizarPago()
        }
    }
}