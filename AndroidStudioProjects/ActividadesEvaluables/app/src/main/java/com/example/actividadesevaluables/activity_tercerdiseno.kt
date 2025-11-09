package com.example.actividadesevaluables

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_tercerdiseno : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tercerdiseno)
        val buttonInicio: Button = findViewById(R.id.buttonInicio)

        buttonInicio.setOnClickListener {
            val intent = Intent(this, Menu::class.java)

            startActivity(intent)
            finish()

        }
    }
}