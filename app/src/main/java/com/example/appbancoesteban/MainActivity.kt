package com.example.appbancoesteban

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appbancoesteban.ui.theme.APPBancoEstebanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APPBancoEstebanTheme {
                // 'currentScreen' es la variable inteligente que controla qué pantalla se muestra.
                // Puede ser: "inicio", "login", "registro", o "dashboard".
                var currentScreen by remember { mutableStateOf("inicio") }
                var usuarioActual by remember { mutableStateOf("") }

                if (currentScreen == "inicio") {
                    InicioApp(
                        onIniciarSesionClick = { currentScreen = "login" },
                        onRegistrarClick = { currentScreen = "registro" }
                    )
                } else if (currentScreen == "login") {
                    PantallaLogin(
                        onVolverClick = { currentScreen = "inicio" },
                        onLoginExitoso = { usuario ->
                            usuarioActual = usuario
                            currentScreen = "dashboard"
                        }
                    )
                } else if (currentScreen == "registro") {
                    InicioSesion(
                        onVolverClick = { currentScreen = "inicio" },
                        onRegistroExitoso = { currentScreen = "login" } // <- Nos lleva automáticamente al login al registrarse con éxito
                    )
                } else if (currentScreen == "dashboard") {
                    DashboardBanco(
                        usuario = usuarioActual.ifEmpty { "Cliente" },
                        onCerrarSesion = {
                            usuarioActual = ""
                            currentScreen = "inicio"
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun InicioApp(onIniciarSesionClick: () -> Unit, onRegistrarClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)), // Azul claro consistente
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bienvenido a",
            fontSize = 20.sp,
            color = Color(0xFF1976D2)
        )
        Text(
            text = "Esteban970 Bank´s",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Al hacer clic, cambia el estado a "login" y nos lleva a esa pantalla
        Button(
            onClick = { onIniciarSesionClick() },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Al hacer clic, cambia el estado a "registro" y nos lleva a esa pantalla
        Button(
            onClick = { onRegistrarClick() },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Registrar una Nueva Cuenta")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppbancoEsteban() {
    APPBancoEstebanTheme {
        InicioApp(onIniciarSesionClick = {}, onRegistrarClick = {})
    }
}
