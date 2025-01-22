package com.example.data.repository

import com.example.data.db.dao.CartItemDao
import com.example.data.model.CartItem
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartItemDao: CartItemDao) {
    suspend fun incrementQuantity(cartItem: CartItem) {
        val existingCartItem = cartItemDao.getCartItemByMenuId(cartItem.menuId)
        if (existingCartItem != null) {
            // If it exists, increase the quantity
            existingCartItem.quantity += 1
            cartItemDao.update(existingCartItem)
        } else {
            // If it doesn't exist, insert as a new item
            cartItemDao.insert(cartItem)
        }
    }

    suspend fun decrementQuantity(cartItem: CartItem) {
        val existingCartItem = cartItemDao.getCartItemByMenuId(cartItem.menuId)
        existingCartItem?.let {
            existingCartItem.quantity -=1
            cartItemDao.update(existingCartItem)
            // If quantity is 0 or less, delete the item
            if (existingCartItem.quantity<=0){
                cartItemDao.delete(existingCartItem)
            }

        }
    }

    suspend fun getCartItems(): List<CartItem> {
        return cartItemDao.getAllCartItems()
    }

    suspend fun getTotalAmount() : Int {
        return cartItemDao.getTotalPrice()
    }
}