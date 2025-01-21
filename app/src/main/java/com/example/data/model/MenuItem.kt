package com.example.data.model


import com.google.gson.annotations.SerializedName


data class Menu(
    @SerializedName("id")
    val id: Int,
    @SerializedName("menu")
    val menu: List<MenuItem>
)
data class MenuItem(
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("menu_id")
    val menuId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)