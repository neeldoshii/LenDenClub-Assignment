package com.example.data.repository

import com.example.data.db.dao.RestaurantDao
import com.example.data.model.RestaurantItem
import com.example.data.model.Result
import com.example.data.network.APIService
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val apiService: APIService,
    private val restaurantDao: RestaurantDao
){
    suspend fun getRestaurantsResponse() : Result<List<RestaurantItem>> {
        return try {
            val response = apiService.getRestaurants()
            // Cache the success fetched response
            restaurantDao.insertRestaurants(response)
            return Result.Success(response)
        }
        /*
        catch (e : HttpException) {
            when(e.code()){
                400 -> Result.Error("Bad Request: The server could not understand the request.", e.code())
                404-> Result.Error("Not Found: The requested resource could not be found.", e.code())
                500 -> Result.Error("Internal Server Error: An unexpected error occurred on the server.", e.code())
                else -> Result.Error("Unexpected error occurred.", e.code())
            }
        }
         */
        catch (e : Exception){
            val cachedResponse = restaurantDao.getAllRestaurants()
            if (cachedResponse.isEmpty()) {
                Result.Error("No cached Response found from server. Exception :" + e.message.toString())
            }
            else{
                Result.Success(cachedResponse)
            }

        }

    }
}