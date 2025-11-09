package com.example.actividadesevaluables

import android.os.Bundle
import android.os.PersistableBundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sexta_actividad)

        imagePerfil = findViewById(R.id.imagePerfil)
        textNombre = findViewById(R.id.textNombre)
        textDescripcion = findViewById(R.id.textDescription)

        cargarPerfil()

    }

    private fun cargarPerfil() {

        imagePerfil.setImageResource(R.drawable.actimeles)
        textNombre.text = "Juanito Perez Alcantara"
        textDescripcion.text = "No se que tengo que poner aqui pero esto es la descripcion"

    }

}