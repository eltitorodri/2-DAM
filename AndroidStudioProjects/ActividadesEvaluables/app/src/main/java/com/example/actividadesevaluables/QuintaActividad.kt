package com.example.actividadesevaluables

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class QuintaActividad : AppCompatActivity() {

    private lateinit var editText_CuartaActividad: EditText
    private var primerValor: Double = 0.0
    private var currentOperator: String = ""
    private var nuevaEntrada: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quinta_actividad)

        editText_CuartaActividad = findViewById(R.id.editText_CuartaActividad)
        editText_CuartaActividad.setText("0")

        setUpNumericListener()
        setUpOperatorListener()
    }

    private fun setUpNumericListener() {
        val botonesNumericosIds = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.decimalButton
        )

        for (id in botonesNumericosIds) {
            findViewById<Button>(id).setOnClickListener {
                val valor = (it as Button).text.toString()
                if (nuevaEntrada) {
                    editText_CuartaActividad.setText("")
                    nuevaEntrada = false
                }
                val textoActual = editText_CuartaActividad.text.toString()
                if (valor == "." && textoActual.contains(".")) return@setOnClickListener
                editText_CuartaActividad.setText(textoActual + valor)
            }
        }
    }

    private fun setUpOperatorListener() {
        findViewById<Button>(R.id.sumaButton).setOnClickListener { seleccionarOperacion("+") }
        findViewById<Button>(R.id.restaButton).setOnClickListener { seleccionarOperacion("-") }
        findViewById<Button>(R.id.multiplicarButton).setOnClickListener { seleccionarOperacion("*") }
        findViewById<Button>(R.id.dividirButton).setOnClickListener { seleccionarOperacion("/") }
        findViewById<Button>(R.id.clearButton).setOnClickListener { limpiar() }
        findViewById<Button>(R.id.igualButton).setOnClickListener { calcularResultado() }
        findViewById<Button>(R.id.igualFinalButton).setOnClickListener { calcularResultado() }
    }

    private fun seleccionarOperacion(operador: String) {
        primerValor = editText_CuartaActividad.text.toString().toDoubleOrNull() ?: 0.0
        currentOperator = operador
        nuevaEntrada = true
    }

    private fun calcularResultado() {
        val segundoValor = editText_CuartaActividad.text.toString().toDoubleOrNull() ?: 0.0
        val resultado = when (currentOperator) {
            "+" -> primerValor + segundoValor
            "-" -> primerValor - segundoValor
            "*" -> primerValor * segundoValor
            "/" -> if (segundoValor != 0.0) primerValor / segundoValor else Double.NaN
            else -> segundoValor
        }
        editText_CuartaActividad.setText(resultado.toString())
        nuevaEntrada = true
        currentOperator = ""
    }

    private fun limpiar() {
        editText_CuartaActividad.setText("0")
        primerValor = 0.0
        currentOperator = ""
        nuevaEntrada = true
    }
}
