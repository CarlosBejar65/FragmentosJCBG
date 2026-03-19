package com.example.calculadorajcbg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class Calculadora : Fragment() {

    private lateinit var pantalla: TextView
    private var operand1: Double = 0.0
    private var pendingOperation = "="

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculadora, container, false)

        pantalla = view.findViewById(R.id.pantalla)

        val numberButtons = listOf<Button>(
            view.findViewById(R.id.btn0),
            view.findViewById(R.id.btn1),
            view.findViewById(R.id.btn2),
            view.findViewById(R.id.btn3),
            view.findViewById(R.id.btn4),
            view.findViewById(R.id.btn5),
            view.findViewById(R.id.btn6),
            view.findViewById(R.id.btn7),
            view.findViewById(R.id.btn8),
            view.findViewById(R.id.btn9)
        )

        for (button in numberButtons) {
            button.setOnClickListener { onNumberClick(button) }
        }

        val operatorButtons = listOf<Button>(
            view.findViewById(R.id.btnSuma),
            view.findViewById(R.id.btnResta),
            view.findViewById(R.id.btnMultiplicacion),
            view.findViewById(R.id.btnDivision)
        )

        for (button in operatorButtons) {
            button.setOnClickListener { onOperatorClick(button) }
        }

        view.findViewById<Button>(R.id.btnIgual).setOnClickListener { onEqualsClick() }
        view.findViewById<Button>(R.id.btnBorrar).setOnClickListener { onClearClick() }

        return view
    }

    private fun onNumberClick(button: Button) {
        pantalla.append(button.text)
    }

    private fun onOperatorClick(button: Button) {
        val operand2 = pantalla.text.toString().toDoubleOrNull()
        if (operand2 != null) {
            performOperation(operand2)
        }
        pendingOperation = button.text.toString()
        pantalla.text = ""
    }

    private fun onEqualsClick() {
        val operand2 = pantalla.text.toString().toDoubleOrNull()
        if (operand2 != null) {
            performOperation(operand2)
        }
        pendingOperation = "="
    }

    private fun onClearClick() {
        pantalla.text = ""
        operand1 = 0.0
        pendingOperation = "="
    }

    private fun performOperation(operand2: Double) {
        when (pendingOperation) {
            "=" -> operand1 = operand2
            "+" -> operand1 += operand2
            "-" -> operand1 -= operand2
            "*" -> operand1 *= operand2
            "/" -> {
                if (operand2 != 0.0) {
                    operand1 /= operand2
                } else {
                    pantalla.text = "Error"
                    return
                }
            }
        }
        pantalla.text = if (operand1 % 1 == 0.0) operand1.toInt().toString() else operand1.toString()
    }
}