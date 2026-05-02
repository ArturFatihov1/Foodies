package com.example.foodies.data.repository

import com.example.foodies.data.local_source.ProductsDao
import com.example.foodies.data.local_source.toEntity
import com.example.foodies.data.local_source.toProduct
import com.example.foodies.domain.CartRepository
import com.example.foodies.domain.entities.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CartRepositoryImpl(
    private val dao: ProductsDao
) : CartRepository {

    override suspend fun addToCart(product: Product) {
        val data = product.toEntity()
        dao.addToCart(data.copy(count = 1))
    }

    override fun getAllCart(): Flow<List<Product>> {
        val data = dao.listenAll().map {
            it.map { it.toProduct() }
        }
        return data
    }

    override suspend fun increment(id: Long) {
        dao.increment(id)
    }

    override suspend fun decrement(id: Long) {
        dao.decrementAndRemove(id)
    }
}