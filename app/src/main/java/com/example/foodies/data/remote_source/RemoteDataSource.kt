package com.example.foodies.data.remote_source

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteDataSource {
    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Long): ProductDto

    @GET("products/category-list")
    suspend fun getNameCategories(): List<String>

    @GET("products/category/{nameCategory}")
    suspend fun getProductsCategory(@Path("nameCategory") category: String): ProductResponse

    @GET("products/search")
    suspend fun searchProduct(@Query("q") query: String): ProductResponse

}
