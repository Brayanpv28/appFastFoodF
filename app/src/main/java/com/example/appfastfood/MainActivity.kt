package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_inicio)

        val btnIS: Button = findViewById(R.id.btnInicioS)
        btnIS.setOnClickListener {
            val intent: Intent = Intent(this, InicioSesion::class.java)
            startActivity(intent)
        }

        val btnReg: Button = findViewById(R.id.btnRegistro)
        btnReg.setOnClickListener {
            val intent: Intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }
}