package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.MenuItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuItems(menuItems: List<MenuItem>)

    // Query the menu items by restaurantId
    @Query("SELECT * FROM menu_items WHERE restaurantId = :restaurantId")
    fun getMenuItemsByRestaurantId(restaurantId: Int): Flow<List<MenuItem>>

    @Query("DELETE FROM menu_items WHERE restaurantId = :restaurantId")
    suspend fun clearMenuItemsByRestaurantId(restaurantId: Int)
}
