package com.example.foodies.domain

import com.example.foodies.domain.entities.Product

class GetAllProductsUseCase(private val repository: ProductsRepository) {
    suspend fun getAllProducts(): List<Product> {
        return repository.getAllProducts()

    }
}