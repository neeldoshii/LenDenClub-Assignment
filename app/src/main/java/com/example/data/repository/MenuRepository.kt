package com.example.data.repository

import com.example.data.db.dao.MenuItemDao
import com.example.data.model.MenuItem
import com.example.data.model.Result
import com.example.data.network.APIService
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import javax.inject.Inject

class MenuRepository @Inject constructor(
    private val apiService: APIService,
    private val menuItemDao: MenuItemDao,
    // DAO
){
    suspend fun getRestaurantsMenuResponse(restaurantId : Int) : Result<List<MenuItem>> {
        return try {
            val response = apiService.getRestaurantMenu(id = restaurantId)
            response.menu.forEach { item ->
                item.restaurantId = restaurantId // Set the restaurantId in the menu item
            }
            menuItemDao.insertMenuItems(response.menu)
            return Result.Success(response.menu)
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
            val cachedMenu = menuItemDao.getMenuItemsByRestaurantId(restaurantId).first()
            if (cachedMenu.isEmpty()){
                Result.Error("No cached Response found from server. Exception :" + e.message.toString())
            } else {
                Result.Success(cachedMenu)
            }

        }

    }
}