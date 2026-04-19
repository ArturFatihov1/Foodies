package com.example.foodies.domain

import com.example.foodies.domain.entities.Product

class GetCategoriesProductUseCase(private val repository: ProductsRepository) {
    suspend fun getNameCategories(): List<String> {
        return repository.getNameCategories()
    }

    suspend fun getProductCategories(category: String): List<Product> {
        return repository.getProductsCategory(category)
    }
}