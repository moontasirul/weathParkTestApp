package com.wealthPark.testApplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wealthPark.testApplication.data.local.database.dao.CityDao
import com.wealthPark.testApplication.data.model.CityItem

/**
 * SQLite Database for storing the logs.
 */
@Database(entities = arrayOf(CityItem::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        const val DATABASE_NAME: String = "wealthpark.db"
    }
}