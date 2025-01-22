package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.CartItem

@Dao
interface CartItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItem)

    @Query("SELECT * FROM cart_items")
    suspend fun getAllCartItems(): List<CartItem>

    @Query("SELECT * FROM cart_items WHERE menuId = :menuId LIMIT 1")
    suspend fun getCartItemByMenuId(menuId: Int): CartItem?

    @Update
    suspend fun update(cartItem: CartItem)

    @Delete
    suspend fun delete(cartItem: CartItem)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    @Query("SELECT SUM(price * quantity) FROM cart_items")
    suspend fun getTotalPrice(): Int
}
