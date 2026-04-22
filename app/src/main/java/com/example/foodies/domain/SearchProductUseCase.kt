package com.example.foodies.domain

import com.example.foodies.domain.entities.Product

class SearchProductUseCase(private val repository: ProductsRepository) {
    suspend fun searchProduct(query: String): List<Product> {
        return repository.searchProduct(query)
    }
}