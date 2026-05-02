package com.example.foodies.domain

import com.example.foodies.domain.entities.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllCart(): Flow<List<Product>>
    suspend fun addToCart(product: Product)
    suspend fun increment(id: Long)
    suspend fun decrement(id: Long)
}