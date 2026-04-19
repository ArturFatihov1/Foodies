package com.example.foodies.domain

import com.example.foodies.domain.entities.Product

interface ProductsRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(id: Long): Product
}