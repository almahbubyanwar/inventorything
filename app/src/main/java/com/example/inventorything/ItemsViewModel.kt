package com.example.inventorything

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Item(val name: String, val amount: Int)

data class ItemsState(
    val items: List<Item> = emptyList<Item>()
)

class ItemsViewModel: ViewModel() {
    private val _itemsState = MutableStateFlow<ItemsState>(ItemsState())
    val itemsState = _itemsState.asStateFlow()

    fun addItem(name: String, amount: Int) {
        val newItem = Item(name, amount)
        _itemsState.update {currentState ->
            currentState.copy(items = currentState.items + newItem)
        }
    }
}