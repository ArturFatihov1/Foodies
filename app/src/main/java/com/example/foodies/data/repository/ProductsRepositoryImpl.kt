package com.example.foodies.data.repository

import com.example.foodies.data.remote_source.RemoteDataSource
import com.example.foodies.data.remote_source.toProduct
import com.example.foodies.data.remote_source.toProductList
import com.example.foodies.domain.ProductsRepository
import com.example.foodies.domain.entities.Product

class ProductsRepositoryImpl(private val cloudRepository: RemoteDataSource) : ProductsRepository {
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

    override suspend fun searchProduct(query: String): List<Product> {
        val data = cloudRepository.searchProduct(query)
        return data.products.toProductList()
    }

}