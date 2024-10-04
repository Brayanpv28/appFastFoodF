package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

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

        val registerButton = findViewById<Button>(R.id.compleRegis)
        registerButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.txtNombreRegistro).text.toString()
            val email = findViewById<EditText>(R.id.CorreoUser).text.toString()
            val password = findViewById<EditText>(R.id.ContraseñaUser).text.toString()
            val phoneNumber = findViewById<EditText>(R.id.NumeroCelular).text.toString()
            val address = findViewById<EditText>(R.id.DireccionUser).text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_LONG).show()
            } else if (!isValidEmail(email)) {
                Toast.makeText(this, "Por favor, ingrese un correo válido con '@correo.com'.", Toast.LENGTH_LONG).show()
            } else {
                validatePasswordSequentially(password) { isValid ->
                    if (isValid) {
                        if (!isValidPhoneNumber(phoneNumber)) {
                            Toast.makeText(this, "El número de celular debe tener 10 dígitos y empezar con 3.", Toast.LENGTH_LONG).show()
                        } else {
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        val userId = auth.currentUser?.uid
                                        saveUserDetails(userId, name, email, phoneNumber, address)
                                    } else {
                                        Toast.makeText(this, "Ocurrió un error durante el registro.", Toast.LENGTH_LONG).show()
                                    }
                                }
                        }
                    }
                }
            }
        }
    }

    private fun saveUserDetails(userId: String?, name: String, email: String, phoneNumber: String, address: String) {
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
                    Toast.makeText(this, "Su registro ha sido exitoso", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, InicioSesion::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error al guardar el registro", Toast.LENGTH_LONG).show()
                }
        }
    }

    // Validar si el correo tiene un dominio válido y contiene el @
    private fun isValidEmail(email: String): Boolean {
        val validDomains = listOf("@gmail.com", "@hotmail.com", "@yahoo.com")
        return validDomains.any { domain -> email.endsWith(domain) }
    }

    // Validar el número de celular
    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.length == 10 && phoneNumber.startsWith("3")
    }

    // Función para validar la contraseña en pasos secuenciales
    private fun validatePasswordSequentially(password: String, onComplete: (Boolean) -> Unit) {
        val handler = Handler(Looper.getMainLooper())

        // Verificar longitud mínima
        if (password.length < 8) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres.", Toast.LENGTH_SHORT).show()
            return onComplete(false)
        }

        handler.postDelayed({
            // Verificar al menos una mayúscula
            if (!password.contains(Regex("[A-Z]"))) {
                Toast.makeText(this, "La contraseña debe tener al menos una letra mayúscula.", Toast.LENGTH_SHORT).show()
                return@postDelayed onComplete(false)
            }

            handler.postDelayed({
                // Verificar al menos un número
                if (!password.contains(Regex("[0-9]"))) {
                    Toast.makeText(this, "La contraseña debe tener al menos un número.", Toast.LENGTH_SHORT).show()
                    return@postDelayed onComplete(false)
                }

                handler.postDelayed({
                    // Verificar al menos un carácter especial
                    if (!password.contains(Regex("[@#\$%^&+=!]"))) {
                        Toast.makeText(this, "La contraseña debe tener al menos un carácter especial.", Toast.LENGTH_SHORT).show()
                        return@postDelayed onComplete(false)
                    }

                    // Si todas las validaciones pasan, continuar
                    onComplete(true)
                }, 500)

            }, 500)

        }, 500)
    }
}
