package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "restaurants")
data class RestaurantItem(
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("description")
    val description: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("restaurantImage")
    val restaurantImage: String?,
    @SerializedName("ratings")
    val ratings : String?
)