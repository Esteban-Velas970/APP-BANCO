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
fun InicioSesion(onVolverClick: () -> Unit, onRegistroExitoso: () -> Unit) {
    var usuario by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)) // Azul medio clarito
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Registro de Cuenta",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2)
        )
        
        Spacer(modifier = Modifier.height(20.dp))

        Text("Usuario", fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Escribe tu usuario") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text("Correo", fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Escribe tu correo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text("ID (Identidad)", fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("Escribe tu Identidad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text("Contraseña", fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Escribe tu Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text("Celular", fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = celular,
            onValueChange = { celular = it },
            label = { Text("Escribe tu Celular") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        if (mensaje.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = mensaje, 
                color = if (mensaje.contains("Error")) Color.Red else Color(0xFF388E3C),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if (usuario.isEmpty() || correo.isEmpty() || id.isEmpty() || contrasena.isEmpty() || celular.isEmpty()) {
                    mensaje = "Error: Por favor completa todos los campos"
                } else {
                    mensaje = "¡Registro exitoso para $usuario!"
                    // Después de registrar, esperamos un momento o vamos directo al login
                    onRegistroExitoso()
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { onVolverClick() },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Volver")
        }
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}

// Preview wrapper for the registration screen. 
// A full project build or preview refresh resolves the ClassNotFoundException by generating the compiled class.
@Preview(showBackground = true)
@Composable
fun PreviewAppIniciosesion() {
    APPBancoEstebanTheme {
        InicioSesion(onVolverClick = {}, onRegistroExitoso = {})
    }
}
