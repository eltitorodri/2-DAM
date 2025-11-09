package com.example.actividadesevaluables

import android.content.Intent
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //Vincula  el codigo con el diseño visual del XML

        //Obtenemos las referencias de las vistas

        val editTextUrl: EditText = findViewById(R.id.editTextUrl)
        val buttonAbrirUrl: Button = findViewById(R.id.buttonAbrirUrl)

        //Ahora vamos a asignar el Listener al boton (lo que sucede al hacer el click)
        buttonAbrirUrl.setOnClickListener {

            //Obtenemos el texto del campo eliminando los espacios y demas
            var urlText = editTextUrl.text.toString().trim()

            // Comprobaremos ahora que el campo no este vacio

            if (urlText.isEmpty()) {
                Toast.makeText(this,"Por favor, introduce una URL", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Comprobaremos que la URL sea valida
            if (!urlText.startsWith("http://") && !urlText.startsWith("https://")) {
                urlText = "http://$urlText"
            }

            //Vamos a usar una expresion regular
            val urlPattern = Regex("^(http|https)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

            if (!URLUtil.isValidUrl(urlText) || !urlText.matches(urlPattern)) {
                Toast.makeText(this, "Por favor, introduzca una URL valida", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            //Ahora abriremos la URL si es que es valida usando un Intent
            try {
                val intent = Intent(Intent.ACTION_VIEW, urlText.toUri()) // Crea el intent: La accion ACTION_VIEW para ver la URI

                startActivity(intent) // inicia la accion

            } catch (e: Exception) {
                Toast.makeText(this, "Error al intentar abrir el enlace.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }

        }

    }

}