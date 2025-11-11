package com.example.actividadesevaluables

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import android.os.Handler

class Menu : AppCompatActivity() {

    private val delay: Long = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnPrimeraActividad: Button = findViewById(R.id.btn_PrimeraActividad)
        val btnSegundaActividad: Button = findViewById(R.id.btn_SegundaActividad)
        val btnTerceraActividad: Button = findViewById(R.id.btn_TerceraActividad)
        val btnCuartaActividad: Button = findViewById(R.id.btn_CuartaActividad)
        val btnQuintaActividad: Button = findViewById(R.id.btn_QuientaActividad)
        val btnSextaActividad: Button = findViewById(R.id.btn_SextaActividad)

        // Logica del Boton Primera Actividad

        btnPrimeraActividad.setOnClickListener {

            val intentPrimera = Intent(this, MainActivity::class.java)

            startActivity(intentPrimera)

        }

        btnSegundaActividad.setOnClickListener {

            iniciarNavegacionConRetraso()

        }

        btnTerceraActividad.setOnClickListener {

            val tercerIntent = Intent(this, TerceraActividad::class.java)

            startActivity(tercerIntent)

        }

        btnCuartaActividad.setOnClickListener {

            val cuartoIntent = Intent(this, CuartaActividad::class.java)

            startActivity(cuartoIntent)

        }

        btnCuartaActividad.setOnClickListener {

            val cuartoIntent = Intent(this, CuartaActividad::class.java)

            startActivity(cuartoIntent)

        }

        btnQuintaActividad.setOnClickListener {

            val quintoIntent = Intent(this, QuintaActividad::class.java)

            startActivity(quintoIntent)

        }

        btnSextaActividad.setOnClickListener {

            val sextoIntent = Intent(this, SextaActividad::class.java)

            startActivity(sextoIntent)

        }

    }

    private fun iniciarNavegacionConRetraso() {
        Toast.makeText(this, "Navegación programada. Espera 10 segundos...", Toast.LENGTH_LONG).show()

        val destinoIntent = Intent(this, SegundaActividad::class.java)

        //Ahora vamos a crear el pending Intent
        val pendingIntent = PendingIntent.getActivity(
            this, 0, destinoIntent, PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        //Ahora vamos a por el Handler
        val handler = Handler(Looper.getMainLooper())

        val runnable = Runnable {
            try {
                pendingIntent.send()
            } catch (e: PendingIntent.CanceledException) {
                e.printStackTrace()
            }
        }

        handler.postDelayed(runnable, delay)

    }

}