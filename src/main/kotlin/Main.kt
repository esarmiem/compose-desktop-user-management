import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var identificacion by remember { mutableStateOf("") }
    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }
    var usuarios by remember { mutableStateOf(listOf<User>()) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            "Gestionar usuarios Medicitas",
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
                .background(Color(0xFFE0F7FA))
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                TextField(
                    value = identificacion,
                    onValueChange = { identificacion = it },
                    label = { Text("Identificación") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nombres,
                    onValueChange = { nombres = it },
                    label = { Text("Nombres") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = apellidos,
                    onValueChange = { apellidos = it },
                    label = { Text("Apellidos") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier.size(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource("medicitas_logo.jpeg"),
                    contentDescription = "Medicitas Logo",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
                .background(Color(0xFFFFF3E0))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = {
                identificacion.toIntOrNull()?.let { id ->
                    Database.insertUser(id, nombres, apellidos, correo)
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
                Spacer(Modifier.width(4.dp))
                Text("Agregar")
            }
            Button(onClick = {
                identificacion.toIntOrNull()?.let { id ->
                    Database.updateUser(id, nombres, apellidos, correo)
                }
            }) {
                Icon(Icons.Default.Edit, contentDescription = "Modificar")
                Spacer(Modifier.width(4.dp))
                Text("Modificar")
            }
            Button(onClick = {
                identificacion.toIntOrNull()?.let { id ->
                    Database.deleteUser(id)
                }
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                Spacer(Modifier.width(4.dp))
                Text("Eliminar")
            }
            Button(onClick = {
                identificacion = ""
                nombres = ""
                apellidos = ""
                correo = ""
            }) {
                Icon(Icons.Default.Home, contentDescription = "Nuevo")
                Spacer(Modifier.width(4.dp))
                Text("Nuevo")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
                .background(Color(0xFFE0E0E0))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar usuario") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                usuarios = Database.getAllUsers().filter { it.nombres.contains(searchQuery, ignoreCase = true) }
            }) {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
                Spacer(Modifier.width(4.dp))
                Text("Buscar")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFFF3E5F5))
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(usuarios) { user ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(user.identificacion.toString(), modifier = Modifier.weight(1f))
                        Text(user.nombres, modifier = Modifier.weight(1f))
                        Text(user.apellidos, modifier = Modifier.weight(1f))
                        Text(user.correo, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}