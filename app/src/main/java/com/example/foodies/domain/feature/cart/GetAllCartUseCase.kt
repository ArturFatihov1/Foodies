package com.example.foodies.domain.feature.cart

import com.example.foodies.domain.CartRepository
import com.example.foodies.domain.entities.Product
import kotlinx.coroutines.flow.Flow

class GetAllCartUseCase(private val repository: CartRepository) {
    fun getAllCart(): Flow<List<Product>> {
        return repository.getAllCart()
    }
}