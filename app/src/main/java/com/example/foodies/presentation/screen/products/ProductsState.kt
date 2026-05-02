package com.example.foodies.presentation.screen.products

import com.example.foodies.domain.entities.Product

data class ProductsState(
    val products: List<Product> = emptyList(),
    val query: String = "",
    val loading: Boolean = false,
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "",
    val showError: Boolean = false,
    val error: String? = null,
    val header: Boolean = false,
    val totalSum: Double = 0.0,
)