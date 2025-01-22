package com.example.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Menu(
    @SerializedName("id")
    val id: Int,
    @SerializedName("menu")
    val menu: List<MenuItem>
)
@Entity(tableName = "menu_items")
data class MenuItem(
    @SerializedName("menu_id")
    @PrimaryKey
    val menuId: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,

    // restaurant id for room to know which restaurant id this menu is
    @SerializedName("restaurant_id")
    var restaurantId: Int
)