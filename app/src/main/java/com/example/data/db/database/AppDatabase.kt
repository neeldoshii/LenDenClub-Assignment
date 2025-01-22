package com.example.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.dao.MenuItemDao
import com.example.data.db.dao.RestaurantDao
import com.example.data.model.MenuItem
import com.example.data.model.RestaurantItem

@Database(entities = [RestaurantItem::class, MenuItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
    abstract fun menuItemDao(): MenuItemDao
}