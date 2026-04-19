package com.example.foodies.domain

import com.example.foodies.domain.entities.Product

class GetDetailProductUseCase(private val repository: ProductsRepository) {
    suspend fun getProductById(id: Long): Product {
        return repository.getProductById(id)
    }
}