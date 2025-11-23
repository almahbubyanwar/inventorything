package com.example.inventorything

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AddItemForm(modifier: Modifier = Modifier, addItem: (String, Int) -> Unit,
                onNavigateToHome: () -> Unit) {
    var name by rememberSaveable { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }

    Column(modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
        Text("Add an item", fontSize = MaterialTheme.typography.headlineLarge.fontSize)
        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            label = {Text("Item name")},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = amount,
            onValueChange = { newValue: String ->
                if (newValue.all { it.isDigit() }) {
                    amount = newValue
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {Text("Amount")},
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            addItem(name, amount.toInt())
            onNavigateToHome()
        }) {
            Text("Add")
        }
    }
}