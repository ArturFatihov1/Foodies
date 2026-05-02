package com.example.foodies.presentation.screen.cart

import com.example.foodies.domain.entities.Product

data class CartState(
    val productList: List<Product> = emptyList(),
    val totalSum: Double = 0.0,
)