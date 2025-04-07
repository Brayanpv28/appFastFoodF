package com.example.appfastfood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth


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

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            startActivity(Intent(this, SeleccionComida::class.java))
            finish()
        }else{
            startActivity(Intent(this, InicioSesion::class.java))
            finish()
        }
    }
}