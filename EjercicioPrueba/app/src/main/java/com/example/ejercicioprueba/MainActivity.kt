package com.example.ejercicioprueba

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.ejercicioprueba.ui.theme.EjercicioPruebaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjercicioPruebaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EjercicioCamaraGaleria()
                }
            }
        }
    }
}

@Composable
fun EjercicioCamaraGaleria() {
    val context = LocalContext.current

    // Esto son variables para poder guardar propiedades de lo que es la propia imagen
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var rutaTexto by remember { mutableStateOf("No se ha seleccionado ninguna imagen") }

    // Este es el lanzador para poder obtener la imagen desde la galeria
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            imageUri = uri
            bitmap = null
            rutaTexto = "Ruta Galería: ${uri.toString()}"
        }
    }

    // Este es el lanzador para que se pueda ejecutar lo que es la camara dentro de la aplicacion
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { btmp: Bitmap? ->
        if (btmp != null) {
            bitmap = btmp
            imageUri = null
            rutaTexto = "Imagen capturada desde la Cámara"
        }
    }

    // Este es el lanzador de permisos para poder usar la camara o no
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch()
        } else {
            Toast.makeText(context, "Debes aceptar el permiso para usar la cámara", Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cámara y Galería",
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Este es el espacio para que se visualize lo que es la imagen "precargada"
        Card(
            modifier = Modifier
                .size(300.dp)
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (bitmap != null) {
                    // Esto es por si la imagen proviene de la camara
                    Image(
                        bitmap = bitmap!!.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else if (imageUri != null) {
                    // Y esto es si proviene desde la galeria que en este caso usamos la libreria de Coil
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text("Imagen no disponible")
                }
            }
        }

        //Esto se usa para poner la ruta de la imagen en texto dentro de la aplicacion
        Text(
            text = rutaTexto,
            fontSize = 13.sp,
            modifier = Modifier.padding(bottom = 30.dp),
            lineHeight = 18.sp
        )

        // Este es el boton para la camara
        Button(
            onClick = {
                // Pedimos permisos antes de abrir la camara
                permissionLauncher.launch(Manifest.permission.CAMERA)
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
        ) {
            Text("TOMAR FOTO")
        }

        // Y este es el boton de la galeria
        Button(
            onClick = {
                galleryLauncher.launch("image/*")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SELECCIONAR DE GALERÍA")
        }
    }
}