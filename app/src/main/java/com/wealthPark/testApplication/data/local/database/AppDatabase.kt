package com.wealthPark.testApplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wealthPark.testApplication.data.local.database.dao.CityDao
import com.wealthPark.testApplication.data.local.database.dao.FoodDao
import com.wealthPark.testApplication.data.local.model.CityItem
import com.wealthPark.testApplication.data.local.model.FoodItem

/**
 * SQLite Database for storing the logs.
 */
//@Database(entities = arrayOf(CityItem::class), version = 1, exportSchema = false)
@Database(entities = [CityItem::class, FoodItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun foodDao(): FoodDao

    companion object {
        const val DATABASE_NAME: String = "wealthpark.db"
    }
}