package com.example.foodies.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.domain.GetAllProductsUseCase
import com.example.foodies.domain.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(repository: ProductsRepository) : ViewModel() {

    private val getAllProductsUseCase = GetAllProductsUseCase(repository)

    private val _state = MutableStateFlow(ProductState(emptyList()))
    val state = _state.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            try {
                val data = getAllProductsUseCase.getAllProducts()
                _state.value = ProductState(data)
            } catch (e: Exception) {
                Log.d("logging123", "error internet")
            }
        }
    }
}