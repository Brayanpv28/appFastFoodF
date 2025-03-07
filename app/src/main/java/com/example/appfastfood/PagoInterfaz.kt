package com.example.appfastfood
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PagoInterfaz : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment)

        val buttonBackM = findViewById<ImageButton>(R.id.backPago)
        buttonBackM.setOnClickListener {
            val intent: Intent = Intent(this, carritoInterfaz::class.java)
            startActivity(intent)
            finish()
        }
        val sformaPago: Spinner = findViewById(R.id.sformaPago)
        val titleDevuPago: TextView = findViewById(R.id.titleDevuelta)
        val txtValorD: EditText = findViewById(R.id.txtValorD)
        val titleNumT: TextView = findViewById(R.id.titleNumT)
        val numTarjeta: EditText = findViewById(R.id.numTarjeta)
        val txtCVC: EditText = findViewById(R.id.txtCVC)
        val fechaVenT: EditText = findViewById(R.id.txtVenciTarje)

        sformaPago.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val formaPagoSeleccionada = parent?.getItemAtPosition(position).toString()
                if (formaPagoSeleccionada == "Efectivo") {
                    titleDevuPago.visibility = View.VISIBLE
                    txtValorD.visibility = View.VISIBLE
                    titleNumT.visibility = View.GONE
                    numTarjeta.visibility = View.GONE
                    txtCVC.visibility = View.GONE
                    fechaVenT.visibility = View.GONE
                } else if (formaPagoSeleccionada == "Tarjeta") {
                    titleDevuPago.visibility = View.GONE
                    txtValorD.visibility = View.GONE
                    titleNumT.visibility = View.VISIBLE
                    numTarjeta.visibility = View.VISIBLE
                    txtCVC.visibility = View.VISIBLE
                    fechaVenT.visibility = View.VISIBLE
                } else if (formaPagoSeleccionada == "PSE") {
                    titleDevuPago.visibility = View.GONE
                    txtValorD.visibility = View.GONE
                    titleNumT.visibility = View.GONE
                    numTarjeta.visibility = View.GONE
                    txtCVC.visibility = View.GONE
                    fechaVenT.visibility = View.GONE
                } else if (formaPagoSeleccionada == "Seleccione una forma de pago") {
                    Toast.makeText(
                        applicationContext,
                        "Por favor, seleccione una forma de pago",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    titleDevuPago.visibility = View.GONE
                    txtValorD.visibility = View.GONE
                    titleNumT.visibility = View.GONE
                    numTarjeta.visibility = View.GONE
                    txtCVC.visibility = View.GONE
                    fechaVenT.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(
                    applicationContext,
                    "Por favor seleccina un opcion de pago",
                    Toast.LENGTH_SHORT
                ).show()
                sformaPago.setSelection(-1)
            }
        }

        val fechayHoraEditText: EditText = findViewById(R.id.FechayHora)
        val currentDateTime = Date()
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
        val formatted = formatter.format(currentDateTime)
        fechayHoraEditText.setText(formatted)
        fechayHoraEditText.isFocusable = false
        fechayHoraEditText.isClickable = false

        val realizarPago = findViewById<ImageButton>(R.id.pagoyButton)
        realizarPago.setOnClickListener {
            val intent: Intent = Intent(this, messageIU::class.java)
            startActivity(intent)
            finish()
        }

        val db = FirebaseFirestore.getInstance()
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if (userId != null) {
            db.collection("Users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val nameU = document.getString("name")
                        val celular = document.getString("phoneNumber")

                        findViewById<EditText>(R.id.txtNombre).setText(nameU)
                        findViewById<EditText>(R.id.txtPhone).setText(celular)
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al obtener datos: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

    }
}
