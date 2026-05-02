package com.example.foodies.data.local_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ProductDto::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProductsDatabase : RoomDatabase() {
    abstract val dao: ProductsDao

    companion object {
        const val NAME = "product_database"
    }
}