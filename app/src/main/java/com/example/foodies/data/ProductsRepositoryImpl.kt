package com.example.foodies.data

import com.example.foodies.domain.ProductsRepository
import com.example.foodies.domain.entities.Product

class ProductsRepositoryImpl(private val cloudRepository: CloudRepository) : ProductsRepository {
    override suspend fun getAllProducts(): List<Product> {
        val data = cloudRepository.getProducts()
        return data.products.toProductList()
    }
}