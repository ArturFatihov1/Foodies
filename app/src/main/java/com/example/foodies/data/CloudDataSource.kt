package com.example.foodies.data

import retrofit2.http.GET
import retrofit2.http.Path

interface CloudRepository {
    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Long): ProductDto
}
