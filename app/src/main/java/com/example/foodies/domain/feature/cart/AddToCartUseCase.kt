package com.example.foodies.domain.feature.cart

import com.example.foodies.domain.CartRepository
import com.example.foodies.domain.entities.Product

class AddToCartUseCase(private val repository: CartRepository) {
    suspend fun addToCart(product: Product) {
        repository.addToCart(product)
    }
}