package com.example.basedatos.Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basedatos.Model.user
import com.example.basedatos.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserApp(userRepository: UserRepository) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var users by rememberSaveable { mutableStateOf(listOf<user>()) } // Usar rememberSaveable
    var selectedUserId by remember { mutableStateOf<Int?>(null) }
    var isUpdating by remember { mutableStateOf(false) }
    var isListVisible by rememberSaveable { mutableStateOf(false) } // Usar rememberSaveable

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF1A1A1A), Color(0xFF2E2E2E))
                )
            )
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text(text = "Nombre", color = Color(0xFFCCCCCC)) },
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF2E2E2E),
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color(0xFFCCCCCC),
                    unfocusedIndicatorColor = Color(0xFF888888)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text(text = "Apellido", color = Color(0xFFCCCCCC)) },
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF2E2E2E),
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color(0xFFCCCCCC),
                    unfocusedIndicatorColor = Color(0xFF888888)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text(text = "Edad", color = Color(0xFFCCCCCC)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF2E2E2E),
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color(0xFFCCCCCC),
                    unfocusedIndicatorColor = Color(0xFF888888)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Colocamos los botones en una fila para que estén uno al lado del otro y centrados
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(0.8f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    val edadInt = edad.toIntOrNull() ?: 0
                    scope.launch {
                        withContext(Dispatchers.IO) {
                            if (isUpdating && selectedUserId != null) {
                                userRepository.updateById(selectedUserId!!, nombre, apellido, edadInt)
                            } else {
                                val user = user(nombre = nombre, apellido = apellido, edad = edadInt)
                                userRepository.insert(user)
                            }
                        }
                        nombre = ""
                        apellido = ""
                        edad = ""
                        isUpdating = false
                        selectedUserId = null
                        users = withContext(Dispatchers.IO) { userRepository.getAllUsers() }
                        Toast.makeText(context, if (isUpdating) "Usuario actualizado" else "Usuario registrado", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text(text = if (isUpdating) "Actualizar" else "Registrar")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    isListVisible = !isListVisible // Alternar la visibilidad de la lista
                    if (isListVisible) {
                        scope.launch {
                            users = withContext(Dispatchers.IO) { userRepository.getAllUsers() }
                        }
                    }
                }) {
                    Text(if (isListVisible) "Ocultar" else "Listar")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la lista solo si isListVisible es true
        if (isListVisible) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(users) { user ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF3A3A3A)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "${user.nombre} ${user.apellido}, Edad: ${user.edad}",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(onClick = {
                                    nombre = user.nombre
                                    apellido = user.apellido
                                    edad = user.edad.toString()
                                    selectedUserId = user.id
                                    isUpdating = true
                                }) {
                                    Icon(imageVector = Icons.Filled.Edit, contentDescription = "Editar", tint = Color.White)
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                IconButton(onClick = {
                                    scope.launch {
                                        withContext(Dispatchers.IO) {
                                            userRepository.deleteById(user.id)
                                        }
                                        users = userRepository.getAllUsers()
                                        Toast.makeText(context, "Usuario eliminado", Toast.LENGTH_SHORT).show()
                                    }
                                }) {
                                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Eliminar", tint = Color.White)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}