package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val cartItemId: Int = 0,
    val menuId: Int,
    val name: String,
    val price: Int,
    var quantity: Int
)
