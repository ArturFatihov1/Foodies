package com.example.foodies.data

import com.example.foodies.domain.entities.Product
import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("discountPercentage")
    val discountPrice: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("images")
    val images: List<String>
)

fun ProductDto.toProduct(): Product {
    return Product(
        id = this.id,
        title = this.title,
        weight = this.weight,
        price = this.price,
        discountPrice = this.discountPrice,
        description = this.description,
        images = this.images
    )
}

fun List<ProductDto>.toProductList(): List<Product> {
    return this.map {
        it.toProduct()
    }
}