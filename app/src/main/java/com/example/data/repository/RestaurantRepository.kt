package com.example.data.repository

import com.example.data.model.RestaurantItem
import com.example.data.model.Result
import com.example.data.network.APIService
import retrofit2.HttpException
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val apiService: APIService,
    // DAO
){
    suspend fun getRestaurantsResponse() : Result<List<RestaurantItem>> {
        return try {
            val response = apiService.getRestaurants()
            return Result.Success(response)
        }
        catch (e : HttpException) {
            when(e.code()){
                400 -> Result.Error("Bad Request: The server could not understand the request.", e.code())
                404-> Result.Error("Not Found: The requested resource could not be found.", e.code())
                500 -> Result.Error("Internal Server Error: An unexpected error occurred on the server.", e.code())
                else -> Result.Error("Unexpected error occurred.", e.code())
            }
        }
        catch (e : Exception){
            Result.Error(e.message.toString())
        }

    }
}