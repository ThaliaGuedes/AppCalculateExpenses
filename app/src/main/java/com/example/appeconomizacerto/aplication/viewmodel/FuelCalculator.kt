package com.example.appeconomizacerto.aplication.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appeconomizacerto.aplication.FuelCalculatorUseCase

class FuelCalculatorViewModel(private val useCase: FuelCalculatorUseCase) : ViewModel() {
    private val _totalValue = MutableLiveData<String>()
    val totalValue: LiveData<String> = _totalValue

    fun calculateTotalValue(distance: Float, price: Float, autonomy: Float) {
        val totalValue = useCase.calculateTotalValue(distance, price, autonomy)
        _totalValue.value = "R$ ${"%.2f".format(totalValue)}"
    }
}