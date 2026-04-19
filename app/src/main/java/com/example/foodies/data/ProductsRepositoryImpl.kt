package com.example.foodies.data

import com.example.foodies.domain.ProductsRepository
import com.example.foodies.domain.entities.Product

class ProductsRepositoryImpl(private val cloudRepository: CloudRepository) : ProductsRepository {
    override suspend fun getAllProducts(): List<Product> {
        val data = cloudRepository.getProducts()
        return data.products.toProductList()
    }

    override suspend fun getProductById(id: Long): Product {
        val data = cloudRepository.getProductById(id)
        return data.toProduct()
    }

    override suspend fun getNameCategories(): List<String> {
        return cloudRepository.getNameCategories()
    }

    override suspend fun getProductsCategory(category: String): List<Product> {
        val data = cloudRepository.getProductsCategory(category)
        return data.products.toProductList()
    }
}