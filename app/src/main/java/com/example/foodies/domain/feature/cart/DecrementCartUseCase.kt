package com.example.foodies.domain.feature.cart

import com.example.foodies.domain.CartRepository

class DecrementCartUseCase(private val repository: CartRepository) {
    suspend fun execute(id: Long) = repository.decrement(id)
}