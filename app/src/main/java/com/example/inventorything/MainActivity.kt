package com.example.inventorything

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inventorything.ui.theme.InventorythingTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InventorythingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Serializable
object Home

@Serializable
object AddItem

@Composable
fun Main(modifier: Modifier = Modifier,
         loginViewModel: LoginViewModel = viewModel()) {
    var isLoggedIn by rememberSaveable{mutableStateOf(true)}
    val loginState by loginViewModel.uiState.collectAsState()

    val navController = rememberNavController()

    fun setLoggedIn(): Unit {
        isLoggedIn = true
    }

    Column() {
        if (!isLoggedIn) {
            LoginScreen(setLoggedIn = { setLoggedIn() }, loginViewModel = loginViewModel)
        }
        else {
            NavHost(navController = navController, startDestination = Home) {
                composable<Home> {
                    Homepage(modifier = modifier, username = loginState.username,
                        onNavigateToAddItem = {navController.navigate(AddItem)})
                }
                composable<AddItem> {
                    AddItemForm(modifier = modifier)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage(modifier: Modifier = Modifier, username: String,
             onNavigateToAddItem: () -> Unit) {
    Column(modifier) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                if (username == "") {
                    Text("Hello, Guest!")
                }
                else {
                    Text("Hello, $username!")
                }
            }
        )
        Column(modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "My Items", fontSize = MaterialTheme.typography.headlineLarge.fontSize)
                Button(onClick = {onNavigateToAddItem()}) {
                    Text("Add Item")
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                InventoryItem("tester", 67)
                InventoryItem("tester", 67)
                InventoryItem("tester", 67)
                InventoryItem("tester", 67)
                InventoryItem("tester", 67)
                InventoryItem("tester", 67)
            }
        }
    }
}

@Composable
fun InventoryItem(name: String, amount: Int) {
    Row(modifier = Modifier
        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
        .fillMaxWidth()
        .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        Text(name, modifier = Modifier.weight(1.0f),
            maxLines = 1, overflow = TextOverflow.Ellipsis)
        Text(amount.toString())
    }
}