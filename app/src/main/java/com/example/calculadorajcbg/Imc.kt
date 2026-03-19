package com.example.calculadorajcbg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

class Imc : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_imc, container, false)

        val etPeso = view.findViewById<TextInputEditText>(R.id.etPeso)
        val etAlturaCm = view.findViewById<TextInputEditText>(R.id.etAltura)
        val btnCalcular = view.findViewById<Button>(R.id.btnCalcularIMC)
        val tvResultado = view.findViewById<TextView>(R.id.tvResultadoIMC)

        btnCalcular.setOnClickListener {
            val pesoStr = etPeso.text.toString()
            val alturaCmStr = etAlturaCm.text.toString()

            if (pesoStr.isNotEmpty() && alturaCmStr.isNotEmpty()) {
                val peso = pesoStr.toFloat()
                val alturaCm = alturaCmStr.toFloat()
                
                // Convertir cm a metros para la fórmula: peso / (altura_m * altura_m)
                val alturaM = alturaCm / 100
                val imc = peso / (alturaM * alturaM)
                
                val resultado = when {
                    imc < 18.5 -> "Bajo peso"
                    imc < 25 -> "Normal"
                    imc < 30 -> "Sobrepeso"
                    else -> "Obesidad"
                }
                
                tvResultado.text = "Tu IMC es: ${String.format("%.2f", imc)}\nCategoría: $resultado"
                tvResultado.setTextColor(resources.getColor(R.color.accent_color))
            } else {
                tvResultado.text = "Por favor, completa los campos"
                tvResultado.setTextColor(resources.getColor(R.color.error_color))
            }
        }

        return view
    }
}