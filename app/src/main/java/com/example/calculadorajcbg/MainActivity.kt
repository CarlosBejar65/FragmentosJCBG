package com.example.calculadorajcbg

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calcu = findViewById<Button>(R.id.calcu)
        val imc = findViewById<Button>(R.id.imc)

        // Configuración inicial: se carga el fragmento de Inicio por defecto
        if (savedInstanceState == null) {
            replaceFragmento(Inicio())
        }

        calcu.setOnClickListener {
            replaceFragmento(Calculadora())
        }

        imc.setOnClickListener {
            replaceFragmento(Imc())
        }
    }

    private fun replaceFragmento(fragmento: Fragment) {
        val manager = supportFragmentManager
        val transaccion = manager.beginTransaction()
        transaccion.replace(R.id.fragmentContainerView, fragmento)
        transaccion.commit()
    }
}