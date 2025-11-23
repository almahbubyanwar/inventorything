package com.example.inventorything

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inventorything.ui.theme.InventorythingTheme

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

@Composable
fun Main(modifier: Modifier = Modifier) {
    var isLoggedIn by rememberSaveable{mutableStateOf(false)}

    fun setLoggedIn(): Unit {
        isLoggedIn = true
    }

    Column(modifier.padding(horizontal = 12.dp).fillMaxWidth()) {
        if (!isLoggedIn) {
            LoginScreen(setLoggedIn = { setLoggedIn() })
        }
        else {
            Homepage()
        }
    }
}

@Composable
fun Homepage(modifier: Modifier = Modifier) {
    Column() {
        Text("hello")
    }
}