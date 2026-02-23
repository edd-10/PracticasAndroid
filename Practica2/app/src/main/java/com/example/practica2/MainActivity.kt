package com.example.practica2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica2.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaTiposDeEscalado(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PantallaTiposDeEscalado(modifier: Modifier = Modifier) {
    val imagenId = R.drawable.mifoto

    // Usamos una columna con scroll para ver todos los ejemplos
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Habilita el scroll vertical
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Tipos de ContentScale", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6200EE))
        Spacer(modifier = Modifier.height(16.dp))

        // 1. FillBounds (Estira sin respetar proporciones)
        ItemDemostracionImagen("FillBounds", ContentScale.FillBounds, imagenId)

        // 2. FillHeight (Llena el alto, recorta el ancho si sobra)
        ItemDemostracionImagen("FillHeight", ContentScale.FillHeight, imagenId)

        // 3. FillWidth (Llena el ancho, recorta el alto si sobra)
        ItemDemostracionImagen("FillWidth", ContentScale.FillWidth, imagenId)

        // 4. Fit (El equivalente a fitCenter - Muestra toda la imagen centrada)
        ItemDemostracionImagen("Fit", ContentScale.Fit, imagenId)

        // 5. Inside (Como Fit, pero si la imagen es pequeña no la agranda)
        ItemDemostracionImagen("Inside", ContentScale.Inside, imagenId)

        // 6. Crop (Llena todo el espacio recortando lo que sobra. El más común)
        ItemDemostracionImagen("Crop (Recorta para llenar)", ContentScale.Crop, imagenId)

        Spacer(modifier = Modifier.height(32.dp)) // Espacio extra al final
    }
}

// Esta es una función "molde" reutilizable para mostrar cada ejemplo
@Composable
fun ItemDemostracionImagen(
    titulo: String,
    tipoEscala: ContentScale,
    @DrawableRes imagenResId: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = titulo, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 8.dp))

        // Creamos una caja gris de tamaño fijo para ver cómo se comporta la imagen dentro
        Box(
            modifier = Modifier
                .height(200.dp) // Altura fija
                .fillMaxWidth() // Ancho total
                .background(Color.LightGray), // Fondo gris para ver los espacios vacíos
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imagenResId),
                contentDescription = null, // Descripción para accesibilidad (opcional aquí)
                modifier = Modifier.fillMaxSize(), // La imagen intenta llenar la caja gris
                contentScale = tipoEscala // ¡AQUÍ ESTÁ LA MAGIA!
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EscalasPreview() {
    MaterialTheme {
        // Nota: El preview puede fallar si no encuentra la imagen R.drawable.mi_foto_perfil
        // Si falla, ejecuta la app en el emulador directamente.
        PantallaTiposDeEscalado()
    }
}