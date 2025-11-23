package com.example.inventorything

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel(),
    setLoggedIn: () -> Unit
) {
    val loginUiState by loginViewModel.uiState.collectAsState()

    Column(modifier = modifier.fillMaxWidth()) {
        Text("Login", fontSize = MaterialTheme.typography.headlineLarge.fontSize)
        OutlinedTextField(
            value = loginUiState.username,
            onValueChange = {loginViewModel.onUsernameChange(it)},
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = loginUiState.password,
            onValueChange = {loginViewModel.onPasswordChange(it)},
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { setLoggedIn() }) {
            Text("Login")
        }
    }
}