package com.example.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantItem(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("restaurantImage")
    val restaurantImage: String?,
    @SerializedName("ratings")
    val ratings : String?
)
