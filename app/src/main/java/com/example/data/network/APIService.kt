package com.example.data.network

import com.example.data.model.RestaurantItem
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
//    localhost:3000/restaurants
    @GET("restaurants")
    suspend fun getRestaurants(): List<RestaurantItem>

    @GET("restaurants/{id}")
    suspend fun getRestaurantRecipe(
        @Path("id") id : Int
    ) // TODO Return MODEL
}