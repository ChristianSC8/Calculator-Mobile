package com.upeu.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var valorResultado: EditText
    private var operadorActual = ""
    private var numeroActual = 0.0
    private var numeroAnterior = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        valorResultado = findViewById(R.id.inputText)
        setupButtons()
    }
    private fun setupButtons() {
        val buttons = arrayOf(
            R.id.butt0, R.id.butt1, R.id.butt2, R.id.butt3,
            R.id.butt4, R.id.butt5, R.id.butt6, R.id.butt7,
            R.id.butt8, R.id.butt9, R.id.buttPunto, R.id.buttIgual,
            R.id.buttSuma, R.id.buttResta, R.id.buttMulty, R.id.buttDivi,
            R.id.delete,R.id.buttPotencia, R.id.buttRaiz, R.id.butt1SQN,
            R.id.buttPI,R.id.buttModulo
        )
        for (buttonId in buttons) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener { onButtonClick(button) }
        }
    }
    private fun appendToInput(value: String) {
        Log.v("VER", value.toString())
        valorResultado.append(value)
    }
    private fun onButtonClick(view: View) {
        when (view.id) {
            R.id.butt0, R.id.butt1, R.id.butt2, R.id.butt3,
            R.id.butt4, R.id.butt5, R.id.butt6, R.id.butt7,
            R.id.butt8, R.id.butt9, R.id.buttPunto -> {
                val button = findViewById<Button>(view.id)
                appendToInput(button.text.toString())
            }
            R.id.buttSuma, R.id.buttResta, R.id.buttMulty, R.id.buttDivi -> {
                val button = findViewById<Button>(view.id)
                setOperator(button.text.toString())
            }
            R.id.buttIgual -> calculateResult()
            R.id.delete-> {
                valorResultado.text.clear()
                operadorActual=""
                numeroActual=0.0
                numeroAnterior=0.0
            }

            //Desde aqui la implementacion de la tarea
            R.id.buttPotencia -> setOperator("^")
            R.id.buttRaiz -> calcularRaiz()
            R.id.butt1SQN -> calcular1SQN()
            R.id.buttPI -> calcularPi()
            R.id.buttModulo -> calcularModulo()

        }
    }

    //Function para la Raiz
    private fun calcularRaiz() {
        numeroActual = valorResultado.text.toString().toDouble()
        val result = Math.sqrt(numeroActual)
        valorResultado.setText(result.toString())
    }
    //End de la function de la Raiz

    //Function para calcular 1 sobre cualquier número
    private fun calcular1SQN() {
        numeroActual = valorResultado.text.toString().toDouble()
        if (numeroActual != 0.0) {
            val result = 1.0 / numeroActual
            valorResultado.setText(result.toString())
        } else {
            valorResultado.setText("Error")
        }
    }
    //End function para calcular 1 sobre cualquier número

    //function para Pi
    private fun calcularPi() {
        valorResultado.setText(Math.PI.toString())
    }
    //End para Pi
    private fun calcularModulo() {
        numeroActual = valorResultado.text.toString().toDouble()
        val absoluteValue = if (numeroActual < 0) -numeroActual else numeroActual
        valorResultado.setText(absoluteValue.toString())
    }


    private fun setOperator(operator: String) {
        operadorActual = operator
        numeroAnterior = valorResultado.text.toString().toDouble()
        valorResultado.text.clear()
    }
    private fun calculateResult() {
        numeroActual = valorResultado.text.toString().toDouble()
        val result = when (operadorActual) {
            "+" -> numeroAnterior + numeroActual
            "-" -> numeroAnterior - numeroActual
            "*" -> numeroAnterior * numeroActual
            "/" -> numeroAnterior / numeroActual
            "^" -> Math.pow(numeroAnterior, numeroActual)
            else -> numeroActual
        }
        valorResultado.setText(result.toString())
        operadorActual = ""
    }
}