package com.example.foodies.data.repository

import com.example.foodies.data.remote_source.RemoteDataSource
import com.example.foodies.data.remote_source.toProduct
import com.example.foodies.data.remote_source.toProductList
import com.example.foodies.domain.ProductsRepository
import com.example.foodies.domain.entities.Product

class ProductsRepositoryImpl(
    private val remoteRepository: RemoteDataSource,
) : ProductsRepository {
    override suspend fun getAllProducts(): List<Product> {
        val data = remoteRepository.getProducts()
        return data.products.toProductList()
    }

    override suspend fun getProductById(id: Long): Product {
        val data = remoteRepository.getProductById(id)
        return data.toProduct()
    }

    override suspend fun getNameCategories(): List<String> {
        return remoteRepository.getNameCategories()
    }

    override suspend fun getProductsCategory(category: String): List<Product> {
        val data = remoteRepository.getProductsCategory(category)
        return data.products.toProductList()
    }

    override suspend fun searchProduct(query: String): List<Product> {
        val data = remoteRepository.searchProduct(query)
        return data.products.toProductList()
    }
}