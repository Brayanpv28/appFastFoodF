package com.example.appfastfood

object CarritoManager {
    private var totalCarrito: Int = 0
    private val observers = mutableListOf<CarritoObserver>()

    fun agregarAlCarrito(precio: Int) {
        totalCarrito += precio
        notificarObservadores()
    }

    fun obtenerTotalCarrito(): Int {
        return totalCarrito
    }

    fun agregarObservador(observer: CarritoObserver) {
        observers.add(observer)
    }

    fun removerObservador(observer: CarritoObserver) {
        observers.remove(observer)
    }

    private fun notificarObservadores() {
        for (observer in observers) {
            observer.onCarritoActualizado(totalCarrito)
        }
    }
}

interface CarritoObserver {
    fun onCarritoActualizado(total: Int)
}
