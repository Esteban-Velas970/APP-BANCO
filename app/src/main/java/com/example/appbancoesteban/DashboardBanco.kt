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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appbancoesteban.ui.theme.APPBancoEstebanTheme
import java.util.Locale

@Composable
fun DashboardBanco(
    usuario: String = "Usuario",
    onCerrarSesion: () -> Unit
) {
    var saldo by remember { mutableStateOf(500.0) }
    var mensaje by remember { mutableStateOf("") }

    // Estados para diálogos
    var mostrarDialogoDepositar by remember { mutableStateOf(false) }
    var mostrarDialogoRetirar by remember { mutableStateOf(false) }
    var montoInput by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Esteban970 Bank's",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2)
        )

        Text(
            text = "Hola, $usuario",
            fontSize = 18.sp,
            color = Color(0xFF555555),
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )

        // Tarjeta de Saldo
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Saldo Disponible",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$ ${String.format(Locale.getDefault(), "%.2f", saldo)}",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Botones de Funcionalidades
        Button(
            onClick = {
                mensaje = "Saldo actual consultado: $ ${String.format(Locale.getDefault(), "%.2f", saldo)}"
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text("Consultar Saldo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                montoInput = ""
                mensaje = ""
                mostrarDialogoDepositar = true
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text("Depositar Dinero")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                montoInput = ""
                mensaje = ""
                mostrarDialogoRetirar = true
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text("Retirar Dinero")
        }

        if (mensaje.isNotEmpty()) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = mensaje,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { onCerrarSesion() },
            modifier = Modifier.fillMaxWidth(0.6f),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
        ) {
            Text("Cerrar Sesión", color = Color.White)
        }
    }

    // Diálogo para Depositar
    if (mostrarDialogoDepositar) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoDepositar = false },
            title = { Text("Depositar Dinero") },
            text = {
                Column {
                    Text("Ingresa el monto a depositar:")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = montoInput,
                        onValueChange = { montoInput = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        label = { Text("Monto") }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val monto = montoInput.toDoubleOrNull()
                        if (monto != null && monto > 0) {
                            saldo += monto
                            mensaje = "¡Depósito exitoso de $ ${String.format(Locale.getDefault(), "%.2f", monto)}!"
                            mostrarDialogoDepositar = false
                        } else {
                            mensaje = "Error: Ingresa un monto válido"
                        }
                    }
                ) {
                    Text("Depositar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogoDepositar = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Diálogo para Retirar
    if (mostrarDialogoRetirar) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoRetirar = false },
            title = { Text("Retirar Dinero") },
            text = {
                Column {
                    Text("Ingresa el monto a retirar:")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = montoInput,
                        onValueChange = { montoInput = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        label = { Text("Monto") }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val monto = montoInput.toDoubleOrNull()
                        if (monto != null && monto > 0) {
                            if (monto <= saldo) {
                                saldo -= monto
                                mensaje = "¡Retiro exitoso de $ ${String.format(Locale.getDefault(), "%.2f", monto)}!"
                                mostrarDialogoRetirar = false
                            } else {
                                mensaje = "Error: Saldo insuficiente"
                                mostrarDialogoRetirar = false
                            }
                        } else {
                            mensaje = "Error: Ingresa un monto válido"
                        }
                    }
                ) {
                    Text("Retirar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogoRetirar = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboardBanco() {
    APPBancoEstebanTheme {
        DashboardBanco(usuario = "Esteban", onCerrarSesion = {})
    }
}
