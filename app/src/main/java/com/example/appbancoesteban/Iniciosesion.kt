package com.example.appbancoesteban

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appbancoesteban.ui.theme.APPBancoEstebanTheme

@Composable
fun PantallaLogin(onVolverClick: () -> Unit, onLoginExitoso: (String) -> Unit) {
    var usuarioOrCorreo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)) // Azul claro consistente con Registro.kt y MainActivity.kt
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Iniciar Sesión",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text("Usuario o Correo", fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = usuarioOrCorreo,
            onValueChange = { 
                usuarioOrCorreo = it
                mensajeError = "" 
            },
            label = { Text("Ingresa tu usuario o correo") },
            modifier = Modifier.padding(bottom = 16.dp),
            singleLine = true
        )

        Text("Contraseña", fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = contrasena,
            onValueChange = { 
                contrasena = it
                mensajeError = "" 
            },
            label = { Text("Ingresa tu contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.padding(bottom = 16.dp),
            singleLine = true
        )

        if (mensajeError.isNotEmpty()) {
            Text(
                text = mensajeError,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (usuarioOrCorreo.isEmpty() || contrasena.isEmpty()) {
                    mensajeError = "Error: Por favor completa todos los campos"
                } else {
                    onLoginExitoso(usuarioOrCorreo)
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Ingresar")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { onVolverClick() },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Volver")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPantallaLogin() {
    APPBancoEstebanTheme {
        PantallaLogin(onVolverClick = {}, onLoginExitoso = {})
    }
}
