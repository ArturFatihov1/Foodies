package com.example.foodies.data.local_source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodies.domain.entities.Parameters
import com.example.foodies.domain.entities.Product

@Entity(tableName = "cart_table")
data class ProductDto(
    @PrimaryKey
    val id: Long,
    val title: String,
    val weight: Int,
    val price: Double?,
    @ColumnInfo("discount_price")
    val discountPrice: Double,
    val description: String,
    val dimensions: Parameters,
    val images: List<String>,
    val count: Int
)

fun ProductDto.toProduct() = Product(
    id = id,
    title = title,
    weight = weight,
    price = price,
    discountPrice = discountPrice,
    description = description,
    dimensions = dimensions,
    images = images,
    count = count
)

fun Product.toEntity() = ProductDto(
    id = id,
    title = title,
    weight = weight,
    price = price,
    discountPrice = discountPrice,
    description = description,
    dimensions = dimensions,
    images = images,
    count = count
)
