package com.example.actividadesevaluables

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SegundaActividad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_segunda_actividad)
        val buttonInicio: Button = findViewById(R.id.buttonInicio)

        buttonInicio.setOnClickListener {
            val intent = Intent(this, Menu::class.java)

            startActivity(intent)
            finish()

        }
    }
}