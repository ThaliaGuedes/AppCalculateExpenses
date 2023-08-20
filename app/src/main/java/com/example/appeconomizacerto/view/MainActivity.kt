package com.example.appeconomizacerto.view

import com.example.appeconomizacerto.aplication.viewmodel.FuelCalculatorViewModel
import com.example.appeconomizacerto.R
import com.example.appeconomizacerto.aplication.FuelCalculatorUseCase
import com.example.appeconomizacerto.databinding.ActivityMainBinding
import com.example.appeconomizacerto.domain.model.FuelCalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(view: View) {
        if (view.id == R.id.buton_calculate) {
            calculate()
        }
    }

    private fun isValid(): Boolean {
        return (binding.distance.text.toString() != ""
                && binding.editPrince.text.toString() != ""
                && binding.editAutonomy.text.toString() != ""
                && binding.editAutonomy.text.toString().toFloat() != 0f)
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: FuelCalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fuelCalculator = FuelCalculator()
        val fuelCalculatorUseCase = FuelCalculatorUseCase(fuelCalculator)
        viewModel = FuelCalculatorViewModel(fuelCalculatorUseCase)

        binding.butonCalculate.setOnClickListener {
            calculate()
        }

        viewModel.totalValue.observe(this) { totalValue ->
            binding.textTotalValue.text = totalValue
        }
    }

    private fun calculate() {
        if (isValid()) {
            val distance = binding.distance.text.toString().toFloat()
            val price = binding.editPrince.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()

            viewModel.calculateTotalValue(distance, price, autonomy)
        } else {
            Toast.makeText(this, R.string.validation, Toast.LENGTH_SHORT).show()
        }
    }
}