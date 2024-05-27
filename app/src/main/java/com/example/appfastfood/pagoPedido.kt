package com.example.appfastfood

import com.google.firebase.Timestamp

data class pagoPedido(
    val id: String = "",
    val nombre: String = "",
    val celular: String = "",
    val fecha_hora: Timestamp? = null,
    val direccion: String = "",
    val punto_refecia: String = "",
    val vivienda: String = "",
    val metodo_pago: String = "",
    val monto_pago: Int? = null,
    val tarjeta_number: String = "",
    val tarjeta_cvc : String = "",
    val tarjeta_fecha: String = "",
)
