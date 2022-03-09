package com.wealthPark.testApplication.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wealthPark.testApplication.data.model.City

@Dao
interface CityDao {

    @Query("SELECT * FROM city_info ORDER BY name DESC")
    fun getAll(): List<City>

    @Insert
    fun insertAll(vararg city: City)
}
