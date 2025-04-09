package com.example.appfastfood

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciosesion)

        val btnReg: Button = findViewById(R.id.irRegis)
        btnReg.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("SesionUsuario", Context.MODE_PRIVATE)

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
            signIn(email, password)
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    FoodSelect()
                } else {
                    Toast.makeText(this, "Completa los datos con la informaciion correcta", Toast.LENGTH_LONG).show()
                }
            }
    }
    private fun FoodSelect() {
        startActivity(Intent(this, SeleccionComida::class.java))
        finish()
    }
}