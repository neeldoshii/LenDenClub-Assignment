package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.RestaurantItem

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurants")
    suspend fun getAllRestaurants(): List<RestaurantItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurants: List<RestaurantItem>)
}
