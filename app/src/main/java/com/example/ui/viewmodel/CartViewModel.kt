package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.CartItem
import com.example.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
) : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>?>(null)
    val cartItem: StateFlow<List<CartItem>?> = _cartItems

    private val _totalAmount = MutableStateFlow<Int?>(null)
    val totalAmount: StateFlow<Int?> = _totalAmount

    fun getCartItems() {
        viewModelScope.launch {
            getTotalAmount()
            _cartItems.emit(cartRepository.getCartItems())
        }
    }

    fun decrementQuantity(cartItem: CartItem) {
        viewModelScope.launch {
            cartRepository.decrementQuantity(cartItem)
            getTotalAmount()
            _cartItems.emit(cartRepository.getCartItems())
        }
    }

    fun incrementQuantity(cartItem: CartItem) {
        viewModelScope.launch {
            cartRepository.incrementQuantity(cartItem)
            getTotalAmount()
            _cartItems.emit(cartRepository.getCartItems())
        }
    }

    fun getTotalAmount() {
        viewModelScope.launch {
            _totalAmount.emit(cartRepository.getTotalAmount())
        }
    }

}