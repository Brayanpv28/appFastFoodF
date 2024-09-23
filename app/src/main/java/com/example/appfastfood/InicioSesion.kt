package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth

class InicioSesion : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciosesion)

        auth = FirebaseAuth.getInstance()

        val buttonRegis: Button = findViewById(R.id.irRegis)
        buttonRegis.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

        val emailUser = findViewById<EditText>(R.id.CorreoInicioUser)
        val passwordUser = findViewById<EditText>(R.id.PasswordUser)
        val login = findViewById<Button>(R.id.IniciarSesion)

        login.setOnClickListener {
            val email = emailUser.text.toString()
            val password = passwordUser.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signIn(email, password)
            } else {
                Toast.makeText(
                    this,
                    "Por favor, complete los campos con su informacion",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SeleccionComida::class.java)
                    startActivity(intent)
                    finish()
                } else{
                    Toast.makeText(this, "Error al iniciar sesion: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}