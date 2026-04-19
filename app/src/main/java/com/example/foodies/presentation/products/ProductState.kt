package com.example.foodies.presentation.products

import com.example.foodies.domain.entities.Product

sealed interface ProductState {
    object Loading : ProductState
    data class Error(val message: String) : ProductState
    data class Success(val products: List<Product>) : ProductState
}