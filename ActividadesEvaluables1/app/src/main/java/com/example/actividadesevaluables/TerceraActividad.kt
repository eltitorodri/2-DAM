package com.example.actividadesevaluables

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TerceraActividad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tercera_actividad)

        val btnPrimerDiseño: Button = findViewById(R.id.btn_PrimerDiseño)
        val btnSegundoDiseño: Button = findViewById(R.id.btn_SegundoDiseño)
        val btnTercerDiseño: Button = findViewById(R.id.btn_TercerDiseño)

        // Primer Boton

        btnPrimerDiseño.setOnClickListener {
            val primerIntent = Intent(this, activity_primerDiseno::class.java)

            startActivity(primerIntent)
        }

        btnSegundoDiseño.setOnClickListener {
            val segundoIntent = Intent(this, activity_segundodiseno::class.java)

            startActivity(segundoIntent)
        }

        btnTercerDiseño.setOnClickListener {
            val tercerIntent = Intent(this, activity_tercerdiseno::class.java)

            startActivity(tercerIntent)
        }
    }
}