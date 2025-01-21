package com.example.data.network

import com.example.data.model.Menu
import com.example.data.model.RestaurantItem
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    // localhost:3000/restaurants
    @GET("restaurants")
    suspend fun getRestaurants(): List<RestaurantItem>

    // localhost:3000/restaurants/menu/1
    @GET("restaurants/menu/{id}")
    suspend fun getRestaurantMenu(
        @Path("id") id : Int
    ) : Menu
}