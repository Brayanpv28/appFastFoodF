package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Registro : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro)

        val buttonInicio: Button = findViewById(R.id.irIniciarSesion)
        buttonInicio.setOnClickListener {
            val intent: Intent = Intent(this, InicioSesion::class.java)
            startActivity(intent)
        }

        fun registrarUsuario(name: String, email: String, password: String, phonenumber: Long, address: String) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid
                        val user = hashMapOf(
                            "name" to name,
                            "email" to email,
                            "password" to password,
                            "phonenumber" to phonenumber,
                            "address" to address
                        )
                        if (userId != null) {
                            db.collection("users").document(userId)
                                .set(user)
                                .addOnSuccessListener {
                                    println("Usuario registrado con éxito")
                                }
                                .addOnFailureListener { e ->
                                    println("Error al registrar usuario: $e")
                                }
                        }
                    } else {
                        println("Error al registrar usuario: ${task.exception}")
                    }
                }
        }
    }
}