package com.example.data.network

import com.example.data.model.Menu
import com.example.data.model.RestaurantItem
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    // https://lendenclub-backend.onrender.com/restaurants
    @GET("restaurants")
    suspend fun getRestaurants(): List<RestaurantItem>

    // https://lendenclub-backend.onrender.com/restaurants/menu/9
    @GET("restaurants/menu/{id}")
    suspend fun getRestaurantMenu(
        @Path("id") id : Int
    ) : Menu
}