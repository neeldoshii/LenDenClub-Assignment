package com.example.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.model.MenuItem
import com.example.myapplication.databinding.MenuItemBinding

class MenuAdapter(
    private val addToCart: (MenuItem) -> Unit
) :
    ListAdapter<MenuItem, MenuAdapter.MenuViewHolder>(MenuDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MenuViewHolder {
        val binding =
            MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: MenuViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }


    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class MenuViewHolder(private val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(menuItem: MenuItem) {
            Glide.with(binding.menuImageView.context)
                .load(menuItem.image)
                .into(binding.menuImageView)
            binding.menuNameTextView.text = menuItem.name
            binding.menuDescriptionTextView.text = menuItem.description
            binding.menuPriceTextView.text = "Rs : " + menuItem.price.toString()
            binding.addToCartButton.setOnClickListener{
                addToCart(menuItem)
            }
        }
    }

    class MenuDiffCallback : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.menuId == newItem.menuId
        }

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem == newItem
        }
    }
}