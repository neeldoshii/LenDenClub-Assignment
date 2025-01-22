package com.example.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.CartItem
import com.example.myapplication.databinding.CartItemBinding

class CartAdapter(
    private val onIncrement: (CartItem) -> Unit,
    private val onDecrement: (CartItem) -> Unit
) :
    ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CartViewHolder {
        val binding =
            CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: CartViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }


    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class CartViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(cardItem: CartItem) {
            binding.menuNameTextView.text = cardItem.name
            binding.menuPriceTextView.text =  "Rs : "+ cardItem.price.toString()
            binding.quantityTextView.text = cardItem.quantity.toString()
            binding.quantityDecrementBtn.setOnClickListener{
                onDecrement(cardItem)
            }
            binding.quantityIncrementBtn.setOnClickListener{
                onIncrement(cardItem)

            }
        }
    }

    class CartDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.cartItemId == newItem.cartItemId
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }
}