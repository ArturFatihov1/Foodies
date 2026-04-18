package com.example.foodies.data

import retrofit2.http.GET

interface CloudRepository {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}
