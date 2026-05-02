package com.example.foodies.data.local_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(product: ProductDto)

    @Query("SELECT * FROM cart_table")
    fun listenAll(): Flow<List<ProductDto>>


    @Query("UPDATE cart_table SET count = count + 1 WHERE id = :id")
    suspend fun increment(id: Long)

    @Transaction
    suspend fun decrementAndRemove(id: Long) {
        decrement(id)
        removeIfZero(id)
    }

    @Query("UPDATE cart_table SET count = count - 1 WHERE id = :id AND count >= 1")
    suspend fun decrement(id: Long)

    @Query("DELETE FROM cart_table WHERE id = :id AND count < 1")
    suspend fun removeIfZero(id: Long)

}