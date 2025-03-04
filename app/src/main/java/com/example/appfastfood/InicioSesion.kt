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

class InicioSesion : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciosesion)

        auth = FirebaseAuth.getInstance()

        val emailUser = findViewById<EditText>(R.id.CorreoInicioUser)
        val passwordUser = findViewById<EditText>(R.id.PasswordUser)
        val login = findViewById<Button>(R.id.IniciarSesion)

        login.setOnClickListener {
            val email = emailUser.text.toString().trim()
            val password = passwordUser.text.toString().trim()
            val handler = Handler(Looper.getMainLooper())

            if (email.isEmpty()) {
                emailUser.error = "Ingrese su correo electrónico"
                emailUser.requestFocus()
                handler.postDelayed({ emailUser.error = null }, 3000)
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                passwordUser.error = "Ingrese su contraseña"
                passwordUser.requestFocus()
                handler.postDelayed({ passwordUser  .error = null }, 3000)
                return@setOnClickListener
            }
            /*if (password.length < 10) {
                passwordUser.error = "La contraseña debe tener al menos 10 caracteres"
                handler.postDelayed({ emailUser.error = null }, 3000)
                return@setOnClickListener
            }*/

            signIn(email, password)
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, SeleccionComida::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Completa los datos con la informaciion correcta", Toast.LENGTH_LONG).show()
                }
            }
    }
}