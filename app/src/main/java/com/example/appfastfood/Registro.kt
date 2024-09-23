package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Registro : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro)

        val buttonInicio: Button = findViewById(R.id.irIniciarSesion)
        buttonInicio.setOnClickListener {
            val intent: Intent = Intent(this, InicioSesion::class.java)
            startActivity(intent)
        }

        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        val registerButton = findViewById<Button>(R.id.compleRegis)
        registerButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.txtNombreRegistro).text.toString()
            val email = findViewById<EditText>(R.id.CorreoUser).text.toString()
            val password = findViewById<EditText>(R.id.ContraseñaUser).text.toString()
            val phoneNumber = findViewById<EditText>(R.id.NumeroCelular).text.toString()
            val address = findViewById<EditText>(R.id.DireccionUser).text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid
                        saveUserDetails(userId, name, email, phoneNumber, address)
                    } else {
                        Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
    private fun saveUserDetails(userId: String?, name: String, email: String, phoneNumber: String, address: String){
        val db = FirebaseFirestore.getInstance()
        val userDetails = hashMapOf(
            "name" to name,
            "email" to email,
            "phoneNumber" to phoneNumber,
            "address" to address
        )
        userId?.let {
            db.collection("Users").document(it)
                .set(userDetails)
                .addOnSuccessListener {
                    Toast.makeText(this, "Si registro a sido exitoso", Toast.LENGTH_LONG).show()
                }
            Toast.makeText(this, "Error al guardar el registro", Toast.LENGTH_LONG).show()
        }
    }
}