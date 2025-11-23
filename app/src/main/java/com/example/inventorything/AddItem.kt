package com.example.inventorything

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

@Composable
fun AddItemForm(modifier: Modifier = Modifier) {
    val name by rememberSaveable { mutableStateOf("") }
    val amount by rememberSaveable { mutableStateOf("") }

    Column(modifier) {
        Text("lol")
    }
}