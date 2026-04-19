package com.example.foodies.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.domain.GetAllProductsUseCase
import com.example.foodies.domain.GetProductByIdUseCase
import com.example.foodies.domain.ProductsRepository
import com.example.foodies.presentation.detail.DetailState
import com.example.foodies.presentation.products.ProductState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(repository: ProductsRepository) : ViewModel() {

    private val getAllProductsUseCase = GetAllProductsUseCase(repository)
    private val getProductByIdUseCase = GetProductByIdUseCase(repository)

    private val _state = MutableStateFlow(ProductState(emptyList()))
    val state = _state.asStateFlow()

    private val _detailState = MutableStateFlow<DetailState>(DetailState.Loading)
    val detailState = _detailState.asStateFlow()

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

    fun loadDetail(id: Long) {
        _detailState.value = DetailState.Loading
        viewModelScope.launch {
            try {
                val product = getProductByIdUseCase.getProductById(id)
                _detailState.value = DetailState.Success(product)
            } catch (e: Exception) {
                _detailState.value = DetailState.Error(message = e.toString())
            }
        }
    }

}