package com.example.foodies.domain

import com.example.foodies.domain.entities.Product

class GetProductByIdUseCase(private val repository: ProductsRepository) {
    suspend fun getProductById(id: Long): Product {
        return repository.getProductById(id)
    }
}