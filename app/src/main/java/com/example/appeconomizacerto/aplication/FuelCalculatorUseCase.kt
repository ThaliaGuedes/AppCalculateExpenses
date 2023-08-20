package com.example.appeconomizacerto.aplication

import com.example.appeconomizacerto.domain.model.FuelCalculator

class FuelCalculatorUseCase(private val fuelCalculator: FuelCalculator) {
    fun calculateTotalValue(distance: Float, price: Float, autonomy: Float): Float {
        return fuelCalculator.calculateTotalValue(distance, price, autonomy)
    }
}