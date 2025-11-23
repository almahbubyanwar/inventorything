package com.example.inventorything

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginState(
    val username: String = "",
    val password: String = ""
)

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun onUsernameChange(newVal: String) {
        _uiState.update { currentState ->
            currentState.copy(username = newVal)
        }
    }

    fun onPasswordChange(newVal: String) {
        _uiState.update { currentState ->
            currentState.copy(password = newVal)
        }
    }

    fun processLogin(): Boolean {
        return true
    }
}