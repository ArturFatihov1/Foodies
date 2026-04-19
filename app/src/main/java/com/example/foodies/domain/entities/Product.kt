package com.example.foodies.domain.entities

data class Product(
    val id: Long,
    val title: String,
    val weight: Int,
    val price: Double?,
    val discountPrice: Double,
    val description: String,
    val dimensions: Parameters,
    val images: List<String>
)

data class Parameters(
    val width: Double,
    val height: Double,
    val depth: Double
)