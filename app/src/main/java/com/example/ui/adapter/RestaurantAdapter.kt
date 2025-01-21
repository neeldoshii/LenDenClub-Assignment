package com.example.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.model.RestaurantItem
import com.example.myapplication.databinding.RestaurantItemBinding

class RestaurantAdapter(
    private val onRestaurantClicked: (Int) -> Unit,
) : ListAdapter<RestaurantItem, RestaurantAdapter.RestaurantViewHolder>(RestaurantDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RestaurantViewHolder {
        val binding =
            RestaurantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: RestaurantViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }


    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class RestaurantViewHolder(private val binding: RestaurantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: RestaurantItem) {
            Glide.with(binding.restaurantImageView.context)
                .load(restaurant.restaurantImage)
                .into(binding.restaurantImageView)
            binding.restaurantNameTextView.text = restaurant.name
            binding.restaurantDescriptionTextView.text = restaurant.description
            binding.restaurantRatings.text = restaurant.ratings
            binding.root.setOnClickListener {
                onRestaurantClicked(restaurant.id)
            }
        }
    }

    class RestaurantDiffCallback : DiffUtil.ItemCallback<RestaurantItem>() {
        override fun areItemsTheSame(oldItem: RestaurantItem, newItem: RestaurantItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RestaurantItem, newItem: RestaurantItem): Boolean {
            return oldItem == newItem
        }
    }
}