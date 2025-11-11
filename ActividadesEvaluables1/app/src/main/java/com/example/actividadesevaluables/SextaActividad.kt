package com.example.actividadesevaluables

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SextaActividad : AppCompatActivity() {

    private lateinit var imagePerfil: ImageView
    private lateinit var textNombre: TextView
    private lateinit var textDescripcion: TextView

    private lateinit var imageTecnologias: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sexta_actividad)

        val buttonInicio: Button = findViewById(R.id.buttonInicio)

        buttonInicio.setOnClickListener {
            val intent = Intent(this, Menu::class.java)

            startActivity(intent)
            finish()

        }

        imagePerfil = findViewById(R.id.imagePerfil)
        textNombre = findViewById(R.id.textNombre)
        textDescripcion = findViewById(R.id.textDescription)
        imageTecnologias = findViewById(R.id.imageTecnologias)

        cargarPerfil()

    }

    private fun cargarPerfil() {

        imagePerfil.setImageResource(R.drawable.actimeles)
        textNombre.text = "Juanito Perez Alcantara"
        textDescripcion.text = "No se que tengo que poner aqui pero esto es la descripcion"
        imageTecnologias.setImageResource(R.drawable.tecnologias)

    }

}